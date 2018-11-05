package cn.fts.service;

import cn.fts.vo.ResponseData;
import cn.fts.vo.VoFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    ResponseData<Integer> insert(VoFile file);

    VoFile selectByPrimaryKey(String fileId);

    boolean deleteBatchByPrimaryKey(List<String> idList);

    ResponseData<List<VoFile>> list();

    ResponseData<String> deleteByPrimaryKey(String id);

    void check (VoFile file) throws Exception;

    void prepareAfterCheck(VoFile file) throws IOException;

    String getSupportPreviewedProcessor(String fileid);
}
