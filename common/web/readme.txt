本地运行
0. 运行应用下面的XXXApplication的main方法
1. 控制台日志输出控制
	-Dspring.profiles.active=console
2. cloud-config.properties加载
	1). 配置文件cloud-config.properties是本地加载，里面指定了配置服务信息
	2). 配置文件默认加载地址为/opt/pay/config/open/，如需覆盖，可用下面两种方式之一
		-Dconfig.root=D:\svn\env_conf\func105\open (或-Dconfig.root=D:/svn/env_conf/func105/open)
	3). 如果指定了-Dspring.profiles.active=local，则会加载cloud-config-local.properties覆盖cloud-config.properties中的配置项
	
	例1：	-Dspring.profiles.active=console -Dconfig.root=D:\svn\env_conf\func105\open
	  说明：日志console输出；加载配置D:\svn\env_conf\func105\open\cloud-config.properties
	例2：	-Dspring.profiles.active=console,local -Dconfig.root=D:\svn\env_conf\func105\open
	  说明：日志console输出；加载配置D:\svn\env_conf\func105\open\cloud-config.properties和D:\svn\env_conf\func105\open\cloud-config-local.properties，后者覆盖前者。
	
3. 其它配置文件都是通过配置服务(open-config)加载，配置服务信息即是cloud-config.properties中加载的信息
	1). 默认加载顺序
		[config_root]/open/{application}/application-{profile}.properties
		[config_root]/open/application-{profile}.properties
		[config_root]/open/{application}/application.properties
		[config_root]/open/application.properties
	2). {application}对应项目中指定的spring.application.name值
	3). {profile}默认对应spring.cloud.config.profile的值（在文件cloud-config.properties中配置）
	4). {profile}也可通过下面参数指定
		-Dconfig.profile=prod