@ECHO OFF
set MAVEN_HOME=D:\JAAD2015-II\02_Programas\apache-maven-3.1.1
set PATH=%MAVEN_HOME%\bin;%PATH%
call mvn dependency:tree > tree.log
pause
