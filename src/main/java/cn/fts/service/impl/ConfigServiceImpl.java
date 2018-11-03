package cn.fts.service.impl;

import cn.fts.mapper.ConfigMapper;
import cn.fts.po.Config;
import cn.fts.po.ConfigExample;
import cn.fts.service.ConfigService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    ConfigMapper configMapper;

    @Override
    public List<Config> selectAll() {
        return configMapper.selectByExample(new ConfigExample());
    }

    @Override
    public Config selectByKey(String key) {
        ConfigExample example = new ConfigExample();
        ConfigExample.Criteria criteria = example.createCriteria();
        criteria.andKeywordEqualTo(key);
        List<Config> configs = configMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(configs)) {
            return null;
        }
        return configs.get(0);
    }
}
