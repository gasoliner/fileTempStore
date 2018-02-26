package cn.fts.service;

import cn.fts.vo.VoFile;

public interface FileService {

    public boolean checkVoFile(VoFile file);

    public int insert(VoFile file);

}
