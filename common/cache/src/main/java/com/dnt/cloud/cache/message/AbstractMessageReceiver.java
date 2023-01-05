package com.dnt.cloud.cache.message;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 
 * 消息接收器
 * 
 * 注： 1. 子类中实现handle方法或重写handleMessage 2. 初始化为spring bean
 * 
 * @author fenghonggang
 */
public abstract class AbstractMessageReceiver<M> extends MessageListenerAdapter {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 消息入口
	 * 
	 * @param message
	 * @param channel
	 */
	public void handleMessage(M message, String channel) {
		String logPrefix = String.format("====%s====", UUID.randomUUID().getMostSignificantBits());
		logger.info("{} 接收到来自[{}]的消息: {}", logPrefix, channel, message);
		try {
			logger.info("{} 开始处理消息", logPrefix);
			this.handle(message, logPrefix);
			logger.info("{} 结束处理消息", logPrefix);
		} catch (Exception e) {
			logger.error(logPrefix + " 消息处理异常", e);
		}
	}

	/**
	 * 指定接收哪些通道的消息，可多个，多个时用逗号","隔开
	 * 
	 * 例: return "open.msg.channel1,open.msg.channel2";
	 */
	public abstract String getChannels();

	/**
	 * 消息处理，子类中实现
	 * 
	 * @param message
	 * @param logPrefix
	 * @throws Exception
	 */
	protected abstract void handle(M message, String logPrefix) throws Exception;
}
