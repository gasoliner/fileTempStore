package cn.fts.mapper;

import cn.fts.po.Systemddl;
import cn.fts.po.SystemddlExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemddlMapper {
    long countByExample(SystemddlExample example);

    int deleteByExample(SystemddlExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(Systemddl record);

    int insertSelective(Systemddl record);

    List<Systemddl> selectByExample(SystemddlExample example);

    Systemddl selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") Systemddl record, @Param("example") SystemddlExample example);

    int updateByExample(@Param("record") Systemddl record, @Param("example") SystemddlExample example);

    int updateByPrimaryKeySelective(Systemddl record);

    int updateByPrimaryKey(Systemddl record);
}