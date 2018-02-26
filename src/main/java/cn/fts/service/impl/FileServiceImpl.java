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
import cn.fts.vo.VoFile;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Autowired
    FileMapper fileMapper;
    @Autowired
    UserService userService;

    public boolean checkVoFile(VoFile file) {
        if (file.getSrcFile().getSize() > (Constant.getInt("singleFileMax")*1024)) {
            return false;
        }
        int time = file.getDay()*24*60 + file.getHour()*60 + file.getMinute();
        if (time > Constant.getInt("keepMax")) {
            return false;
        }
        if (file.getName() == null) {
            file.setName(file.getSrcFile().getOriginalFilename());
        }
        file.setKeep(time);
        file.setStart(new Date());
        if (file.getAccess() == 2 && StringUtils.isEmpty(file.getAuthoricode())) {
            file.setAuthoricode("123");
        }
        try {
            file.setFileid(FastDFSClient.uploadFile(FileUtils.InputStreamToFile(file.getSrcFile().getInputStream()),file.getName()));
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
        FileExample example = new FileExample();
        for (String id:
                idList) {
            if (deleteFileEntry(id) >= 0) {
                example.or().andFileidEqualTo(id);
            }
        }
        return fileMapper.deleteByExample(example);
    }

    private int deleteFileEntry(String id) {
        return FastDFSClient.deleteFile(id);
    }
}
