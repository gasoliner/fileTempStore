package cn.fts.utils;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static String getExtension (String fileName) {
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }

}
