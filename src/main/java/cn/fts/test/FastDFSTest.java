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


}
