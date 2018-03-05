package cn.fts.controller;

import cn.fts.po.File;
import cn.fts.service.FileService;
import cn.fts.utils.FastDFSClient;
import cn.fts.utils.PageUtil;
import cn.fts.vo.VoFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping("/upload")
    public String upload(VoFile file) {
        /***
         * 检查文件大小
         * 检查时长是否合法
         * 填充file各个字段
         * 检查access字段是否 == 2,检查授权码
         * 上传file
         * 保存到 DB
         */
//        System.out.println("file1 = " + file);
        if (fileService.checkVoFile(file)) {
//            System.out.println("file2 = " + file);
            try {
                fileService.insert(file);
                return "common/successful";
            } catch (Exception e) {
                return "common/failed";
            }
        }
        return "common/failed";
    }

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        List<VoFile> viewList = fileService.list();
//        modelAndView.addObject("viewList",viewList);
        if (viewList == null && viewList.size() == 0) {
            viewList = null;
        }
        request.getSession().setAttribute("viewList",viewList);
        System.out.println("fileController list()\t" + viewList);
        return "index";
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
        }
    }




}
