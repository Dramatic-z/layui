# 新零售云服务dal规范

## 1. dal pom依赖
		<dependency>
			<groupId>com.dnt.nrc</groupId>
			<artifactId>common-dal</artifactId>
		</dependency>
		
## 2. dal代码全部生成
	1). pom
	mybatis-generator-maven-plugin已经配置在nrc-service-root，不需要再配置
	2). 配置文件和bat文件 
	复制src/test/resources下的generator.bat和generatorConfig.xml到需要生成的模块的dal的 src/test/resources下
	3). 数据库连接 
	修改generatorConfig.xml中的jdbcConnection
	和table
	4). 指定Dao和Model的package
	修改javaModelGenerator和javaClientGenerator和targetPackage属性
	5). 指定table和model名
	修改table的tableName和domainObjectName
	6). 生成代码
	双击 generator.bat生成代码
	7). 添加@Mapper到DAO

## 3. domain
	自己定义
	
## 注： model不要用枚举类，damain可以用