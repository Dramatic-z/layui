注：前提条件
国际化语言包，放置在/src/main/resources目录
并配置到application.properties
spring.messages.basename=i18n/mess

#集成springMVC国际化
1.引入freemarker
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
2.自定义国际化语言解析器 
详见NrcLocaleResolver类
3.使用WebMvcConfigurerAdapter可以扩展SpringMvc的功能
创建解析器Bean示例，交给Spring管理
4.获取位置在spring-webmvc-4.1.2.RELEASE.jar  包下面的 \org\springframework\web\servlet\view\freemarker\spring.ftl
复制到/src/main/resources/html目录下
5.页面引用
<#import "/spring.ftl" as spring/>
6.使用springMVC标签
<@spring.message code="mess.user.name"/>
<@spring.message code=""/>

<!-- 
语言选择
<a href="${rc.contextPath}/index?i18n_language=en_US">English(US)</a>
<a href="${rc.contextPath}/index?i18n_language=zh_CN">简体中文</a> 
<a href="${rc.contextPath}/index?i18n_language=en">English(EN)</a>
<a href="${rc.contextPath}/index?i18n_language=zh">简体中文ZH</a> 
<a href="${rc.contextPath}/index?i18n_language=ru">俄文RU</a> 
<a href="#" onclick="aclick()">hello</a> 
<@spring.message code="mess.user.name"/>
 -->
#后台应用接口国际化
1.注入I8nHandler
	@Resource
	private I18nHandler i18n;
2.调用getMessage转换获取指定语言信息
i18n.getMessage(request, "mess.user.btn")
