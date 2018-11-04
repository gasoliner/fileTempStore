package cn.fts.service.impl;

import cn.fts.po.File;
import cn.fts.service.FileService;
import cn.fts.utils.*;
import cn.fts.vo.ResponseData;
import cn.fts.vo.VoFile;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service("fileService")
public class FileServiceImpl implements FileService {

    private static final Logger logger = Logger.getLogger(FileServiceImpl.class);

    private static final String REDIS_FTS_FILE_PREFIX = "fts:file:";

    private static final double MULTI_SIZE = 10;

    @Autowired
    RedisCacheManager redisCacheManager;

    public ResponseData<Integer> insert(VoFile file) {
        ResponseData<Integer> res;
        String key = REDIS_FTS_FILE_PREFIX + file.getFileid();
        try {
            redisCacheManager.set(key, JSON.toJSONString(file), file.getKeep() * 60);
            res = ResponseData.success();
        } catch (Throwable t) {
            logger.error("fileServiceImpl.insert error ", t);
            res = ResponseData.failed();
        }
        return res;
    }

    @Override
    public VoFile selectByPrimaryKey(String fileId) {
        try {
            String result = (String) redisCacheManager.get(REDIS_FTS_FILE_PREFIX + fileId);
            VoFile voFile = JSON.parseObject(result, VoFile.class);
            return voFile;
        } catch (Throwable t) {
            logger.error("FileServiceImpl.selectByPrimaryKey, fileId = " + fileId, t);
            return null;
        }
    }

    public int deleteBatchByPrimaryKey(List<String> idList) {
//        System.out.println(TimeUtils.dateToString() + "deleteBatchByPrimaryKey idList \t" + idList);
//        FileExample example = new FileExample();
//        for (String id:
//                idList) {
//            if (deleteFileEntry(id) >= 0) {
//                example.or().andFileidEqualTo(id);
//            }
//        }
//        fileMapper.deleteByExample(example);
        return 0;
    }

    @Override
    public ResponseData<List<VoFile>> list() {
        List<VoFile> fileList;
        Set<String> keys;
        try {
            keys = redisCacheManager.keys(REDIS_FTS_FILE_PREFIX + "*");
        } catch (Throwable t) {
            logger.error("fileServiceImpl.list, an error cached when keys() ", t);
            return ResponseData.failed();
        }
        if (CollectionUtils.isNotEmpty(keys)) {
            fileList = new ArrayList<>(keys.size());
//            int n = (int) Math.ceil(keys.size()/MULTI_SIZE);
            Iterator<String> keySetIteartor = keys.iterator();
//            单个查询
            while (keySetIteartor.hasNext()) {
                String key = keySetIteartor.next();
                String result = "";
                long expire = 0;
                try {
                    result = (String) redisCacheManager.get(key);
                    expire = redisCacheManager.getExpire(key);
                } catch (Throwable t) {
                    logger.error("fileServiceImpl.list, an error cached when get() or getExpire() ,key = " + key, t);
                }
                VoFile voFile = JSON.parseObject(result, VoFile.class);
                voFile.setRemaining(expire);
                fileList.add(vo(voFile));
            }
//            批量查询
//            List<String> keyList;
//            for (int i = 0; i < n; i++) {
//                keyList = new ArrayList((int) MULTI_SIZE);
//                for (int j = 0; j < MULTI_SIZE && keySetIteartor.hasNext();j++) {
//                    String key = keySetIteartor.next();
//                    keyList.add(key);
//                }
//                List<Object> tempFileList;
//                try {
//                    tempFileList = redisCacheManager.mGet(keyList);
//                } catch (Throwable t) {
//                    logger.error("fileServiceImpl.list. an error cached when mGet() ",t);
//                    return ResponseData.failed();
//                }
//                for (Object temp:
//                        tempFileList) {
//                    VoFile voFile = JSON.parseObject((String) temp, VoFile.class);
//                    fileList.add(voFile);
//                }
//            }
            return ResponseData.success(fileList);
        }
        return ResponseData.success();
    }

    @Override
    public ResponseData<String> deleteByPrimaryKey(String id) {
        logger.info("FileServiceImpl.deleteByPrimaryKey id = " + id);
        try {
            redisCacheManager.del(REDIS_FTS_FILE_PREFIX + id);
            return ResponseData.success();
        } catch (Throwable t) {
            return ResponseData.failed();
        }
    }

