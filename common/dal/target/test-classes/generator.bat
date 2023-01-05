set curdir=%~dp0
@echo 工程目录：%curdir%
cd %curdir%
cd ../../..
call mvn mybatis-generator:generate -e
pause