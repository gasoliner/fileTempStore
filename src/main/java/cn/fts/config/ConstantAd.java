package cn.fts.config;

import cn.fts.config.listener.ConfigListener;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ww on 2017/11/20.
 * Updated by Ww on 2018/10/19.支持热重加载配置，支持从配置文件和数据库等多种方式
 */
public class ConstantAd {
    private static Logger logger = Logger.getLogger(ConstantAd.class);

    /**
     * 保存全局属性值(缓存)
     */
    private Map<String, String> map = Maps.newHashMap();

    /**
     * 支持热重加载的configListenerList
     */
    @Autowired
    private List<ConfigListener> configListenerList;

    private LoadConfig loadConfig;

    public ConstantAd(LoadConfig loadConfig) {
        this.loadConfig = loadConfig;
        loadConfig.loadAll(map);
    }

    public ConstantAd() {
    }

    /**
     * 刷新配置
     */
    public synchronized void refresh() {
        clearCache();
        loadConfig.loadAll(map);
        notifyAllConfigListener();
    }

    /**
     * 清空配置缓存
     */
    private void clearCache() {
        map.clear();
    }

    /**
     * 通知所有监听者更新配置
     */
    private synchronized void notifyAllConfigListener() {
        for (ConfigListener temp :
                configListenerList) {
            temp.updateConfig(this);
        }
    }



    /**
     * 获取配置属性
     * @param key
     * @return
     */
    public String getConfig(String key) {
        String value = map.get(key);
        if (value == null){
            value = loadConfig.loadSingle(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }


    public Integer getInt(String key) {
        return getInt(key, null);
    }

    public Integer getInt(String key, Integer defaultValue) {
        String value = getConfig(key);
        if (value != null)
            return Integer.parseInt(value.trim());
        return defaultValue;
    }

    public Long getLong(String key) {
        return getLong(key, null);
    }

    public Long getLong(String key, Long defaultValue) {
        String value = getConfig(key);
        if (value != null)
            return Long.parseLong(value.trim());
        return defaultValue;
    }


    public Boolean getBoolean(String key) {
        return getBoolean(key, null);
    }


    public Boolean getBoolean(String key, Boolean defaultValue) {
        String value = getConfig(key );
        if (value != null) {
            value = value.toLowerCase().trim();
            if ("true".equals(value))
                return true;
            else if ("false".equals(value))
                return false;
            throw new RuntimeException("The value can not parse to Boolean : "
                    + value);
        }
        return defaultValue;
    }

    public Set<Map.Entry<String, String>> settings() {
        return map.entrySet();
    }

    public List<ConfigListener> getConfigListenerList() {
        return configListenerList;
    }

    public void setConfigListenerList(List<ConfigListener> configListenerList) {
        this.configListenerList = configListenerList;
    }
}
