// Kompilacja dla Ucam92:
// c:\\progra~2\Java\\jdk1.6.0_30\bin\javac.exe -Xlint -d u:\\ucamusers\\java\\ucam\\home\\ -classpath u:\\ucamusers\\java\\ucam\\home\\;c:\\mb\\Ucam92\\classes\\ucam.jar u:\\ucamusers\\java\\ucam\\home\\Workspace\\UcamSilk3\\src\\com\\company\\ucam\\silk\\MainSilk3.java 
// c:\\progra~2\Java\\jdk1.6.0_30\bin\javac.exe -Xlint -d u:\\ucamusers\\java\\ucam\\home\\ -classpath u:\\ucamusers\\java\\ucam\\home\\;c:\\mb\\Ucam92\\classes\\ucam.jar u:\\ucamusers\\java\\ucam\\home\\Workspace\\UcamSilk3\\src\\com\\company\\ucam\\silk\\MainOptimize.java 
// c:\\progra~2\Java\\jdk1.6.0_30\bin\javac.exe -Xlint -d u:\\ucamusers\\java\\ucam\\home\\ -classpath u:\\ucamusers\\java\\ucam\\home\\;c:\\mb\\Ucam92\\classes\\ucam.jar;c:\\mb\\Ucam92\\classes\\bsh.jar u:\\ucamusers\\java\\ucam\\home\\Workspace\\UcamSilk3\\src\\com\\company\\ucam\\silk\\BeanShell.java 
// c:\\progra~2\Java\\jdk1.6.0_30\\bin\javac.exe -Xlint -d u:\\ucamusers\\java\\ucam\\home\\ -classpath u:\\ucamusers\\java\\ucam\\home\\;c:\\mb\\Ucam92\\classes\\ucam.jar u:\\ucamusers\\java\\ucam\\home\\Workspace\\UcamSilk3\\src\\com\\company\\ucam\\silk\\MainHTConsole.java 
// c:\\progra~2\Java\\jdk1.6.0_30\\bin\javac.exe -Xlint -d u:\\ucamusers\\java\\ucam\\home\\ -classpath u:\\ucamusers\\java\\ucam\\home\\;c:\\mb\\Ucam92\\classes\\ucam.jar u:\\ucamusers\\java\\ucam\\home\\Workspace\\UcamSilk3\\src\\com\\company\\ucam\\silk\MainHoles.java 
// c:\\progra~2\Java\\jdk1.6.0_30\bin\javac.exe -Xlint -d u:\\ucamusers\\java\\ucam\\home\\ -classpath u:\\ucamusers\\java\\ucam\\home\\;c:\\mb\\Ucam92\\classes\\ucam.jar u:\\ucamusers\\java\\ucam\\home\\Workspace\\UcamSilk3\\src\\UcamActions.java 

// Kompilacja dla Ucam81:
// c:\\PROGRA~2\\Java\\JDK15~1.0_0\\bin\\javac.exe -Xlint -d u:\\ucamusers\\java\\ucam\\home\\ -classpath u:\\ucamusers\\java\\ucam\\home\\;c:\\mb\\Ucam81\\classes\\classes.jar u:\\ucamusers\\java\\ucam\\home\\Workspace\\UcamSilk3\\src\\com\\company\\ucam\\silk\MainSilk3.java 
// c:\\PROGRA~2\\Java\\JDK15~1.0_0\\bin\\javac.exe -Xlint -d u:\\ucamusers\\java\\ucam\\home\\ -classpath u:\\ucamusers\\java\\ucam\\home\\;c:\\mb\\Ucam81\\classes\\classes.jar u:\\ucamusers\\java\\ucam\\home\\Workspace\\UcamSilk3\\src\\com\\company\\ucam\\silk\MainHoles.java 
// c:\\PROGRA~2\\Java\\JDK15~1.0_0\\bin\\javac.exe -Xlint -d u:\\ucamusers\\java\\ucam\\home\\ -classpath u:\\ucamusers\\java\\ucam\\home\\;c:\\mb\\Ucam81\\classes\\classes.jar u:\\ucamusers\\java\\ucam\\home\\Workspace\\UcamSilk3\\src\\com\\company\\ucam\\silk\MainHTConsole.java 
// c:\\PROGRA~2\\Java\\JDK15~1.0_0\\bin\\javac.exe -Xlint -d u:\\ucamusers\\java\\ucam\\home\\ -classpath u:\\ucamusers\\java\\ucam\\home\\;c:\\mb\\Ucam81\\classes\\classes.jar u:\\ucamusers\\java\\ucam\\home\\Workspace\\UcamSilk3\\src\\UcamActions.java 

// ---------------------
// najlepiej uzyc: jc81.bat albo jc92.bat 

import com.barco.ets.ucam.hypertool.Ucamapp;
import com.barco.ets.ucam.dtl.Ucamaction;
// import com.company.ucam.silk.MainHTConsole;
// import com.company.ucam.silk.MainOptimize;
import com.company.ucam.silk.MainSilk3;
import com.company.ucam.silk.MainHoles;

/**
 * Class loaded by Ucam.
 * Only its static block is executed.
 */
public class UcamActions {

	// Static block executed when the class is loaded.
	static {

		Ucamaction act = new Ucamaction() {
			public void action() {
				new MainSilk3().main();
			}
		};
		Ucamapp.cO.add_pb("silk_export", "Silk export", null, act, "hypertool_menu", true);

//		act = new Ucamaction() {
//			public void action() {
//				new MainHTConsole().HTConsole();
//			}
//		};
//		Ucamapp.cO.add_pb("hyper_tool_console", "Hyper Tool Console", null, act, "hypertool_menu", true);

//		act = new Ucamaction() {
//			public void action() {
//				new MainOptimize().main();
//			}
//		};
//		Ucamapp.cO.add_pb("drill_time", "Drill Time", null, act, "hypertool_menu", true);

		act = new Ucamaction() {
			public void action() {
				new MainHoles().main();
			}
		};
		Ucamapp.cO.add_pb("sort_holes", "Sort holes", null, act, "hypertool_menu", true);

		
		
		
	}
}