REM @ECHO OFF
set MAVEN_HOME=C:\Program Files\apache-maven-3.0.5
set PATH=%MAVEN_HOME%\bin;%PATH%
call mvn dependency:tree > tree.log
pause
