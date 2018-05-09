package cn.fts.test;

import cn.fts.utils.FastDFSClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.File;
import java.util.HashMap;

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

//    @Test
//    public void test4() {
//        HttpServletRequest request = new HttpServletRequestWrapper(null);
//        HttpServletResponse response = new HttpServletResponseWrapper(null);
//        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext();
//
//    }
}
