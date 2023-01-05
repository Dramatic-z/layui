package com.dnt.cloud.layui.service.impl;

import com.dnt.cloud.common.domain.result.PageData;
import com.dnt.cloud.layui.dao.LayMenuDAO;
import com.dnt.cloud.layui.domain.LayMenuDomain;
import com.dnt.cloud.layui.model.LayMenu;
import com.dnt.cloud.layui.model.LayMenuCriteria;
import com.dnt.cloud.layui.query.LayMenuQuery;
import com.dnt.cloud.layui.service.LayMenuService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("layMenuService")
public class LayMenuServiceImpl implements LayMenuService {
	
	@Resource
	private LayMenuDAO layMenuDAO;

	@Override
	public PageData<LayMenuDomain> getLayMenuByCondition(LayMenuQuery query) {
		LayMenuCriteria example = new LayMenuCriteria();
		LayMenuCriteria.Criteria criteria = example.createCriteria();

		if (query.getStatus()!=null) {
			criteria.andStatusEqualTo(query.getStatus());
		}
		PageHelper.startPage(query.getPageNum(), query.getPageSize(),query.getOrderBy());
		Page<LayMenu> page = (Page<LayMenu>) layMenuDAO.selectByExample(example);
		if (CollectionUtils.isEmpty(page)) {
			return null;
		}
		List<LayMenuDomain> list = new ArrayList<LayMenuDomain>();
		page.forEach(data -> {
			list.add(this.converNADomain(data));
		});
		return new PageData<LayMenuDomain>(list, page.getTotal(),page.getPageNum(), page.getPageSize());
	}

	@Override
	public LayMenuDomain saveLayMenu(LayMenuDomain domain) {
		LayMenu menu = this.converNAModel(domain);
		int res = layMenuDAO.insertSelective(menu);
		return res == 1 ? this.converNADomain(menu) : null;
	}

	@Override
	public boolean deleteLayMenuById(Long id) {
		if (id == null) {
			Assert.isNull("id 不能为空");
		}
		int result = layMenuDAO.deleteByPrimaryKey(id);
		return result == 1 ? true : false;
	}

	@Override
	public LayMenuDomain queryLayMenuById(Long id) {
		if (id == null) {
			Assert.isNull("id 不能为空");
		}
		LayMenu menu = layMenuDAO.selectByPrimaryKey(id);
		return this.converNADomain(menu);
	}

	@Override
	public LayMenuDomain updateLayMenuy(LayMenuDomain domain) {
		LayMenu menu = this.converNAModel(domain);
		int res = layMenuDAO.updateByPrimaryKeySelective(menu);
		return res == 1 ? this.converNADomain(menu) : null;
	}

	private LayMenuDomain converNADomain(LayMenu source) {
		if (source == null) {
			return null;
		}
		LayMenuDomain target = new LayMenuDomain();
		BeanUtils.copyProperties(source, target);
		return target;
	}

	private LayMenu converNAModel(LayMenuDomain source) {
		if (source == null) {
			return null;
		}
		LayMenu target = new LayMenu();
		BeanUtils.copyProperties(source, target, "gmtCreate", "gmtModify");
		return target;
	}
}
