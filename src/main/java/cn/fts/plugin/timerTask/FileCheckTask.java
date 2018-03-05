package cn.fts.plugin.timerTask;

import cn.fts.po.File;
import cn.fts.service.FileService;
import cn.fts.utils.PageUtil;
import cn.fts.utils.SpringContextUtil;
import cn.fts.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/***
 * 文件检查流程：
 *  每隔t分钟，从数据库中查出所有文件的file_id，开始时间，保存时间（最新）；
 *  当前时间，比对是否超期，如果超期，将其删除，发送通知给对应用户，记录日志。
 */

@Component
public class FileCheckTask extends TimerTask {
    private static boolean isRunning = false;
    private int checkCount = 1;

    FileService fileService;

    public FileCheckTask() {
        fileService = SpringContextUtil.getBean("fileService");
    }
//    @Autowired
//    NoticeService noticeService

    public void run() {
        if (!isRunning) {
            isRunning = true;
            checkCount++;
            List<File> fileList = fileService.select();
            List<String> overdueIdList = checkOverdueFile(fileList);
            if (overdueIdList != null && overdueIdList.size() > 0) {
                fileService.deleteBatchByPrimaryKey(overdueIdList);
            }
            System.out.println(TimeUtils.dateToString() + " 第 " + checkCount + " 次检查，被删除的文件ID：\t" + overdueIdList);
            isRunning = false;
        } else {
        }
    }

    private List<String> checkOverdueFile(List<File> fileList) {
        Date now = new Date();
        List<String> overdueIdList = new ArrayList<>();
        for (File file:
                fileList) {
            Date start = file.getStart();
            if ((start.getTime()+file.getKeep()*60*1000) < now.getTime()) {
//                System.out.println("overDue file id :\t" + file.getFileid() + "\tstart:\t" + start.getTime() + "\tkeep:\t" + file.getKeep() + "\tstart + keep:\t" + (start.getTime()+file.getKeep()*60*1000) + "\tnow:\t" + now.getTime());
                overdueIdList.add(file.getFileid());
            }
        }
        return overdueIdList;
    }
}
