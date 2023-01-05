package com.dnt.cloud.layui.web.i18n;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.dnt.cloud.common.domain.result.NrcDataResult;
import com.dnt.cloud.common.domain.result.NrcResult;

@Component("i18n")
public class I18nHandler {
	private static Logger logger = LoggerFactory.getLogger(I18nHandler.class);

	private MessageSource messageSource;
	@Value("${spring.messages.basename:i18n/mess}")
	private String basename;

	@Value("${spring.messages.cache-seconds:-1}")
	private long cacheMillis;

	@Value("${spring.messages.encoding:UTF-8}")
	private String encoding;

	/**
	 * 初始化
	 * 
	 * @return
	 */
	private MessageSource initMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		logger.info("baseName====>:" + this.basename);
		messageSource.setBasename(basename);
		messageSource.setDefaultEncoding(encoding);
		messageSource.setCacheMillis(cacheMillis);
		return messageSource;
	}

	/**
	 * 设置当前的返回信息
	 * 
	 * @param request
	 * @param code
	 * @return
	 */
	public String getMessage(HttpServletRequest request, String code) {
		if (messageSource == null) {
			messageSource = initMessageSource();
		}
		String lauage = request.getHeader("Accept-Language");
		// 默认没有就是请求地区的语言
		Locale locale = null;
		if (lauage == null) {
			locale = request.getLocale();
		} else if ("en-US".equals(lauage)) {
			locale = Locale.ENGLISH;
		} else if ("zh-CN".equals(lauage)) {
			locale = Locale.CHINA;
		} else {
			// 其余的不正确的默认就是本地的语言
			locale = request.getLocale();
		}
		String result = null;
		try {
			result = messageSource.getMessage(code, null, locale);
		} catch (NoSuchMessageException e) {
			logger.error("Cannot find the error message of internationalization, return the original error message.");
		}
		if (result == null) {
			return code;
		}
		return result;
	}
	
	/***
	 * 封装统一的返回信息转换方法
	 * @param <T>
	 * @param result
	 * @param object
	 * @return
	 */
	public <T> Object i18nHandler2Message(
			HttpServletRequest request,
			NrcDataResult<?> result, 
			NrcDataResult<List<T>> result1,
			NrcResult result2) {
		if(result != null){
			result.setDescription(this.getMessage(request, result.getDescription()));
			result.setMessage(this.getMessage(request, result.getMessage()));
			return result;
		}
		if(result1 != null){
			result1.setDescription(this.getMessage(request, result1.getDescription()));
			result1.setMessage(this.getMessage(request, result1.getMessage()));
			return result1;
		}
		if(result2 != null){
			result2.setDescription(this.getMessage(request, result2.getDescription()));
			result2.setMessage(this.getMessage(request, result2.getMessage()));
			return result2;
		}
		return null;
	}

	/**
	 * 设置当前的返回信息
	 * @param language
	 * @param code
	 * @return
	 */
	public String getMessage(String language, String code) {
		if (messageSource == null) {
			messageSource = initMessageSource();
		}
		// 默认没有就是请求地区的语言
		Locale locale = null;
		
		Locale[] localeList = Locale.getAvailableLocales();
		for(Integer i=0;i<localeList.length;i++){
			if(localeList[i].getLanguage().equals(language.split("-")[0])){
				locale = localeList[i];
			}
		}
		if(locale == null){
			locale = Locale.ENGLISH;
		}
		String result = null;
		try {
			result = messageSource.getMessage(code, null, locale);
		} catch (NoSuchMessageException e) {
			logger.error("Cannot find the error message of internationalization, return the original error message.");
		}
		if (result == null) {
			return code;
		}
		return result;
	}
	
	/**
	 * 
	 * 设置当前的返回信息
	 * 用于：后台传到前端的文字显示
	 * @param language
	 * @param code
	 * @return
	 */
	public String getMessageByBackStage(String language, String code) {
		if (messageSource == null) {
			messageSource = initMessageSource();
		}
		// 默认没有就是请求地区的语言
		Locale locale = null;
		logger.info("language===>{}", language);
		if (StringUtils.isNotBlank(language)) {
			Locale[] localeList = Locale.getAvailableLocales();
			for(Integer i=0;i<localeList.length;i++){
				if(localeList[i].getLanguage().equals(language.split("-")[0])){
					locale = localeList[i];
				}
			}
		}
		if(locale == null){
			locale = Locale.ENGLISH;
		}
		String result = null;
		try {
			result = messageSource.getMessage(code, null, locale);
		} catch (NoSuchMessageException e) {
			logger.error("Cannot find the error message of internationalization, return the original error message.");
		}
		if (result == null) {
			return code;
		}
		return result;
	}
	
	/**
	 * 返回当前国家的时间
	 * @author julio
	 * @date 2019年2月25日
	 * @param timeZone
	 * @return
	 */
	public Date getCountryDate(String timeZone){
		try {
			logger.info("======当前请求的时区=====:{}",timeZone);
			StringBuffer sb = new StringBuffer();
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
			//1. 然后把时间字符串转换成日期类型
			int month = calendar.get(Calendar.MONTH)+1;
			sb.append(calendar.get(Calendar.YEAR));
			sb.append("-");
			sb.append(month < 10 ? "0" + month : month);
			sb.append("-");
			sb.append(calendar.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + calendar.get(Calendar.DAY_OF_MONTH) : calendar.get(Calendar.DAY_OF_MONTH));
			sb.append(" ");
			sb.append(calendar.get(Calendar.HOUR_OF_DAY) < 10 ? "0" + calendar.get(Calendar.HOUR_OF_DAY) : calendar.get(Calendar.HOUR_OF_DAY));
			sb.append(":");
			sb.append(calendar.get(Calendar.MINUTE) < 10 ? "0" + calendar.get(Calendar.MINUTE) : calendar.get(Calendar.MINUTE));
			sb.append(":");
			sb.append(calendar.get(Calendar.SECOND) < 10 ? "0" + calendar.get(Calendar.SECOND) : calendar.get(Calendar.SECOND));
			logger.info("{}:当前时间:{}",timeZone,sb.toString());
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = null;
			if ((sb.toString() != null) && (sb.toString().length() == "yyyy-MM-dd HH:mm:ss".length())) {
				try {
					d = dateFormat.parse(sb.toString());
				} catch (ParseException ex) {
					return null;
				}
			}
			return d;
		} catch (Exception e) {
			logger.info("获取当前国家时区异常:{}",e);
			return new Date();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new I18nHandler().getCountryDate("Asia/Shanghai"));
//		 String[] timeZoneIDList = TimeZone.getAvailableIDs();
//	        //List of Time Zones
//	        for(String timeZoneID:timeZoneIDList){
//	            System.out.println(timeZoneID);
//	        }
//	        //What time is it in Fiji
//	        Calendar fijiCalendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
//	        System.out.println("Time in Fiji->"+fijiCalendar.get(Calendar.HOUR_OF_DAY)+":"+fijiCalendar.get(Calendar.MINUTE));
	
		Date payDate = new I18nHandler().getCountryDate("Asia/Almaty");
		 SimpleDateFormat formatter = new SimpleDateFormat("HH");
		 String dateString = formatter.format(payDate);
		 System.err.println(dateString);
		 if(dateString.equals("5")){
			 System.err.println("111111111");
		 }
	
	}
}
