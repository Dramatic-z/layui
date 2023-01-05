package com.dnt.cloud.layui.web.filter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component("behaviorInterceptor")
public class GuideInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(getClass());


	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (true) {
			/*HttpSession session=request.getSession(false);
			session.setMaxInactiveInterval(30*60);*/
			return true;
		} else {
			String ajaxHeader = request.getHeader("X-Requested-With");// ajax请求特有的请求头
			if("XMLHttpRequest".equals(ajaxHeader)){
				logger.info("用户失效   ajax请求路径-->{}",request.getRequestURL());
				response.setStatus(401);
			}else{
			//	logger.info("用户失效   请求路径-->{}",request.getRequestURL());
				response.sendRedirect(request.getContextPath() + "/user/login");
			}
//			logger.info("返回登录页面");
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}
