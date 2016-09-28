rem kompilacja:
rem c:\Programmer\Java\jdk1.5.0_05\bin\jar.exe -cvfm opti2.jar manifest.mf bin/*.class

rem set LM_LICENSE_FILE=c:\mb\Flexlm\mb_license.dat 
set ETSCAM_DAT=C:\mb\Ucam81\env\dat
set URDPATH=%ETSCAM_DAT%
set ETSCAM_JAVA=C:\mb\Jre\jre_1.5.0-05
set PATH=C:\mb\Ucam81
set CLASS_PATH=c:\mb\Ucam81\classes
set CLASSES=c:\mb\Ucam81\classes\classes.jar;bin

rem C:\mb\Jre\jre_1.5.0-05\bin\java.exe -verbose -Djava.library.path=C:\mb\Ucam81\bin -classpath "%CLASSES%" MainGUI
rem C:\mb\Jre\jre_1.5.0-05\bin\java.exe -Djava.library.path=C:\mb\Ucam81\bin -classpath "%CLASSES%" MainGUI
start C:\mb\Jre\jre_1.5.0-05\bin\javaw.exe -Djava.library.path=C:\mb\Ucam81\bin -classpath "%CLASSES%" MainGUI



