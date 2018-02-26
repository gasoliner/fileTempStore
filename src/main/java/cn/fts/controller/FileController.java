package cn.fts.controller;

import cn.fts.service.FileService;
import cn.fts.vo.VoFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
        System.out.println("file1 = " + file);
        if (fileService.checkVoFile(file)) {
            System.out.println("file2 = " + file);
            try {
                fileService.insert(file);
                return "successful";
            } catch (Exception e) {
                return "failed";
            }
        }
        return "failed";
    }
}
