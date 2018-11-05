package cn.fts.service.impl;

import cn.fts.mapper.ActionMapper;
import cn.fts.po.Action;
import cn.fts.po.ActionExample;
import cn.fts.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("actionService")
public class ActionServiceImpl implements ActionService {

    @Autowired
    ActionMapper actionMapper;

    @Override
    public int insert(Action action) {
        return actionMapper.insertSelective(action);
    }

    @Override
    public Action selectByPrimaryKey(long id) {
        return actionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Action> list() {
        return actionMapper.selectByExample(new ActionExample());
    }

    @Override
    public int deleteByPrimaryKey(long id) {
        return actionMapper.deleteByPrimaryKey(id);
    }
}
