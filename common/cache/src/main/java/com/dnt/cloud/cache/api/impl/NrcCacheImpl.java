package com.dnt.cloud.cache.api.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.dnt.cloud.cache.api.NrcCache;
import com.dnt.cloud.cache.result.CacheResult;
import com.dnt.cloud.cache.result.ResultCode;

@Component("openCache")
public class NrcCacheImpl<V> implements NrcCache<V> {

	private static Logger logger = LoggerFactory.getLogger(NrcCacheImpl.class);

	protected RedisTemplate<String, V> redisTemplate;

	@Resource
	protected StringRedisTemplate stringRedisTemplate;

	public NrcCacheImpl(@Autowired RedisTemplate<String, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public RedisTemplate<String, V> getRedisTemplate() {
		return redisTemplate;
	}

	public StringRedisTemplate getStringRedisTemplate() {
		return stringRedisTemplate;
	}

	@Override
	public ResultCode put(String namespace, String key, V value) {
		if (value == null) {
			return ResultCode.VALUE_NOT_NULL;
		}

		try {
			logger.debug("开始添加缓存: namespace={}, key={}, value={}", namespace, key, value);
			this.redisTemplate.opsForValue().set(String.format("%s:%s", namespace, key), value);
			logger.debug("结束添加缓存: namespace={}, key={}, value={}", namespace, key, value);
			return ResultCode.SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResultCode.EXCEPTION;
		}
	}

	@Override
	public ResultCode put(String namespace, String key, V value, long expireSeconds) {
		if (value == null) {
			return ResultCode.VALUE_NOT_NULL;
		}
		if (expireSeconds <= 0) {
			this.put(namespace, key, value);
		}

		try {
			logger.debug("开始添加缓存: namespace={}, key={}, value={}, expireSeconds={}", namespace, key, value,
					expireSeconds);
			this.redisTemplate.opsForValue().set(String.format("%s:%s", namespace, key), value, expireSeconds,
					TimeUnit.SECONDS);
			logger.debug("结束添加缓存: namespace={}, key={}, value={}, expireSeconds={}", namespace, key, value,
					expireSeconds);
			return ResultCode.SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResultCode.EXCEPTION;
		}
	}

	@Override
	public ResultCode expire(String namespace, String key, long expireSeconds) {
		try {
			if (!this.hasKey(namespace, key)) {
				return ResultCode.DATA_NOT_EXSITS;
			}

			logger.debug("开始设置过期时间: namespace={}, key={}, seconds={}", namespace, key, expireSeconds);
			Boolean result = this.redisTemplate.expire(String.format("%s:%s", namespace, key), expireSeconds,
					TimeUnit.SECONDS);
			logger.debug("结束设置过期时间: namespace={}, key={}, seconds={}", namespace, key, expireSeconds);
			if (result) {
				return ResultCode.SUCCESS;
			} else {
				return ResultCode.FAIL;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResultCode.EXCEPTION;
		}
	}

	@Override
	public ResultCode expireAt(String namespace, String key, Date expireDate) {
		try {
			if (!this.hasKey(namespace, key)) {
				return ResultCode.DATA_NOT_EXSITS;
			}

			logger.debug("开始设置过期日期: namespace={}, key={}, expireDate={}", namespace, key, expireDate);
			Boolean result = this.redisTemplate.expireAt(String.format("%s:%s", namespace, key), expireDate);
			logger.debug("结束设置过期日期: namespace={}, key={}, expireDate={}", namespace, key, expireDate);
			if (result) {
				return ResultCode.SUCCESS;
			} else {
				return ResultCode.FAIL;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResultCode.EXCEPTION;
		}
	}

	@Override
	public CacheResult<Set<String>> namespaces() {

		Set<String> result = new TreeSet<>();
		try {
			logger.debug("开始加载缓存keys: *:*");
			Set<String> keys = this.redisTemplate.keys("*:*");
			logger.debug("结束加载缓存keys: *:*, result={}", keys);
			if (keys != null) {
				keys.forEach(key -> {
					result.add(key.substring(0, key.indexOf(":")));
				});
			}

			return new CacheResult<Set<String>>(ResultCode.SUCCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new CacheResult<Set<String>>(ResultCode.EXCEPTION, result);
		}
	}

	@Override
	public CacheResult<Set<String>> keys(String namespace) {
		Set<String> result = new TreeSet<>();
		try {
			logger.debug("开始加载缓存keys: namespace={}", namespace);
			Set<String> keys = this.redisTemplate.keys(String.format("%s:*", namespace));
			logger.debug("结束加载缓存keys: namespace={}, result={}", namespace, keys);
			if (keys != null) {
				keys.forEach(key -> {
					result.add(key.substring(key.indexOf(":") + 1));
				});
			}
			return new CacheResult<Set<String>>(ResultCode.SUCCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new CacheResult<Set<String>>(ResultCode.EXCEPTION, result);
		}
	}

	@Override
	public CacheResult<V> get(String namespace, String key) {
		try {
			logger.debug("开始获取缓存: namespace={}, key={}", namespace, key);
			V value = this.redisTemplate.opsForValue().get(String.format("%s:%s", namespace, key));
			logger.debug("结束获取缓存: namespace={}, key={}, 结果：value={}", namespace, key, value);
			if (value == null) {
				return new CacheResult<V>(ResultCode.DATA_NOT_EXSITS);
			} else {
				return new CacheResult<V>(ResultCode.SUCCESS, value);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new CacheResult<V>(ResultCode.EXCEPTION);
		}
	}

	@Override
	public CacheResult<String> getStr(String namespace, String key) {
		try {
			logger.debug("开始获取缓存: namespace={}, key={}", namespace, key);
			String value = this.stringRedisTemplate.opsForValue().get(String.format("%s:%s", namespace, key));
			logger.debug("结束获取缓存: namespace={}, key={}, 结果：value={}", namespace, key, value);
			if (value == null) {
				return new CacheResult<String>(ResultCode.DATA_NOT_EXSITS);
			} else {
				return new CacheResult<String>(ResultCode.SUCCESS, value);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new CacheResult<String>(ResultCode.EXCEPTION);
		}
	}

	@Override
	public CacheResult<Map<String, V>> mget(String namespace) {
		try {
			logger.debug("开始加载缓存keys: namespace={}", namespace);
			Set<String> keys = this.redisTemplate.keys(String.format("%s:*", namespace));
			logger.debug("结束加载缓存keys: namespace={}, result={}", namespace, keys);

			List<String> keyList = new ArrayList<>();
			keyList.addAll(keys);
			logger.debug("开始批量获取缓存: keyList={}", keyList);
			List<V> values = this.redisTemplate.opsForValue().multiGet(keyList);
			logger.debug("结束批量获取缓存: keyList={}, 结果：values={}", keyList, values);

			Map<String, V> result = new TreeMap<>();
			for (int i = 0; i < keys.size(); i++) {
				result.put(keyList.get(i), values.get(i));
			}

			return new CacheResult<Map<String, V>>(ResultCode.SUCCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new CacheResult<Map<String, V>>(ResultCode.EXCEPTION);
		}
	}

	@Override
	public CacheResult<Map<String, String>> mgetStr(String namespace) {
		try {
			logger.debug("开始加载缓存keys: namespace={}", namespace);
			Set<String> keys = this.redisTemplate.keys(String.format("%s:*", namespace));
			logger.debug("结束加载缓存keys: namespace={}, result={}", namespace, keys);

			List<String> keyList = new ArrayList<>();
			keyList.addAll(keys);
			logger.debug("开始批量获取缓存: keyList={}", keyList);
			List<String> values = this.stringRedisTemplate.opsForValue().multiGet(keyList);
			logger.debug("结束批量获取缓存: keyList={}, 结果：values={}", keyList, values);

			Map<String, String> result = new TreeMap<>();
			for (int i = 0; i < keys.size(); i++) {
				result.put(keyList.get(i), values.get(i));
			}

			return new CacheResult<Map<String, String>>(ResultCode.SUCCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new CacheResult<Map<String, String>>(ResultCode.EXCEPTION);
		}
	}

	@Override
	public Boolean hasKey(String namespace, String key) {
		return this.redisTemplate.hasKey(String.format("%s:%s", namespace, key));
	}

	@Override
	public ResultCode delete(String namespace, String key) {
		try {
			logger.debug("开始删除缓存: namespace={}, key={}", namespace, key);
			this.redisTemplate.delete(String.format("%s:%s", namespace, key));
			logger.debug("结束删除缓存: namespace={}, key={}", namespace, key);
			return ResultCode.SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResultCode.EXCEPTION;
		}
	}

	@Override
	public ResultCode mdelete(String namespace) {
		try {
			logger.debug("开始加载缓存keys: namespace={}", namespace);
			Set<String> keys = this.redisTemplate.keys(String.format("%s:*", namespace));
			logger.debug("结束加载缓存keys: namespace={}, result={}", namespace, keys);
			logger.debug("开始批量删除缓存: key={}", keys);
			this.redisTemplate.delete(keys);
			logger.debug("结束批量删除缓存: key={}", keys);
			return ResultCode.SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResultCode.EXCEPTION;
		}
	}

	@Override
	public ResultCode deleteAll() {
		try {
			logger.debug("开始清理缓存");
			this.redisTemplate.execute(new RedisCallback<Void>() {
				@Override
				public Void doInRedis(RedisConnection connection) throws DataAccessException {
					connection.flushDb();
					return null;
				}
			});
			logger.debug("结束清理缓存");
			return ResultCode.SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResultCode.EXCEPTION;
		}

	}

	@Override
	public CacheResult<Long> incr(String namespace, String key) {
		return this.incr(namespace, key, 1);
	}

	@Override
	public CacheResult<Long> incr(String namespace, String key, long step) {
		try {
			logger.debug("开始增加计数器: namespace={}, key={}, step={}", namespace, key, step);
			Long value = this.redisTemplate.opsForValue().increment(String.format("%s:%s", namespace, key), step);
			logger.debug("结束增加计数器: namespace={}, key={}, step={}, 结果：value={}", key, step, value);
			return new CacheResult<Long>(ResultCode.SUCCESS, value);
		} catch (InvalidDataAccessApiUsageException e) {
			logger.warn("计数器[key={}]增加失败：{}", key, e.getMessage());
			return new CacheResult<Long>(ResultCode.VALUE_INCR_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new CacheResult<Long>(ResultCode.EXCEPTION);
		}
	}

	@Override
	public CacheResult<Long> decr(String namespace, String key) {
		return this.decr(namespace, key, 1);
	}

	@Override
	public CacheResult<Long> decr(String namespace, String key, long step) {
		return this.incr(namespace, key, 0 - step);
	}
}
