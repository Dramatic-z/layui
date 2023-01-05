package com.dnt.cloud.layui.dao;

import com.dnt.cloud.layui.model.NrcActivity;
import com.dnt.cloud.layui.model.NrcActivityCriteria;

import java.util.List;

/**
 * @author dramatic
 */
public interface NrcActivityDAO {
    int deleteByExample(NrcActivityCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(NrcActivity record);

    int insertSelective(NrcActivity record);

    List<NrcActivity> selectByExample(NrcActivityCriteria example);

    NrcActivity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NrcActivity record);

    int updateByPrimaryKey(NrcActivity record);
}