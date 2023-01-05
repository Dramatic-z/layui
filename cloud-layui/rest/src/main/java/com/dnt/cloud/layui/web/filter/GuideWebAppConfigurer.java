package com.dnt.cloud.layui.web.filter;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Component
//@EnableWebMvc //不能加入这个注解否则不执行自定义的converter
public class GuideWebAppConfigurer extends WebMvcConfigurerAdapter {

	@Resource(name = "behaviorInterceptor")
	private GuideInterceptor behaviorInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(behaviorInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/user/login","/user/test/logonuser","/user/logout"
						,"/static/layuiadmin/**","/obtainRelatedDataOut/*","/addRelatedDataOut/*","/install/*"
						,"/systemSetup/syncStorePoint","/market/initMarketDataMatching","/weather/*",
						"/layMenu/*","/rate/*",
						"/test/*");
		
		
		// 排除拦截目录 /extinterface/** 两个星“*”表示 该目录下及其子目录 全部涵盖
		//"/stores/*","/activity/*","/device/*","/pdAply/*","/simditor/*",
		//"/pdNotice/*","/pdqa/*","/brand/*","/category/*",
		super.addInterceptors(registry);
	}
}
