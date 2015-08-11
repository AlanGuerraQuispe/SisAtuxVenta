@ECHO OFF
REM set MAVEN_HOME=C:\Program Files\apache-maven-3.0.5
REM set PATH=%MAVEN_HOME%\bin;%PATH%

REM Needed to parse XSD files
call mvn install:install-file -DgroupId=oracle -DartifactId=ojdbc -Dversion=12.1.0.1 -Dpackaging=jar -Dfile=ojdbc6.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=elaprendiz -DartifactId=elaprendiz -Dversion=1.0 -Dpackaging=jar -Dfile=swinger.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=jcl -DartifactId=jcl -Dversion=1.0 -Dpackaging=jar -Dfile=jcl.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=jpos-controls -DartifactId=jpos-controls -Dversion=1.0 -Dpackaging=jar -Dfile=jpos-controls.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=stario -DartifactId=stario -Dversion=1.0 -Dpackaging=jar -Dfile=stario.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=starjavapos -DartifactId=starjavapos -Dversion=1.0 -Dpackaging=jar -Dfile=starjavapos.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=jcalendar -DartifactId=jcalendar -Dversion=1.0 -Dpackaging=jar -Dfile=jcalendar.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=jdev-rt -DartifactId=jdev-rt -Dversion=1.0 -Dpackaging=jar -Dfile=jdev-rt.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=NameGenerator -DartifactId=NameGenerator -Dversion=1.0 -Dpackaging=jar -Dfile=NameGenerator.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=eckerd-XML -DartifactId=eckerd-XML -Dversion=1.0 -Dpackaging=jar -Dfile=eckerd-XML.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=xbean -DartifactId=xbean -Dversion=1.0 -Dpackaging=jar -Dfile=xbean.jar -DgeneratePom=true
call mvn install:install-file -DgroupId=CAD_File -DartifactId=CAD_File -Dversion=1.0 -Dpackaging=jar -Dfile=CAD_File.jar -DgeneratePom=true


