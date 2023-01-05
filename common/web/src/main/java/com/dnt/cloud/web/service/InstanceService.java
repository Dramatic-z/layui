package com.dnt.cloud.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class InstanceService {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Value("${spring.application.name:}")
	private String serviceName;

	@Value("${server.context-path:/}")
	private String contextPath;

	@Value("${server.port:8080}")
	private String port;

	/**
	 * @return 当前服务实例列表
	 */
	public List<ServiceInstance> getServiceInstances() {
		return this.discoveryClient.getInstances(serviceName);
	}

	/**
	 * @param serviceName
	 * @return 服务实例列表
	 */
	public List<ServiceInstance> getServiceInstancesByServiceName(String serviceName) {
		return this.discoveryClient.getInstances(serviceName);
	}

	/**
	 * @return 当前服务url列表
	 */
	public List<String> getServiceUrls() {
		List<ServiceInstance> instances = this.getServiceInstances();

		List<String> uris = new ArrayList<>();
		if (!CollectionUtils.isEmpty(instances)) {
			instances.forEach(instance -> {
				uris.add(instance.getUri().toString() + this.contextPath);
			});
		}
		return uris;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getContextPath() {
		return contextPath;
	}

	public String getPort() {
		return port;
	}
}
