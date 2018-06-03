package cn.fts.service.impl;

import cn.fts.mapper.FileMapper;
import cn.fts.mapper.UserMapper;
import cn.fts.po.File;
import cn.fts.po.FileExample;
import cn.fts.service.FileService;
import cn.fts.service.UserService;
import cn.fts.utils.Constant;
import cn.fts.utils.FastDFSClient;
import cn.fts.utils.FileUtils;
import cn.fts.utils.TimeUtils;
import cn.fts.vo.VoFile;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Autowired
    FileMapper fileMapper;
    @Autowired
    UserService userService;


    public boolean checkVoFile(VoFile file) {
        if (file.getSrcFile().getSize() == 0||file.getSrcFile().getSize() > (Constant.getInt("singleFileMax")*1024)) {
            return false;
        }
        int time = file.getDay()*24*60 + file.getHour()*60 + file.getMinute();
        if (time > Constant.getInt("keepMax")) {
            return false;
        }
        if (StringUtils.isEmpty(file.getName())) {
            file.setName(file.getSrcFile().getOriginalFilename());
        }
        file.setKeep(time);
        file.setStart(new Date());
        file.setSize((int) (file.getSrcFile().getSize()/1024));
        if (file.getAccess() == 2 && StringUtils.isEmpty(file.getAuthoricode())) {
            file.setAuthoricode("123");
        }
        try {
            file.setFileid(FastDFSClient.uploadFile(file.getSrcFile().getInputStream(),file.getName()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int insert(VoFile file) {
        return fileMapper.insert(file);
    }

    public List<File> select() {
        return fileMapper.selectByExample(new FileExample());
    }

    public File selectByPrimaryKey(String id) {
        return fileMapper.selectByPrimaryKey(id);
    }

    public int deleteBatchByPrimaryKey(List<String> idList) {
//        System.out.println(TimeUtils.dateToString() + "deleteBatchByPrimaryKey idList \t" + idList);
        FileExample example = new FileExample();
        for (String id:
                idList) {
            if (deleteFileEntry(id) >= 0) {
                example.or().andFileidEqualTo(id);
            }
        }
        return fileMapper.deleteByExample(example);
    }

    /***
     * 先检查其登录状态，权限，再决定显示内容。
     */
    @Override
    public List<VoFile> list() {
        FileExample example = new FileExample();
        FileExample.Criteria criteria = example.createCriteria();

//        未登录状态
        if (true) {
            criteria.andAccessNotEqualTo(3);
        } else {
//            已登录状态
//            criteria.andUploadbyEqualTo("小明");
        }
        List<File> fileList = fileMapper.selectByExample(example);
        return vo(fileList);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        System.out.println("deleteByPrimaryKey fileId\t" + id);
        if (deleteFileEntry(id) >= 0) {
            return fileMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

    private List<VoFile> vo(List<File> fileList) {
        List<VoFile> voFileList = new ArrayList<>();
        for (File file:
                fileList) {
            VoFile voFile = new VoFile();
            voFile.setName(file.getName());
            voFile.setSize(file.getSize());
            voFile.setStart(file.getStart());
            long expirationTime = file.getStart().getTime() + file.getKeep()*60*1000;
            long timeDifference = expirationTime - new Date().getTime();
            if (timeDifference < 0) {
                deleteByPrimaryKey(file.getFileid());
                continue;
            } else {
                voFile.setExpirationTime(new Date(expirationTime));
            }
            voFile.setAccess(file.getAccess());
            String action;
            String fileId = file.getFileid();
            String fileIdHtmlId = fileId.replaceAll("/","").replaceAll("\\.","");
            if (voFile.getAccess() == 2) {
                action = "<input id=\"" + fileIdHtmlId +"\" type=\"text\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"button\" value=\"download\" onclick=\"downloadFile('" + fileIdHtmlId +"','" + fileId +"')\">";
            } else {
                action = "<input type=\"button\" value=\"download\" onclick=\"downloadFile('no','" + fileId + "')\">";
            }
            voFile.setAction(action);
            voFileList.add(voFile);
        }
        return voFileList;
    }

    private int deleteFileEntry(String id) {
        return FastDFSClient.deleteFile(id);
    }
}
