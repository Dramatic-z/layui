package com.dnt.cloud.layui.web.controller;

import com.dnt.cloud.common.domain.result.PageData;
import com.dnt.cloud.layui.domain.NrcActivityDomain;
import com.dnt.cloud.layui.query.NrcActivityQuery;
import com.dnt.cloud.layui.service.ActivityService;
import com.dnt.cloud.layui.service.LayMenuService;
import com.dnt.cloud.layui.web.common.BaseAction;
import com.dnt.cloud.layui.web.utils.EscapeDomainUtils;
import com.dnt.cloud.layui.web.utils.LayuiReturnUtils;
import com.dnt.cloud.layui.web.utils.PictureUploadUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class LayMenuController extends BaseAction {
	
	@Resource
	private LayMenuService layMenuService;

	private final static String MAIN_MENU_PAGE = "/views/activity/activitytable";

	private final static String TEST_VUE_PAGE = "/views/activity/saveActivity";


	/**
	 * 跳转到查询菜单列表的内容页面
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/layMenu/mainMenuPage", method = { RequestMethod.GET })
	public ModelAndView toActivityListPage(ModelMap map,HttpSession session) {
		//查询相关内容
		return new ModelAndView(TEST_VUE_PAGE);
	}


	
	


	/**
	 * 添加活动图片或视频
	 * @param request
	 * @param file
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/activity/dealActivityPic", method = { RequestMethod.POST })
	public String dealActivityPic(HttpServletRequest request, MultipartFile file,HttpSession session) {
		String imageUrl = "";/*PictureUploadUtils.Uploadpic(file, "Aactivitypic", host, port, userName, password, root, access);*/
		return LayuiReturnUtils.LayuiReturnImageUrl(imageUrl);
	}
}
