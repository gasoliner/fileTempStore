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
//        if (file.getSrcFile().getSize() == 0||file.getSrcFile().getSize() > (Constant.getInt("singleFileMax")*1024)) {
//            return false;
//        }
//        int time = file.getDay()*24*60 + file.getHour()*60 + file.getMinute();
//        if (time > Constant.getInt("keepMax")) {
//            return false;
//        }
//        if (StringUtils.isEmpty(file.getName())) {
//            file.setName(file.getSrcFile().getOriginalFilename());
//        }
//        file.setKeep(time);
//        file.setStart(new Date());
//        file.setSize((int) (file.getSrcFile().getSize()/1024));
//        if (file.getAccess() == 2 && StringUtils.isEmpty(file.getAuthoricode())) {
//            file.setAuthoricode("123");
//        }
//        try {
//            file.setFileid(FastDFSClient.uploadFile(file.getSrcFile().getInputStream(),file.getName()));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
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

    @Override
    public void check(VoFile file) throws Exception {
        if ("fastText".equals(file.getCurrentFileKind())) {
            if (file.getjFile().length() == 0||file.getjFile().length() > (Constant.getInt("singleFileMax")*1024)) {
                throw new Exception("文件大小（" + file.getjFile().length() + "）非法，当前规定大小 = " + Constant.getInt("singleFileMax")*1024);
            }
        } else if ("uploadFile".equals(file.getCurrentFileKind())) {
            if (file.getSrcFile().getSize() == 0||file.getSrcFile().getSize() > (Constant.getInt("singleFileMax")*1024)) {
                throw new Exception("文件大小（" + file.getSrcFile().getSize() + "）非法，当前规定大小 = " + Constant.getInt("singleFileMax")*1024);
            }
        }
        int time = file.getDay()*24*60 + file.getHour()*60 + file.getMinute();
        if (time > Constant.getInt("keepMax")) {
            throw new Exception("文件时间 （" + time + "）非法，当前规定时间长度 = " + Constant.getInt("keepMax"));
        }
        return;
    }

    @Override
    public void prepareAfterCheck(VoFile file) throws IOException {
        int keep = file.getDay()*24*60 + file.getHour()*60 + file.getMinute();
        Date start = new Date();
        long size = 0;
        if ("fastText".equals(file.getCurrentFileKind())) {
            if (StringUtils.isEmpty(file.getName())) {
                file.setName(file.getjFile().getName());
            }
            size = file.getjFile().length();
            file.setFileid(FastDFSClient.uploadFile(file.getjFile(),file.getName()));
        } else if ("uploadFile".equals(file.getCurrentFileKind())) {
            if (StringUtils.isEmpty(file.getName())) {
                file.setName(file.getSrcFile().getOriginalFilename());
            }
            size = file.getSrcFile().getSize();
            file.setFileid(FastDFSClient.uploadFile(file.getSrcFile().getInputStream(),file.getName()));
        }
        file.setSize(size);
        file.setKeep(keep);
        file.setStart(start);
        if (file.getAccess() == 2 && StringUtils.isEmpty(file.getAuthoricode())) {
            file.setAuthoricode("123");
        }
    }

    @Override
    public String getSupportPreviewedProcessor(String fileid) {
        File file = fileMapper.selectByPrimaryKey(fileid);
        if (file == null) {
            return null;
        }
        String extension = FileUtils.getExtension(file.getName());
        String[] supportTypes = Constant.getConfig("supportTypes").split(",");
        for (String type:
                supportTypes) {
            String[] items = type.split("-");
            if (items[0].equals(extension)) {
                return items[1];
            }
        }
        return null;
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
                voFile.setDay(Math.toIntExact(timeDifference / (1000 * 60 * 60 * 24)));
                timeDifference %= (1000 * 60 * 60 * 24);
                voFile.setHour(Math.toIntExact(timeDifference / (1000 * 60 * 60)));
                timeDifference %= (1000 * 60 * 60);
                if (timeDifference / (1000 * 60) == 0) {
                    voFile.setMinute(1);
                } else {
                    voFile.setMinute(Math.toIntExact(timeDifference / (1000 * 60)));
                }
            }
            voFile.setAccess(file.getAccess());
            String action;
            String fileId = file.getFileid();
            String fileIdHtmlId = fileId.replaceAll("/","").replaceAll("\\.","");
            if (voFile.getAccess() == 2) {
                action = "<form class=\"form-inline\"><input id=\"" + fileIdHtmlId +"\" type=\"text\" class=\"form-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class=\"btn btn-success\" onclick=\"downloadFile('" + fileIdHtmlId + "','" + fileId +"')\">下载</button>&nbsp;&nbsp;" +
                        "<button class=\"btn btn-info\" data-toggle=\"modal\" onclick=\"requestPreview('" + fileId +"')\">预览该文件</button></form>";
            } else {
                action = "<button class=\"btn btn-success\" onclick=\"downloadFile('no','" + fileId + "')\">下载</button>&nbsp;&nbsp;" +
                        "<button class=\"btn btn-info\" data-toggle=\"modal\" onclick=\"requestPreview('" + fileId +"')\">预览该文件</button>";
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
