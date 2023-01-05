package com.dnt.cloud.layui.web.common;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.dnt.cloud.common.domain.result.NrcDataResult;
import com.dnt.cloud.common.lang.diagnostic.Profiler;
import com.dnt.cloud.core.common.exceptions.ValidateException;
import com.dnt.cloud.web.common.BaseController;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

public abstract class BaseAction extends BaseController {
	
	public static final String LOG_PREFIX = "logPrefix";
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/** 获取模板，使页面可以使用java静态类 **/
	@SuppressWarnings("deprecation")
	private final static BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
	private final static TemplateHashModel staticModels = wrapper.getStaticModels();

	/**
	 * 向页面设置工具类的全路径，以适应页面使用java方法类需求
	 * @param map
	 */
	protected void setStaticClassToPage(ModelMap map) {
		// register 静态访问
		map.put("JDKEnumUtil", useStaticPacker("com.dnt.cloud.biz.common.util.JDKEnumUtil"));
	}
	
	/**
	 * 
	 * @param packname
	 * @return
	 */
	protected static TemplateHashModel useStaticPacker(String packname) {
		TemplateHashModel fileStatics = null;
		try {
			fileStatics = (TemplateHashModel) staticModels.get(packname);
		} catch (TemplateModelException e) {
			e.printStackTrace();
		}
		return fileStatics;
	};

	public String getCurrentKey(HttpServletRequest request) {
		String t = IPAddressUtil.getIpAddr(request);
		Cookie[] cs = request.getCookies();
		if (cs == null)
			return null;
		List<Cookie> cookies = Arrays.asList(cs);
		String cv = "";
		for (Cookie c : cookies) {
			if (c.getName().equalsIgnoreCase(NrcConstants.LoginCookieName)) {
				cv = c.getValue();
				break;
			}
		}
		if (StringUtils.isBlank(cv))
			return null;
		return t.concat(cv);
	}

	public abstract class ActionTemplate {

		protected String resultMessage;

		public ModelAndView process(ModelAndView mv) {
			MDC.put(LOG_PREFIX,
					String.format("%s-%s", processorName(), UUID.randomUUID()
							.toString().replace("-", "")));
			Profiler.start();
			try {
				logger.info("请求={}", getLogContent());
				validate(mv);
				mv = doProcess(mv);
			} catch (IllegalArgumentException e) {
				logger.error("校验异常", e);
			} catch (ManagerServiceException e) {
				logger.error("页面异常", e);
			} catch (Throwable e) {
				logger.error("页面异常", e);
			} finally {
				Profiler.release();
				logger.info("响应(耗时:" + Profiler.getDuration() + "ms) {}",
						resultMessage);
				if (Profiler.getDuration() > 3000) {
					logger.info("时间分析: {}", Profiler.dump());
				}
				Profiler.reset();
				MDC.clear();
			}
			return mv;
		}

		/** 处理器名称 */
		protected abstract String processorName();


		/** 日志内容 */
		protected abstract String getLogContent();

		/**
		 * 校验，不通过时需要setViewName
		 * 
		 * @see ValidateException
		 */
		protected void validate(ModelAndView mv)
				throws IllegalArgumentException {
		}

		/** 执行核心，返回处理结果说明 */
		protected abstract ModelAndView doProcess(ModelAndView mv);

	}

	public abstract class JsonOperateTemplate<Resp> {

		public String process() {
			MDC.put(LOG_PREFIX,
					String.format("%s-%s", processorName(), UUID.randomUUID()
							.toString().replace("-", "")));
			Profiler.start();
			NrcDataResult<?> result = null;
			try {
				logger.info("请求={}", getLogContent());
				result = NrcDataResult.success(doProcess());
			} catch (IllegalArgumentException e) {
				result = NrcDataResult.fail(e.getMessage());
			} catch (ManagerServiceException e) {
				logger.error("Json请求异常", e);
				result = NrcDataResult.fail(e.getMessage());
			} catch (Throwable e) {
				logger.error("Json请求异常", e);
				result = NrcDataResult.fail(e.getMessage());
			} finally {
				Profiler.release();
				logger.info("响应(耗时:" + Profiler.getDuration() + "ms) {}",
						result);
				if (Profiler.getDuration() > 3000) {
					logger.info("时间分析: {}", Profiler.dump());
				}
				Profiler.reset();
				MDC.clear();
			}
			return JSON.toJSONString(result);
		}

		/** 处理器名称 */
		protected abstract String processorName();

		/** 日志内容 */
		protected abstract String getLogContent();

		/**
		 * 校验
		 * 
		 * @see ValidateException
		 */
		protected void validate() throws IllegalArgumentException {
		}

		/** 执行核心，返回处理结果说明 */
		protected abstract Resp doProcess();

	}

	protected static boolean fileTypeIsImg(String fileType) {
		String[] imgTypeList = { ".jpg", ".gif", ".bmp", ".png", ".jpeg" };
		for (String imgType : imgTypeList) {
			if (fileType.equalsIgnoreCase(imgType))
				return true;
		}
		return false;
	}

	protected void downloadSmallFile(HttpServletRequest req,
			HttpServletResponse resp, byte[] bytes, String fileName)
			throws IOException {
		OutputStream fos = resp.getOutputStream();
		// resp.setHeader("Content-Type", "application/octet-stream");
		resp.setContentType("vnd.ms-excel;charset=ISO-8859-1");
		/*
		 * String fileNameParam = req.getHeader("User-Agent").indexOf("Firefox")
		 * != -1 ? convertString4FireFox(fileName) : convertString(fileName);
		 */
		resp.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\";");
		resp.setContentType("application/octet-stream");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}

}