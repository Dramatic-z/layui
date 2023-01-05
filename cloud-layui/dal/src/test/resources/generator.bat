set curdir=%~dp0
@echo ¹¤³ÌÄ¿Â¼£º%curdir%
cd %curdir%
cd ../../..
call mvn mybatis-generator:generate -e
pause