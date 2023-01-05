package com.dnt.cloud.layui.dao;

import com.dnt.cloud.layui.model.LayMenu;
import com.dnt.cloud.layui.model.LayMenuCriteria;
import java.util.List;

public interface LayMenuDAO {
    int deleteByExample(LayMenuCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(LayMenu record);

    int insertSelective(LayMenu record);

    List<LayMenu> selectByExample(LayMenuCriteria example);

    LayMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LayMenu record);

    int updateByPrimaryKey(LayMenu record);
}