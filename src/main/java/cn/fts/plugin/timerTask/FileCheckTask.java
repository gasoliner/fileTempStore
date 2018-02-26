package cn.fts.plugin.timerTask;

import cn.fts.po.File;
import cn.fts.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/***
 * 文件检查流程：
 *  每隔t分钟，从数据库中查出所有文件的file_id，开始时间，保存时间（最新）；
 *  当前时间，比对是否超期，如果超期，将其删除，发送通知给对应用户，记录日志。
 */

public class FileCheckTask extends TimerTask {
    private static boolean isRunning = false;

    @Autowired
    FileService fileService;
//    @Autowired
//    NoticeService noticeService

    public void run() {
        if (!isRunning) {
            isRunning = true;
            List<File> fileList = fileService.select();
            List<String> overdueIdList = checkOverdueFile(fileList);
            fileService.deleteBatchByPrimaryKey(overdueIdList);
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
            Date keep = new Date(file.getKeep()*60*1000);
            if ((start.getTime()+keep.getTime()) > now.getTime()) {
                overdueIdList.add(file.getFileid());
            }
        }
        return overdueIdList;
    }
}
