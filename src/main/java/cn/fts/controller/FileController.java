package cn.fts.controller;

import cn.fts.po.File;
import cn.fts.service.FileService;
import cn.fts.utils.FastDFSClient;
import cn.fts.utils.FileUtils;
import cn.fts.utils.PageUtil;
import cn.fts.utils.RequestUtils;
import cn.fts.vo.ResponseData;
import cn.fts.vo.VoFile;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/file",produces = {"application/json;charset=UTF-8"})
public class FileController {

    private static final Logger logger = Logger.getLogger(FileController.class);

    @Autowired
    FileService fileService;

    @RequestMapping("/fastText")
    @ResponseBody
    public String fastText(VoFile file,String content,HttpServletRequest request) {
        file.setCurrentFileKind("fastText");
        try {

//            因为后面会生成一个临时文件，所以需要判断一下content长度

            java.io.File textFile = FileUtils.generateNewText("E://temp.txt",content);
            System.out.println(textFile.getName());
            file.setjFile(textFile);
//            prepareBeforeCheck
//            file.prepareBeforeCheck(file);
//            check
            fileService.check(file);
//            prepareAfterCheck
            fileService.prepareAfterCheck(file);
//            file.setSrcFile();
            fileService.insert(file);
            FileUtils.deleteFile(textFile);
            log("fastText","successful",request,file,"");
            return JSON.toJSONString(new ResponseData<>(0,"操作成功",null));
        } catch (Exception e) {
            log("fastText","failed",request,file,"");
            return JSON.toJSONString(new ResponseData<>(1,"操作失败",null));
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
            fileService.insert(file);
            log("upload","successful",request,file,"");
            return JSON.toJSONString(new ResponseData<>(0,"操作成功",null));
        } catch (Exception e) {
            log("upload","failed",request,file,"");
            return JSON.toJSONString(new ResponseData<>(1,"操作失败",null));
        }
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list() {
        List<VoFile> viewList = fileService.list();
        if (viewList == null && viewList.size() == 0) {
            viewList = null;
        }
        ResponseData<List<VoFile>> data = new ResponseData(0,"查询成功",viewList);
        return JSON.toJSONString(data);
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
            PageUtil.settingResponseForDownLoad(file.getName(),response);
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
