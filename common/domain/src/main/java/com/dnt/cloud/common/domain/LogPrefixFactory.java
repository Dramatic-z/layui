package com.dnt.cloud.common.domain;

import java.util.UUID;

public class LogPrefixFactory {

	private static final ThreadLocal<String> LOCAL_LOG_PREFIX = new ThreadLocal<String>();

	public static final String getLogPrefix() {
		if (LOCAL_LOG_PREFIX.get() == null) {
			LOCAL_LOG_PREFIX.set(String.format("====%s====", UUID.randomUUID().getMostSignificantBits()));
		}
		return LOCAL_LOG_PREFIX.get();
	}

}
