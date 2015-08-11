setlocal
SET JAVA_HOME=C:\Program Files\Java\jdk1.6.0_45
call mvn -Dmaven.test.skip=true -DawDev -U package
pause