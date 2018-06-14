package cn.fts.utils;


import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static String getExtension (String fileName) {
        if (fileName.lastIndexOf(".") < 0) {
            return null;
        } else {
            return fileName.substring(fileName.lastIndexOf(".")+1);
        }
    }

    public static File generateNewText(String filePath,String content) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.close();
        return file;
    }

    public static boolean deleteFile(String filePath) throws IOException {
        return deleteFile(new File(filePath));
    }

    public static boolean deleteFile(File file) throws IOException {
        return file.delete();
    }
}
