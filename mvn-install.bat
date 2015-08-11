@ECHO OFF
SET JAVA_HOME=C:\Program Files\Java\jdk1.6.0_45
mvn -o -Dmaven.test.skip=true -DawDev  install
pause
