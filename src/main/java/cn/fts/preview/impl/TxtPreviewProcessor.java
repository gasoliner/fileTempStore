package cn.fts.preview.impl;

import cn.fts.preview.PreviewProcessor;
import cn.fts.utils.FastDFSClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * Created by Ww on 2018/6/14.
 */
public class TxtPreviewProcessor implements PreviewProcessor {

    @Override
    public String previewed(String fileId) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        FastDFSClient.downloadFile(fileId, outputStream);
        String result = null;
        try {
            result = outputStream.toString();
            outputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
