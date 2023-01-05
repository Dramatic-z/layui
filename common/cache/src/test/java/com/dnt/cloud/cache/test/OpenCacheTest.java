package com.dnt.cloud.cache.test;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.dnt.cloud.cache.api.NrcCache;
import com.dnt.cloud.cache.config.NrcCacheConfig;
import com.dnt.cloud.cache.message.MessageSender;
import com.dnt.cloud.cache.result.CacheResult;
import com.dnt.cloud.cache.result.ResultCode;
import com.dnt.cloud.cache.test.model.UserBean;
import com.dnt.cloud.mock.model.Product;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = { NrcCacheConfig.class })
public class OpenCacheTest {

	@Resource
	StringRedisTemplate stringRedisTemplate;

	@Resource
	RedisTemplate<String, Serializable> redisTemplate;

	@Resource
	MessageSender messageSender;

	@Resource
	NrcCache<Object> nrcCache;

	@Before
	public void setUp() {
	}

////	@Test
	public void putUser() {
		UserBean user = UserBean.getInstance();
		
		nrcCache.put("open.test.user", "user1", user);
		nrcCache.put("open.test.user", "user2", user);
		nrcCache.put("open.test.user", "user3", user);
	}

	@Test
	public void getUser() {
		String v = stringRedisTemplate.opsForValue().get("open.test.user:user1");
		System.out.println(v);

		CacheResult<Object> result = nrcCache.get("open.test.user", "user1");
		UserBean user = (UserBean) result.getValue();
		System.out.println(user);

		result = nrcCache.get("open.test.user", "user2");
		user = (UserBean) result.getValue();
		System.out.println(user);

		result = nrcCache.get("open.test.user", "user2");
		user = (UserBean) result.getValue();
		System.out.println(user);

		CacheResult<Map<String, Object>> mresult = nrcCache.mget("open.test.user");

		System.out.println("mresult: " + mresult);
	}

////	@Test
	public void putStr() {
		ResultCode resultCode = nrcCache.put("open.test.str", "str1", 3);
		Assert.assertTrue(resultCode.isSuccess());
	}

	@Test
	public void putExpireData() {
		ResultCode resultCode = nrcCache.put("open.test.str", "exp1", 10);
		Assert.assertTrue(resultCode.isSuccess());

		nrcCache.put("open.test.str", "exp1", 0);
		nrcCache.put("open.test.str", "exp1", -1);
	}

	@Test
	public void get() {
		CacheResult<Object> result = nrcCache.get("open.test.str", "exp1");
		Assert.assertTrue(result.isSuccess());
		Assert.assertEquals("puttest1value", result.getValue());

		result = nrcCache.get("open.test.str", "puttest2key");
		Assert.assertTrue(result.isSuccess());
	}

	@Test
	public void delete() {
		ResultCode resultCode = nrcCache.deleteAll();
		Assert.assertTrue(resultCode.isSuccess());
	}

//	@Test
	public void expire() {
		ResultCode resultCode = nrcCache.expire("open.test.str", "exp1", 30);
		System.out.println(resultCode);
	}

//	@Test
	public void incr() {
		CacheResult<Long> result = nrcCache.incr("open.test.incr", "incr2", -3);
		System.out.println(result);

		result = nrcCache.incr("open.test.incr", "str1", -3);
		System.out.println(result);
		result = nrcCache.incr("open.test.str", "str1", -3);
		System.out.println(result);
	}

//	@Test
	public void decr() {
		CacheResult<Long> result = nrcCache.decr("open.test.incr", "decr1");
		System.out.println(result);
	}

//	@Test
	public void clear() {
		nrcCache.deleteAll();
	}

//	@Test
	public void keys() {
		System.out.println(nrcCache.namespaces().getValue());
		System.out.println(nrcCache.keys("namespace.open.osms").getValue());
	}

//	@Test
	public void sendMessage() {
		messageSender.sendMessage(new Product("Product1", "A"), "open.msg.channel1");
		messageSender.sendMessage(new Product("Product2", "A"), "open.msg.channel2");
	}
}