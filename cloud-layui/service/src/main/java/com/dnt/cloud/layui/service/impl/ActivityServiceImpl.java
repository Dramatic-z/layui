package com.dnt.cloud.layui.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.dnt.cloud.layui.dao.NrcActivityDAO;
import com.dnt.cloud.layui.model.NrcActivity;
import com.dnt.cloud.layui.model.NrcActivityCriteria;
import com.dnt.cloud.layui.service.ActivityService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.dnt.cloud.common.domain.result.PageData;
import com.dnt.cloud.common.lang.StringUtil;
import com.dnt.cloud.layui.domain.NrcActivityDomain;
import com.dnt.cloud.layui.query.NrcActivityQuery;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
	
	@Resource
	private NrcActivityDAO nrcActivityDAO;

	@Override
	public PageData<NrcActivityDomain> getActivityByCondition(
			NrcActivityQuery query) {
		NrcActivityCriteria example = new NrcActivityCriteria();
		NrcActivityCriteria.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotBlank(query.getNameChina())) {
			criteria.andNameChinaLike("%"+query.getNameChina()+"%");
		}
		if (StringUtil.isNotBlank(query.getNameEng())) {
			criteria.andNameEngLike("%"+query.getNameEng()+"%");
		}
		if (query.getMarketId()!=null) {
			criteria.andMarketIdEqualTo(query.getMarketId());
		}
		if (query.getStatus()!=null) {
			criteria.andStatusEqualTo(query.getStatus());
		}
		if(query.getGmtCreate()!=null){
			criteria.andStartTimeLessThanOrEqualTo(query.getGmtCreate());
			criteria.andEndTimeGreaterThanOrEqualTo(query.getGmtCreate());
		}
		PageHelper.startPage(query.getPageNum(), query.getPageSize(),query.getOrderBy());
		Page<NrcActivity> page = (Page<NrcActivity>) nrcActivityDAO.selectByExample(example);
		if (CollectionUtils.isEmpty(page)) {
			return null;
		}
		List<NrcActivityDomain> list = new ArrayList<NrcActivityDomain>();
		page.forEach(data -> {
			list.add(this.converNADomain(data));
		});
		return new PageData<NrcActivityDomain>(list, page.getTotal(),page.getPageNum(), page.getPageSize());
	}

	@Override
	public NrcActivityDomain saveActivity(NrcActivityDomain domain) {
		NrcActivity nrcactivity = this.converNAModel(domain);
		int res = nrcActivityDAO.insertSelective(nrcactivity);
		return res == 1 ? this.converNADomain(nrcactivity) : null;
	}

	@Override
	public boolean deleteActivityById(Long id) {
		if (id == null) {
			Assert.isNull("id 不能为空");
		}
		int result = nrcActivityDAO.deleteByPrimaryKey(id);
		return result == 1 ? true : false;
	}

	@Override
	public NrcActivityDomain queryActivityById(Long id) {
		if (id == null) {
			Assert.isNull("id 不能为空");
		}
		NrcActivity nrcactivity = nrcActivityDAO.selectByPrimaryKey(id);
		return this.converNADomain(nrcactivity);
	}

	@Override
	public NrcActivityDomain updateActivity(NrcActivityDomain domain) {
		NrcActivity nrcactivity = this.converNAModel(domain);
		int res = nrcActivityDAO.updateByPrimaryKeySelective(nrcactivity);
		return res == 1 ? this.converNADomain(nrcactivity) : null;
	}
	
	
	private NrcActivityDomain converNADomain(NrcActivity source) {
		if (source == null) {
			return null;
		}
		NrcActivityDomain target = new NrcActivityDomain();
		BeanUtils.copyProperties(source, target);
		return target;
	}
	
	private NrcActivity converNAModel(NrcActivityDomain source) {
		if (source == null) {
			return null;
		}
		NrcActivity target = new NrcActivity();
		BeanUtils.copyProperties(source, target, "gmtCreate", "gmtModify");
		return target;
	}
	
}
