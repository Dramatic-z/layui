package com.dnt.cloud.cache.config;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisConfigProperties {

	// normal
	@Value("${nrc.cache.redis.dbIndex:10}")
	int dbIndex = 0;
	@Value("${nrc.cache.redis.password:wanwuganzhi@dnt}")
	String password;
	@Value("${nrc.cache.redis.timeout:2000}")
	int timeout = 2000;

	// cluster
	@Value("${nrc.cache.redis.clusterNodes:106.14.220.4:6379}")
	List<String> clusterNodes;
	int clusterMaxRedirects = 1;

	// sentinel
	String sentinelMaster = "master";
	Set<String> sentinelNodes;

	public int getDbIndex() {
		return dbIndex;
	}

	public void setDbIndex(int dbIndex) {
		this.dbIndex = dbIndex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public List<String> getClusterNodes() {
		return clusterNodes;
	}

	public void setClusterNodes(List<String> clusterNodes) {
		this.clusterNodes = clusterNodes;
	}

	public int getClusterMaxRedirects() {
		return clusterMaxRedirects;
	}

	public void setClusterMaxRedirects(int clusterMaxRedirects) {
		this.clusterMaxRedirects = clusterMaxRedirects;
	}

	public String getSentinelMaster() {
		return sentinelMaster;
	}

	public void setSentinelMaster(String sentinelMaster) {
		this.sentinelMaster = sentinelMaster;
	}

	public Set<String> getSentinelNodes() {
		return sentinelNodes;
	}

	public void setSentinelNodes(Set<String> sentinelNodes) {
		this.sentinelNodes = sentinelNodes;
	}
}
