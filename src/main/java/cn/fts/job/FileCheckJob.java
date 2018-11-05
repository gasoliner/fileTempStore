package cn.fts.job;

import cn.fts.po.ActionHelper;
import cn.fts.service.ActionService;
import cn.fts.service.impl.FileServiceImpl;
import cn.fts.utils.FastDFSClient;
import cn.fts.utils.RedisCacheManager;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/***
 * 文件检查流程：
 *  每隔t分钟，从数据库中查出所有文件的file_id，开始时间，保存时间（最新）；
 *  当前时间，比对是否超期，如果超期，将其删除，发送通知给对应用户，记录日志。
 */

public class FileCheckJob {
    private static final Logger logger = Logger.getLogger(FileCheckJob.class);

    @Autowired
    RedisCacheManager redisCacheManager;

    @Autowired
    ActionService actionService;

    public void run() {
        logger.info("fileCheckJob start...");
        long current = System.currentTimeMillis();
        String filePrefix = FileServiceImpl.REDIS_FTS_FILE_PREFIX;
        String keySetPrefix = FileServiceImpl.REDIS_FTS_KEY_SET_PREFIX;
        Set currentKeys = redisCacheManager.keys( filePrefix+ "*");
        Set allKeys = redisCacheManager.sGet(keySetPrefix);
//        求差集
        allKeys.removeAll(currentKeys);
//        将差集从key-set中删掉，根据fileId将实体文件删掉
        int preFixLen = filePrefix.length();
        for (Object o :
                allKeys) {
            String key = (String)o;
//            todo 以下两步最好改成批量的
            redisCacheManager.setRemove(keySetPrefix,key);
            FastDFSClient.deleteFile(key.substring(preFixLen));
        }
        String log = "fileCheckJob check completely! cost " + (System.currentTimeMillis() - current) + " millisecond, " + allKeys.size() + " files's entities was removed . removedFileId:" + JSON.toJSONString(allKeys);
        logger.info(log);
        actionService.insert(ActionHelper.deleteSysMultiSuccess(log));
    }

}
