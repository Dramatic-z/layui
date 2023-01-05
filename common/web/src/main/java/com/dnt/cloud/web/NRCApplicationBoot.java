package com.dnt.cloud.web;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import feign.auth.BasicAuthRequestInterceptor;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = { "com.dnt.cloud.web", "com.dnt.cloud.logger" })
public abstract class NRCApplicationBoot extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}

	public static ConfigurableApplicationContext run(Class<?> clazz, String[] args) {
		Logger logger = LoggerFactory.getLogger(clazz);

		ConfigurableApplicationContext context = SpringApplication.run(clazz, args);

		Environment env = context.getEnvironment();
		String appName = env.getProperty("spring.application.name");
		String port = env.getProperty("server.port");
		String contextPath = env.getProperty("server.context-path");
		String hostAddress;
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
			String localInfo = "\n-----------------------------------------------------------------------\n\tApplication '{}' is running! Access URLs:\n\tLocal: \thttp://127.0.0.1:{}{}\n\tExternal: \thttp://{}:{}{}\n-----------------------------------------------------------------------";
			logger.info(localInfo, appName, port, contextPath, hostAddress, port, contextPath);
		} catch (Exception e) {
			logger.error("启动异常", e);
		}
		return context;
	}

	@LoadBalanced
	@Bean
	RestTemplate restTemplateLB() {
		return new RestTemplate();
	}

	@Primary
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor(@Value("${security.user.name:}") String username,
			@Value("${security.user.password:}") String password) {
		return new BasicAuthRequestInterceptor(username, password);
	}
}
