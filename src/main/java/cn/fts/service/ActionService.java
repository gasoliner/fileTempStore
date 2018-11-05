package cn.fts.service;

import cn.fts.po.Action;

import java.util.List;

public interface ActionService {

    int insert(Action action);

    Action selectByPrimaryKey(long id);

    List<Action> list();

    int deleteByPrimaryKey(long id);
}
