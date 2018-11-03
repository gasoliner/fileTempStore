package cn.fts.plugin;

import cn.fts.config.ConstantAd;
import cn.fts.plugin.timerTask.FileCheckTask;
import cn.fts.utils.Constant;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Timer;

public class TimeTask {

    @Autowired
    private ConstantAd constantAd;

    /**
     * 每分钟的毫秒数
     */
    public static final long PERIOD_MINUTE = DateUtils.MILLIS_PER_MINUTE;
    /**
     * 无延迟
     */
    public static final long NO_DELAY = 0;
    /**
     * 延迟2分钟
     */
    public static final long DELAY_2MINUTE = PERIOD_MINUTE*2;
    /**
     * 定时器
     */
    private Timer timer;

    public void start() {
        int scanningInterval = constantAd.getInt("scanningInterval");
        timer = new Timer("文件过期检查",true);
        timer.schedule(new FileCheckTask(),NO_DELAY,PERIOD_MINUTE*scanningInterval);
    }

    public void cancel() {
        timer.cancel();
    }
}
