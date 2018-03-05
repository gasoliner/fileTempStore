package cn.fts.listener;

import cn.fts.plugin.TimeTask;
import cn.fts.utils.Constant;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * web启动监听器
 */
public class WebContextListener extends ContextLoaderListener {

//    private TimeTask task;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n======================================================================\r\n");
        sb.append("\r\n       欢迎使用 "+ Constant.getConfig("productName") + "  ^_^\r\n");
        sb.append("\r\n======================================================================\r\n");
        System.out.println(sb.toString());
//        task = new TimeTask();
//        task.start();
        super.contextInitialized(event);

    }

//    @Override
//    public void contextDestroyed(ServletContextEvent event) {
//        task.cancel();
//    }
}