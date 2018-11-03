package cn.fts.service;

import cn.fts.po.Config;

import java.util.List;

public interface ConfigService {
    List<Config> selectAll();

    Config selectByKey(String key);
}
