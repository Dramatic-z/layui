package com.dnt.cloud.cache.api;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.dnt.cloud.cache.result.CacheResult;
import com.dnt.cloud.cache.result.ResultCode;

/**
 * <h2>缓存接口</h2>
 * <p>
 * <h3>0.pom.xml:</h3>
 * 
 * <pre class="code">
 * &lt;dependency&gt;
 *	&lt;groupId&gt;com.netfinworks.open&lt;/groupId&gt;
 *	&lt;artifactId&gt;open-cache-redis&lt;/artifactId&gt;
 * &lt;/dependency&gt;
 * </pre>
 * 
 * <h3>1.配置:</h3> 参考cache-config-sample.properties</br>
 * 注：开放平台已在配置中心配置
 * <p>
 * <h3>2.初始化:</h3>在SpringBoot主类上添加
 * <p>
 * {@code @Import(OpenCacheConfig.class) }
 * <p>
 * <h3>3.接口注入(两种方式)：</h3>
 * <h4>1).通用式：</h4>
 * <p>
 * {@code @Resource OpenCache<Object> openCache; }</br>
 * <p>
 * a.优点：所有objecct都可用此做缓存操作 </br>
 * b.缺点：取出的结果需要强转 </br>
 * 
 * <h4>2).专用式：</h4> </br>
 * {@code @Resource OpenCache<UserBean> openCache; } </br>
 * <p>
 * a.优点：get得到的结果不需要强转 </br>
 * b.缺点：只能用来操作UserBean </br>
 * <p>
 * 
 * @author fenghonggang
 * @see OpenStringCache
 */
public interface NrcCache<V> {

	/**
	 * <h3>设置数据</h3>
	 * <p>
	 * 1). 如果数据已经存在，则覆 盖， </br>
	 * 2). 如果不存在，则新增 </br>
	 * 3). 数据不失效 </br>
	 * 
	 * @param key
	 *            数据的key
	 * @param value
	 *            数据的value
	 * @return ResultCode
	 */
	// ResultCode put(String key, V value);
	ResultCode put(String namespace, String key, V value);

	/**
	 * <h3>设置数据</h3>
	 * <p>
	 * 1). 如果数据已经存在，则覆盖，</br>
	 * 2). 如果不存在，则新增 </br>
	 * 3). 数据在expireSeconds秒后失效
	 * 
	 * 注：相当于 put(String key, V value) + expire(String key, long expireSeconds)
	 * 
	 * @param key
	 *            数据的key
	 * @param value
	 *            数据的value
	 * @param expireSeconds
	 *            数据的有效时间，单位为秒
	 * @return ResultCode
	 */
	// ResultCode put(String key, V value, long expireSeconds);
	ResultCode put(String namespace, String key, V value, long expireSeconds);

	/**
	 * <h3>设置数据有效时间</h3>
	 * 
	 * @param key
	 *            数据的key
	 * @param expireSeconds
	 * @return ResultCode
	 */
	// ResultCode expire(String key, long expireSeconds);
	ResultCode expire(String namespace, String key, long expireSeconds);

	/**
	 * <h3>设置数据过期日期（时间）</h3>
	 * 
	 * @param key
	 *            数据的key
	 * @param expireDate
	 *            数据有效日期
	 * 
	 * @return ResultCode
	 */
	// ResultCode expireAt(String key, Date expireDate);
	ResultCode expireAt(String namespace, String key, Date expireDate);

	/**
	 * <h3>获取所有namespace</h3>
	 * 
	 * @return 所有namespace
	 */
	CacheResult<Set<String>> namespaces();

	/**
	 * <h3>获取namespace下的所有key</h3>
	 * 
	 * @param namespace
	 * @return namespace下的所有key
	 */
	CacheResult<Set<String>> keys(String namespace);

	/**
	 * 
	 * <h3>获取数据</h3>
	 * 
	 * @param key
	 *            要获取的数据的key
	 * 
	 * @return 缓存数据
	 */
	// CacheResult<V> get(String key);
	CacheResult<V> get(String namespace, String key);
	CacheResult<String> getStr(String namespace, String key);

	/**
	 * <h3>批量获取数据</h3>
	 * 
	 * @param keys
	 *            要获取的数据的key列表
	 * 
	 * @return 缓存数据列表数据列表；不存在的返回null列表
	 */
	// CacheResult<List<V>> get(Collection<String> keys);
	CacheResult<Map<String, V>> mget(String namespace);
	CacheResult<Map<String, String>> mgetStr(String namespace);

	/**
	 * <h3>存在缓存数据</h3>
	 * 
	 * @param key
	 *            数据的key
	 * 
	 * @return 存在-true；不存在-false
	 */
	// Boolean hasKey(String key);
	Boolean hasKey(String namespace, String key);

	/**
	 * <h3>删除数据</h3>
	 * 
	 * @param key
	 *            要删除的数据的key
	 * 
	 * @return ResultCode
	 */
	// ResultCode delete(String key);
	ResultCode delete(String namespace, String key);

	/**
	 * <h3>批量删除数据</h3>
	 * 
	 * @param keys
	 *            要删除的数据的key列表
	 * 
	 * @return ResultCode
	 */
	// ResultCode delete(Collection<String> keys);
	ResultCode mdelete(String namespace);

	/**
	 * <h3>删除所有数据（慎用）</h3>
	 * 
	 * 注：redis删除当前db下的所有数据
	 * 
	 * @return ResultCode
	 */
	ResultCode deleteAll();

	/**
	 * 
	 * 将key对应的数据增加1，并返回新值。
	 * <p>
	 * 1). 如果key对应的数据不存在，则新增，并将值设置为1。 </br>
	 * 2). 如果key对应的数据不是int(long)型，则返回失败。 </br>
	 * 
	 * 注：incr(key) = incr(key, 1)
	 * 
	 * @param key
	 *            数据的key
	 * @return 增长后的值
	 */
	CacheResult<Long> incr(String namespace, String key);

	/**
	 * 
	 * 将key对应的数据增加step，并返回新值。
	 * <p>
	 * 1). 如果key对应的数据不存在，则新增，并将值设置为step。 </br>
	 * 2). 如果key对应的数据不是int(long)型，则返回失败。 </br>
	 * 
	 * 注：incr(key, step) = decr(key, -step)
	 * 
	 * @param key
	 *            数据的key
	 * @param step
	 *            增加的步长
	 * @return 增长后的值
	 */
	CacheResult<Long> incr(String namespace, String key, long step);

	/**
	 * 
	 * 将key对应的数据减少1，并返回新值。
	 * <p>
	 * 1). 如果key对应的数据不存在，则新增，并将值设置为-1。 </br>
	 * 2). 如果key对应的数据不是int(long)型，则返回失败 </br>
	 * 
	 * 注：decr(key) = decr(key, 1)
	 * 
	 * @param key
	 *            数据的key
	 * @return 减少后的值
	 */
	CacheResult<Long> decr(String namespace, String key);

	/**
	 * 
	 * 将key对应的数据减少step，并返回新值。
	 * <p>
	 * 1). 如果key对应的数据不存在，则新增，并将值设置为-step。 </br>
	 * 2). 如果key对应的数据不是int(long)型，则返回失败 </br>
	 * 
	 * 注：decr(key, step) = incr(key, -step)
	 * 
	 * @param key
	 *            数据的key
	 * @param step
	 *            减少的步长
	 * @return 减少后的值
	 */
	CacheResult<Long> decr(String namespace, String key, long step);
}
