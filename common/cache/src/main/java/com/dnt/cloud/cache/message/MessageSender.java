package com.dnt.cloud.cache.message;

import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 消息发送器
 * 
 * @author fenghonggang
 */
@Component("messageSender")
public class MessageSender {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	RedisTemplate<String, Object> redisTemplate;

	/**
	 * @param message 消息
	 * @param channel 发送消息的通道，例：open.msg.channel1
	 */
	public void sendMessage(Object message, String channel) {
		String logPrefix = String.format("====%s====", UUID.randomUUID().getMostSignificantBits());
		try {
			logger.info("{} 开始发送消息: channel={}, message={}", logPrefix, channel, message);
			redisTemplate.convertAndSend(channel, message);
			logger.info("{} 结束发送消息", logPrefix);
		} catch (Exception e) {
			logger.error(logPrefix + " 消息发送异常", e);
		}
	}

}
