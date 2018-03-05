package cn.fts.test;

import cn.fts.utils.FastDFSClient;
import org.junit.Test;

import java.io.File;

public class FastDFSTest {

    @Test
    public void test1() {
//        File file = new File("E:\\temp.txt");
//        String fileId = FastDFSClient.uploadFile(file,"testfile180228.txt");
//        System.out.println("fileId:\t" + fileId);

//        File file = new File("F:\\testfile.txt");
        String fileId = "group1/M00/00/00/sHquUlqWSHSAc4D3AAAADxr3SS4881.txt";
//        FastDFSClient.downloadFile(fileId,file);
        int result = FastDFSClient.deleteFile(fileId);
        System.out.println("result:\t" + result);
    }

    @Test
    public void test3() {
        System.out.println("group1/M00/00/00/sHquUlqbW96AH3EPAAABcV5ZwSc7151712".length());
    }

    @Test
    public void test4() {
        long l = 1520151680000L;
        int i = 5*60*1000;
        System.out.println(l);
        System.out.println(i);
        System.out.println(i+l);
    }
}
