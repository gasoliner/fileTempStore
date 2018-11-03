package cn.fts.config.loadConfigImpl;

import cn.fts.config.LoadConfig;
import cn.fts.po.Config;
import cn.fts.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class LoadConfigFromDB implements LoadConfig {

    @Autowired
    private ConfigService configService;

    @Override
    public void loadAll(Map<String,String> map) {
        List<Config> configList = configService.selectAll();
        for (Config temp:
             configList) {
            map.put(temp.getKeyword(),temp.getValuee());
        }
        return;
    }

    @Override
    public String loadSingle(String key) {
        Config config = configService.selectByKey(key);
        if (null == config) {
            return null;
        }
        return config.getValuee();
    }
}
