package cn.fts.controller;

import cn.fts.po.File;
import cn.fts.preview.PreviewProcessor;
import cn.fts.service.FileService;
import cn.fts.utils.*;
import cn.fts.vo.ResponseData;
import cn.fts.vo.VoFile;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping(value = "/file",produces = {"application/json;charset=UTF-8"})
public class FileController {

    private static final Logger logger = Logger.getLogger(FileController.class);

    @Autowired
    FileService fileService;

    @RequestMapping("/previewed")
    @ResponseBody
    public String previewed(String fileid,String authoricode) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        File file = fileService.selectByPrimaryKey(fileid);
        boolean flag = true;
        if (file.getAccess() == 2) {
            if (!file.getAuthoricode().equals(authoricode)) {
                flag = false;
            }
        }
        if (flag) {
            String supportPreviewProcessor = fileService.getSupportPreviewedProcessor(fileid);
            if (supportPreviewProcessor != null) {
                PreviewProcessor processor = (PreviewProcessor)Class.forName("cn.fts.preview.impl." + supportPreviewProcessor +"PreviewProcessor").newInstance();
                String result = processor.previewed(fileid);
                return JSON.toJSONString(new ResponseData<>(0,"操作成功",result));
            }
        }

        return JSON.toJSONString(new ResponseData<>(1,"操作失败","预览失败，可能是该文件暂不支持预览或者权限校验未通过"));
    }

    @RequestMapping("/fastText")
    @ResponseBody
    public String fastText(VoFile file,String content,HttpServletRequest request) {
        file.setCurrentFileKind("fastText");
        try {

//            因为后面会生成一个临时文件，所以需要判断一下content长度
            if (content.length() > Constant.getInt("fastText_maxSize")) {
                throw new Exception("字符串长度（" + content.length() + "）非法，当前规定大小 = " + Constant.getInt("fastText_maxSize"));
            }
            java.io.File textFile = null;
            synchronized (this) {
                textFile = FileUtils.generateNewText("/" + UUID.randomUUID().toString().replaceAll("-","") +".txt",content);
            }
            logger.debug(textFile.getName());
            file.setjFile(textFile);
//            check
            fileService.check(file);
//            prepareAfterCheck
            fileService.prepareAfterCheck(file);
//            file.setSrcFile();
            ResponseData<Integer> responseData = fileService.insert(file);
//            todo 记录操作！！！
            FileUtils.deleteFile(textFile);
            log("fastText","successful",request,file,"");
            return JSON.toJSONString(responseData);
        } catch (Exception e) {
            log("fastText","failed",request,file,e.getMessage());
            return JSON.toJSONString(ResponseData.failed());
        }
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(HttpServletRequest request,VoFile file) {
        /***
         * 检查文件大小
         * 检查时长是否合法
         * 填充file各个字段
         * 检查access字段是否 == 2,检查授权码
         * 上传file
         * 保存到 DB
         */
        file.setCurrentFileKind("uploadFile");
        try {
            fileService.check(file);
            fileService.prepareAfterCheck(file);
            ResponseData responseData = fileService.insert(file);
//            todo 记录操作！！！
            log("upload","successful",request,file,"");
            return JSON.toJSONString(responseData);
        } catch (Exception e) {
            log("upload","failed",request,file,e.getMessage());
            return JSON.toJSONString(ResponseData.failed());
        }
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list() {
        ResponseData responseData = fileService.list();
        return JSON.toJSONString(responseData);
    }

    @RequestMapping("/download")
    public void download(String authoricode,String fileId,HttpServletRequest request,HttpServletResponse response) throws IOException {
        File file = fileService.selectByPrimaryKey(fileId);
        boolean flag = true;
        if (file.getAccess() == 2) {
            if (!file.getAuthoricode().equals(authoricode)) {
                flag = false;
            }
        } else if (file.getAccess() == 3) {
//            检查登录状态是否正确，否则flag=false;
        }
        if (flag) {
//            允许下载文件
            PageUtil.settingResponseForDownLoad(file.getName(),request,response);
            FastDFSClient.downloadFile(fileId,response.getOutputStream());
            log("download","success",request,file,"");
        }
    }

    private void log(String action,String isSuccess,HttpServletRequest request,File file,String another) {
        logger.debug("fileController\t" + action +"\t" + isSuccess + "\tip=" + RequestUtils.getRemoteAddr(request)
                + "\tfileName=" + file.getName()
                + "\tfileKeep=" + file.getKeep()
                + "\tfileAccess=" + file.getAccess()
                + "\tfileId=" + file.getFileid()
                + "\tfileSize=" + file.getSize() + another);
    }
}
