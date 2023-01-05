package com.dnt.cloud.web.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.dnt.cloud.common.domain.result.NrcDataResult;
import com.dnt.cloud.common.domain.result.NrcResult;
import com.dnt.cloud.common.domain.result.PageData;
import com.dnt.cloud.common.domain.result.ResultCode;
import com.dnt.cloud.web.editor.DateEditor;
import com.dnt.cloud.web.editor.StringTrimEditor;

public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@InitBinder
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
		binder.registerCustomEditor(String.class, new StringTrimEditor());
		binder.registerCustomEditor(String[].class, new StringTrimEditor());
	}

	protected <D> NrcDataResult<PageData<D>> nrcDataResultPage(PageData<D> pageDate) {
		if (pageDate == null || CollectionUtils.isEmpty(pageDate.getList())) {
			return new NrcDataResult<PageData<D>>(ResultCode.NOT_EXIST);
		} else {
			return new NrcDataResult<PageData<D>>(ResultCode.SUCCESS, pageDate);
		}
	}

	protected <D> NrcDataResult<List<D>> nrcDataResultList(PageData<D> pageDate) {
		if (pageDate == null || CollectionUtils.isEmpty(pageDate.getList())) {
			return new NrcDataResult<List<D>>(ResultCode.NOT_EXIST);
		} else {
			return new NrcDataResult<List<D>>(ResultCode.SUCCESS, pageDate.getList());
		}
	}

	protected <D> NrcDataResult<List<D>> nrcDataResultList(List<D> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new NrcDataResult<List<D>>(ResultCode.NOT_EXIST);
		} else {
			return new NrcDataResult<List<D>>(ResultCode.SUCCESS, list);
		}
	}

	protected <D> NrcDataResult<D> nrcDataResultDomain(D domain) {
		if (domain == null) {
			return new NrcDataResult<D>(ResultCode.NOT_EXIST);
		} else {
			return new NrcDataResult<D>(ResultCode.SUCCESS, domain);
		}
	}

	protected NrcResult nrcResult(boolean success) {
		if (success) {
			return new NrcResult(ResultCode.SUCCESS);
		} else {
			return new NrcResult(ResultCode.FAIL);
		}
	}

}
