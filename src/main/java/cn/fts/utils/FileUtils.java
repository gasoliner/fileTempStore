package cn.fts.utils;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static String getExtension (String fileName) {
        return fileName.substring(fileName.lastIndexOf(".")+1);
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
        File file = new File(filePath);
        return file.delete();
    }
}
