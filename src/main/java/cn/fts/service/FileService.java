package cn.fts.service;

import cn.fts.po.File;
import cn.fts.vo.VoFile;

import java.util.List;

public interface FileService {

    boolean checkVoFile(VoFile file);

    int insert(VoFile file);

    List<File> select();

    File selectByPrimaryKey(String id);

    int deleteBatchByPrimaryKey(List<String> idList);

}