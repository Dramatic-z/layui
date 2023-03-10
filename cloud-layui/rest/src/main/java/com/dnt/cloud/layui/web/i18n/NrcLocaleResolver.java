package com.dnt.cloud.layui.web.i18n;

import java.util.Locale;  
  


import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
  


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;  
  
/** 
 * 自定义国际化语言解析器 
 * 
 */  
public class NrcLocaleResolver implements LocaleResolver{
      
    private static final String I18N_LANGUAGE = "l";  
    private static final String I18N_LANGUAGE_SESSION = "i18n_l";  
    private Logger logger = LoggerFactory.getLogger(this.getClass());
  
    @Override  
    public Locale resolveLocale(HttpServletRequest req) {
        String i18n_language = req.getParameter(I18N_LANGUAGE);
        Locale locale = Locale.SIMPLIFIED_CHINESE; 
		if (!StringUtils.isEmpty(i18n_language)) {
            String[] language = i18n_language.split("_");  
            if(language!=null&&language.length==2){
            	locale = new Locale(language[0], language[1]);  
            }else{
            	locale = new Locale(language[0]);  
            }
            //将国际化语言保存到session  
            HttpSession session = req.getSession();  
            logger.info("当前国际化语言编码为--》{}",locale);
            session.setAttribute(I18N_LANGUAGE_SESSION, locale);
        }else {
            //如果没有带国际化参数，则判断session有没有保存，有保存，则使用保存的，也就是之前设置的，避免之后的请求不带国际化参数造成语言显示不对  
            HttpSession session = req.getSession();
            Locale localeInSession = (Locale) session.getAttribute(I18N_LANGUAGE_SESSION);  
            if(localeInSession != null) {
                locale = localeInSession;  
            }  
        }  
        return locale;  
    }  
  
    @Override  
    public void setLocale(HttpServletRequest req, HttpServletResponse res, Locale locale) {  
          
    }  
  
}  