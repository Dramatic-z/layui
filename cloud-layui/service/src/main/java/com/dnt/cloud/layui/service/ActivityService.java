package com.dnt.cloud.layui.service;

import com.dnt.cloud.common.domain.result.PageData;
import com.dnt.cloud.layui.domain.NrcActivityDomain;
import com.dnt.cloud.layui.query.NrcActivityQuery;

public interface ActivityService {

	PageData<NrcActivityDomain> getActivityByCondition(NrcActivityQuery query);

	NrcActivityDomain saveActivity(NrcActivityDomain domain);

	boolean deleteActivityById(Long id);

	NrcActivityDomain queryActivityById(Long id);

	NrcActivityDomain updateActivity(NrcActivityDomain domain);
}
