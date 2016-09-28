@echo off
rem kompilacja:
rem c:\Programmer\Java\jdk1.5.0_05\bin\jar.exe -cvfm opti2.jar manifest.mf bin/*.class



rem java.exe -verbose -Djava.library.path=c:\mb\Ucam92\bin -classpath "%CLASSES%" MyUrout
java.exe -Djava.library.path=c:\mb\Ucam92\bin -classpath "%CLASSES%" MyUrout
rem start javaw.exe -Djava.library.path=c:\mb\Ucam92\bin -classpath "%CLASSES%" MyUrout



