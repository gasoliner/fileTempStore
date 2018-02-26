package cn.fts.utils;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static String getExtension (String fileName) {
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }

    public static File InputStreamToFile(InputStream inputStream) throws IOException {
        File file = new File("");
        org.apache.commons.io.FileUtils.copyInputStreamToFile(inputStream,file);
        return file;
    }
}
