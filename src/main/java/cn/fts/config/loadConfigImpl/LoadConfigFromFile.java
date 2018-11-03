package cn.fts.config.loadConfigImpl;

import cn.fts.config.LoadConfig;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class LoadConfigFromFile implements LoadConfig {

    private static Logger logger = Logger.getLogger(LoadConfigFromFile.class);

    private Properties props;

    private String defaultLoadProperties = "fts.properties";//默认加载配置文件

    /**
     * 加载配置文件
     */
    public LoadConfigFromFile() {
        props = new Properties();
        loadFile();
    }

    @Override
    public void loadAll(Map<String, String> map) {
//        懒汉模式加载，不需要加载全部配置
        props.clear();
        loadFile();
        return;
    }

    @Override
    public String loadSingle(String key) {
        return props.getProperty(key);
    }

    private void loadFile() {
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(defaultLoadProperties);
            props.load(is);
        } catch (IOException ex) {
            logger.warn("Could not load spider.properties" ,ex.getCause());
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
}
