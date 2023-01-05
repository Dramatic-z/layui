package com.dnt.cloud.layui.web.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
  
//使用WebMvcConfigurerAdapter可以扩展SpringMvc的功能，包括拦截器，转换器等  
//@EnableWebMvc //设置@EnableWebMvc为完全接管SpringMvc，但一般不要设置完全接管SpringMvc  
@Configuration
public class NrcMvcConfig extends WebMvcConfigurerAdapter {
  
    /** 
     * 配置自己的国际化语言解析器 
     * @return 
     */  
    @Bean  
    public LocaleResolver localeResolver(){
		return new NrcLocaleResolver();
	}
  
    /** 
     * 配置自己的拦截器 
     */  
    @Override  
    public void addInterceptors(InterceptorRegistry registry) {
        //super.addInterceptors(registry);  
    }  
      
      
} 