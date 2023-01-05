package com.dnt.cloud.layui.service;

import com.dnt.cloud.common.domain.result.PageData;
import com.dnt.cloud.layui.domain.LayMenuDomain;
import com.dnt.cloud.layui.domain.NrcActivityDomain;
import com.dnt.cloud.layui.query.LayMenuQuery;
import com.dnt.cloud.layui.query.NrcActivityQuery;

public interface LayMenuService {

	PageData<LayMenuDomain> getLayMenuByCondition(LayMenuQuery query);

	LayMenuDomain saveLayMenu(LayMenuDomain domain);

	boolean deleteLayMenuById(Long id);

	LayMenuDomain queryLayMenuById(Long id);

	LayMenuDomain updateLayMenuy(LayMenuDomain domain);
}
