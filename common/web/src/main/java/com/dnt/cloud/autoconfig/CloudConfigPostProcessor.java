package com.dnt.cloud.autoconfig;

import java.io.IOException;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StringUtils;

/**
 * 从公共外部文件加载spring.cloud.config.*配置
 * 
 * http://stackoverflow.com/questions/37888231/how-to-manage-spring-cloud-
 * bootstrap-properties-in-a-shared-library
 * 
 * @author fenghonggang
 */
public class CloudConfigPostProcessor implements EnvironmentPostProcessor, Ordered {

	private static final String DEFAULT_CONFIG_ROOT = "/opt/config/dnt";
	private static final String DEFAULT_CONFIG_NAME = "cloud-config";

	private PropertiesPropertySourceLoader loader;

	public CloudConfigPostProcessor() {
		loader = new PropertiesPropertySourceLoader();
	}

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment env, SpringApplication application) {
		// the bootstrap file is only loaded in the bootstrap context
		if (env.getPropertySources().contains("bootstrap")) {
			// 配置路径
			String configRoot = env.getProperty("config.root");
			if (!StringUtils.hasText(configRoot)) {
				configRoot = DEFAULT_CONFIG_ROOT;
			}
			if (!configRoot.endsWith("/") && !configRoot.endsWith("\\")) {
				configRoot += "/";
			}

			String cloudConfigName = env.getProperty("config.name"); // 配置文件名
			if (!StringUtils.hasText(cloudConfigName)) {
				cloudConfigName = DEFAULT_CONFIG_NAME;
			}

			// 设置本地配置文件地址
			this.setConfigLocation(configRoot, env);

			// 设置云配置地址
			String fileName = cloudConfigName + ".properties";
			String filePath = configRoot + fileName;
			this.loadProfile(filePath, fileName, env);
			for (String profile : env.getActiveProfiles()) {
				fileName = cloudConfigName + (profile != null ? "-" + profile : "") + ".properties";
				filePath = configRoot + fileName;
				this.loadProfile(filePath, fileName, env);
			}
		}
	}

	private void loadProfile(String filePath, String fileName, ConfigurableEnvironment env) {
		try {
			PropertySource<?> propertySource = loader.load(fileName, new FileSystemResource(filePath), null);
			if (propertySource != null) {
				env.getPropertySources().addFirst(propertySource);
			}
		} catch (IOException e) {
			// throw new RuntimeException(e);
			// do nothing
		}
	}

	private void setConfigLocation(String configRoot, ConfigurableEnvironment env) {
		Properties props = new Properties();
		props.put("spring.config.location",
				configRoot + "," + configRoot + env.getProperty("spring.application.name") + "/");
		props.put("spring.config.name", "application");
		env.getPropertySources().addFirst(new PropertiesPropertySource("local-config-location", props));
	}

	@Override
	public int getOrder() {
		// must go after ConfigFileApplicationListener
		return Ordered.HIGHEST_PRECEDENCE + 11;
	}
}
