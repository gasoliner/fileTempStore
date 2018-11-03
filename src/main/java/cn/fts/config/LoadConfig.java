package cn.fts.config;

import java.util.Map;

public interface LoadConfig {

    void loadAll(Map<String, String> map);

    String loadSingle(String key);
}