    @Override
    public void check(VoFile file) throws Exception {
        if ("fastText".equals(file.getCurrentFileKind())) {
            if (file.getjFile().length() == 0 || file.getjFile().length() > (Constant.getInt("singleFileMax") * 1024)) {
                throw new Exception("文件大小（" + file.getjFile().length() + "）非法，当前规定大小 = " + Constant.getInt("singleFileMax") * 1024);
            }
        } else if ("uploadFile".equals(file.getCurrentFileKind())) {
            if (file.getSrcFile().getSize() == 0 || file.getSrcFile().getSize() > (Constant.getInt("singleFileMax") * 1024)) {
                throw new Exception("文件大小（" + file.getSrcFile().getSize() + "）非法，当前规定大小 = " + Constant.getInt("singleFileMax") * 1024);
            }
        }
        int time = file.getDay() * 24 * 60 + file.getHour() * 60 + file.getMinute();
        if (time > Constant.getInt("keepMax")) {
            throw new Exception("文件时间 （" + time + "）非法，当前规定时间长度 = " + Constant.getInt("keepMax"));
        }
        return;
    }

    @Override
    public void prepareAfterCheck(VoFile file) throws IOException {
        int keep = file.getDay() * 24 * 60 + file.getHour() * 60 + file.getMinute();
        file.setDay(null);
        file.setHour(null);
        file.setMinute(null);
        Date start = new Date();
        long ex = start.getTime() + keep*60*1000;
        Date expirationTime = new Date(ex);
        long size = 0;
        if ("fastText".equals(file.getCurrentFileKind())) {
            if (StringUtils.isEmpty(file.getName())) {
                file.setName(file.getjFile().getName());
            } else {
                file.setName(file.getName() + ".txt");
            }
            size = file.getjFile().length();
            file.setFileid(FastDFSClient.uploadFile(file.getjFile(), file.getName()));
        } else if ("uploadFile".equals(file.getCurrentFileKind())) {
            if (StringUtils.isEmpty(file.getName())) {
                file.setName(file.getSrcFile().getOriginalFilename());
            }
            size = file.getSrcFile().getSize();
            file.setFileid(FastDFSClient.uploadFile(file.getSrcFile().getInputStream(), file.getName()));
        }
        file.setSize(size);
        file.setKeep(keep);
        file.setStart(start);
        file.setExpirationTime(expirationTime);
        if (file.getAccess() == 2 && StringUtils.isEmpty(file.getAuthoricode())) {
            file.setAuthoricode("123");
        }
    }

    @Override
    public String getSupportPreviewedProcessor(String fileid) {
        VoFile file = selectByPrimaryKey(fileid);
        if (file == null) {
            return null;
        }
        String extension = FileUtils.getExtension(file.getName());
        String[] supportTypes = Constant.getConfig("supportTypes").split(",");
        for (String type :
                supportTypes) {
            String[] items = type.split("-");
            if (items[0].equals(extension)) {
                return items[1];
            }
        }
        return null;
    }

    private VoFile vo(VoFile file) {

//        对剩余时间的处理
        long timeDifference = file.getRemaining();
        file.setDay(Math.toIntExact(timeDifference / (60 * 60 * 24)));
        timeDifference %= (60 * 60 * 24);
        file.setHour(Math.toIntExact(timeDifference / (60 * 60)));
        timeDifference %= (60 * 60);
        if (timeDifference / 60 == 0) {
            file.setMinute(1);
        } else {
            file.setMinute(Math.toIntExact(timeDifference / 60));
        }

//        String fileId = file.getFileid();
//        String fileIdHtmlId = fileId.replaceAll("/", "").replaceAll("\\.", "");
//        if (voFile.getAccess() == 2) {
//            action = "<div class=\"form-inline\"><input id=\"" + fileIdHtmlId + "\" type=\"text\" class=\"form-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class=\"btn btn-success\" onclick=\"downloadFile('" + fileIdHtmlId + "','" + fileId + "')\">下载</button>&nbsp;&nbsp;" +
//                    "<button class=\"btn btn-info\" data-toggle=\"modal\" onclick=\"requestPreview('" + fileIdHtmlId + "','" + fileId + "')\">预览该文件</button></div>";
//        } else {
//            action = "<button class=\"btn btn-success\" onclick=\"downloadFile('no','" + fileId + "')\">下载</button>&nbsp;&nbsp;" +
//                    "<button class=\"btn btn-info\" data-toggle=\"modal\" onclick=\"requestPreview('no','" + fileId + "')\">预览该文件</button>";
//        }
//        voFile.setAction(action);
        return file;
    }

    private int deleteFileEntry(String id) {
        return FastDFSClient.deleteFile(id);
    }
}
