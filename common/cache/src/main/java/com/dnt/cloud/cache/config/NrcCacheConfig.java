package com.dnt.cloud.cache.config;

import static org.springframework.util.StringUtils.split;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dnt.cloud.cache.message.AbstractMessageReceiver;

@Configuration
@ComponentScan(basePackages = "com.dnt.cloud.cache")
@EnableCaching
public class NrcCacheConfig extends CachingConfigurerSupport {

	private static Logger logger = LoggerFactory.getLogger(NrcCacheConfig.class);

	@Resource
	RedisConfigProperties redisConfigProperties;

	@Bean
	public RedisConnectionFactory jedisConnectionFactory() {
		logger.info("==============RedisConnectionFactory 初始化开始==============");
		JedisConnectionFactory redisConnectionFactory = null;
		// 有Sentinel配置时优先以Sentinel连接
		if (!StringUtils.isEmpty(redisConfigProperties.getSentinelMaster())
				&& !CollectionUtils.isEmpty(redisConfigProperties.getSentinelNodes())) { // sentinel
			RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration(
					redisConfigProperties.getSentinelMaster(), redisConfigProperties.getSentinelNodes());
			logger.info("sentinel: {}, {}", redisConfigProperties.getSentinelMaster(),
					redisConfigProperties.getSentinelNodes());
			redisConnectionFactory = new JedisConnectionFactory(sentinelConfig);
		} else {
			List<String> clusterNodes = redisConfigProperties.getClusterNodes();
			Assert.notEmpty(redisConfigProperties.getClusterNodes(), "请配置nrc.cache.redis.clusterNodes");

			if (clusterNodes.size() > 1) { // 多个node时cluster连接
				RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration(
						redisConfigProperties.getClusterNodes());
				clusterConfig.setMaxRedirects(redisConfigProperties.getClusterMaxRedirects());
				logger.info("cluster: {}, {}", redisConfigProperties.getClusterMaxRedirects(),
						redisConfigProperties.getClusterNodes());
				redisConnectionFactory = new JedisConnectionFactory(clusterConfig);
			} else {// 单个多个node时normal
				String[] args = split(clusterNodes.get(0), ":");
				Assert.notNull(args, "Host和Port需要用':'分开.");
				Assert.isTrue(args.length == 2, "Host和Port需要用':'分开.");

				redisConnectionFactory = new JedisConnectionFactory();
				logger.info("host:{}", args[0]);
				redisConnectionFactory.setHostName(args[0]);
				logger.info("port:{}", args[1]);
				redisConnectionFactory.setPort(Integer.valueOf(args[1]).intValue());
				logger.info("dbindex:{}", redisConfigProperties.getDbIndex());
				redisConnectionFactory.setDatabase(redisConfigProperties.getDbIndex());
				logger.info("pw:{}", redisConfigProperties.getPassword());
				redisConnectionFactory.setPassword(redisConfigProperties.getPassword());
				logger.info("timeout:{}", redisConfigProperties.getTimeout());
				redisConnectionFactory.setTimeout(redisConfigProperties.getTimeout());
			}
		}
		logger.info("==============RedisConnectionFactory 初始化结束==============");
		return redisConnectionFactory;
	}

	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Serializable> redisTemplate() {
		RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());

		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);

		GenericJackson2JsonRedisSerializer vs = new GenericJackson2JsonRedisSerializer();
		redisTemplate.setValueSerializer(vs);
		redisTemplate.setHashValueSerializer(vs);

		return redisTemplate;
	}

	@Bean(name = "stringRedisTemplate")
	public StringRedisTemplate stringRedisTemplate() {
		return new StringRedisTemplate(jedisConnectionFactory());
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate<String, Serializable> redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(300);
		cacheManager.setUsePrefix(true);
		return cacheManager;
	}

	@Autowired(required = false)
	List<AbstractMessageReceiver<?>> receivers;

	@Bean
	public RedisMessageListenerContainer container(RedisConnectionFactory jedisConnectionFactory) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(jedisConnectionFactory);
		if (!CollectionUtils.isEmpty(receivers)) {
			receivers.forEach(receiver -> {
				List<Topic> topics = new ArrayList<>();
				Assert.hasText(receiver.getChannels(), "消息渠道不能为空");
				String[] channels = receiver.getChannels().split(",");
				for (String channelName : channels) {
					topics.add(new ChannelTopic(StringUtils.trimWhitespace(channelName)));
				}
				receiver.setSerializer(new GenericJackson2JsonRedisSerializer());
				container.addMessageListener(receiver, topics);
			});
		}
		return container;
	}

	@Override
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object o, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				// sb.append(o.getClass().getPackage().getName());
				// sb.append(":");
				// sb.append(o.getClass().getSimpleName());
				// sb.append(".");
				sb.append(method.getName());
				sb.append("(");
				sb.append(StringUtils.arrayToCommaDelimitedString(params));
				sb.append(")");
				return sb.toString();
				// return CacheConstant.getKey(params);
			}
		};
	}

}