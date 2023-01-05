# 0.pom.xml:

	<dependency>
		<groupId>com.dnt.nrc</groupId>
		<artifactId>common-cache</artifactId>
	</dependency>

# 1.配置:

	参考cache-config-sample.properties
	注：开放平台已在配置中心配置，不需要再加

# 2.初始化:
	在SpringBoot主类上添加 
	@Import(OpenCacheConfig.class)  

# 3.接口注入(两种方式)：

	1).通用式：
	@Resource OpenCache<Object> openCache; 
	
	a.优点：所有objecct都可用此做缓存操作 
	b.缺点：取出的结果需要强转 
	
	2).专用式：
	@Resource OpenCache<UserBean> openCache;  
	
	a.优点：get得到的结果不需要强转 
	b.缺点：只能用来操作UserBean 
	
	
# 单机
open.cache.redis.nodes=10.65.216.17:6379
# 集群
open.cache.redis.nodes=10.65.215.31:7000,10.65.215.32:7001,10.65.215.31:7002,10.65.215.32:7003,10.65.215.31:7004,10.65.215.32:7005

