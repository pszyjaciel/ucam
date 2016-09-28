// kompilacja
// c:\\progra~2\Java\\jdk1.6.0_30\bin\javac.exe -Xlint -d u:\\ucamusers\\java\\ucam\\home\\ -classpath u:\\ucamusers\\java\\ucam\\home\\;c:\\mb\\Ucam92\\classes\\ucam.jar u:\\ucamusers\\java\\ucam\\home\\Workspace\\UcamSilk3\\src\\com\\company\\ucam\\silk\\MainHTConsole.java 

package com.company.ucam.silk;

public class MainHTConsole {

	public void HTConsole() {

		// HyperToolConsole.showDialog();
		BeanShell.startBeanShellDesktop();

		// przed uzyciem funkcji nalezy zapodac import w htconsoli. np.:
		// import com.barco.ets.ucam.log4ucam.LogViewerGUI;
		// .. albo uzywac pelnych sciezek do class

		/*
		com.barco.ets.ucam.log4ucam.Log4Ucam mylogger = com.barco.ets.ucam.log4ucam.Log4Ucam.getLogger();
		mylogger.saveLog("c:\\logger2.txt");

		System.out.println(com.barco.ets.ucam.log4ucam.LogViewerGUI.isLogVisible());
		LogViewerGUI.showLog(); // (otwiera okno View -> Messages)
		

		// LogViewerGUI mydialog = new LogViewerGUI(); 	// constructor not visible
		LogViewerGUI mydialog = LogViewerGUI.getDialog();
		mydialog.addMessage("Jakis mesecz..");

		com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage("maj Mesecz");
		
		LogViewerGUI.update();

		File file = new File("c:\\monitoring.txt");
		com.barco.ets.ucam.log4ucam.OutputFileMonitor ofm = new com.barco.ets.ucam.log4ucam.OutputFileMonitor(file);
		ofm.startMonitoring();

		com.barco.ets.ucam.ui.Baseframe  bf = new Baseframe();
		System.out.println("getVisibleWindowCount(): " + bf.getVisibleWindowCount());
		
		bf.setDefaultLogo();
		bf.setBackgroundVisible(true);
		bf.resetAllScrollableComponents();
		bf.resetAllWindowPositions();

		int vis = new com.barco.ets.ucam.ui.Baseframe().getVisibleWindowCount();
		com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(Integer.toString(vis));

		
		System.out.println(com.barco.ets.ucam.dtl.Ufile.cO.compare("first", "second"));
		int result = com.barco.ets.ucam.dtl.Ufile.cO.compareDPF("c:\\01\\fr.dpf", "c:\\01\\dr.dpf");
		com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(Integer.toString(result));
		
		long zmienna = com.barco.ets.ucam.dtl.Ufile.cO.getchecksum("c:\\01\\dr.dpf");
		com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(Long.toString(zmienna));
		
		
		new com.barco.ets.ucam.dtl.Dtl$object().show();
		
		com.barco.ets.ucam.fiducials.FiducialGUI.showDialog(true);
		
		new com.barco.ets.ucam.hypertool.Icotool().init();
		new com.barco.ets.ucam.hypertool.Icotool().show();

		com.barco.ets.ucam.hypertool.Uts3_GUI.cO.show();
		

		com.barco.ets.ucam.hypertool.Ujobinput.cO.ui();
		
		// wywoluje SmartStart
		com.barco.ets.ucam.hypertool.Ujobinput.cO.run();
		
		// wyswietla zawartosc katalogu
		String[] myFiles = com.barco.ets.ucam.hypertool.Ujobinput.cO.get_files_in_dir("c:\\05");
		for (int i = 0; i < myFiles.length; i++) { com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(myFiles[i] + "\n"); }
		
		com.barco.ets.ucam.hypertool.Ujobinput.cO.no_license("sam strink");
		com.barco.ets.ucam.hypertool.Ujobinput.cO.get_lan("what is the lan");
		
		// otwiera okno 'Select Gar File'
		com.barco.ets.ucam.hypertool.Ujobinput.cO.gar_filebox(); 
		
		com.barco.ets.ucam.hypertool.Ujobinput.cO.dump();
		
		com.barco.ets.ucam.hypertool.Ujobinput.cO.select_files("c:\\01\\dr.dpf");
		com.barco.ets.ucam.hypertool.Ujobinput.cO.preview();
		
		com.barco.ets.ucam.hypertool.Ujobinput.cO.analyse();
		com.barco.ets.ucam.hypertool.Ujobinput.cO.change("jakis sztrink");
		com.barco.ets.ucam.hypertool.Ujobinput.cO.change_job();
		
		com.barco.ets.ucam.hypertool.Ujobinput.cO.addfile("c:\\01\\dr.dpf");
		
		String zmienna2 = com.barco.ets.utils.OSProp.OsArch;
		com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(zmienna2 + "\n");
		zmienna2 = com.barco.ets.utils.OSProp.OsName;
		com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(zmienna2 + "\n");
		zmienna2 = com.barco.ets.utils.OSProp.OsVersion;
		com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(zmienna2 + "\n");

		boolean zmienna3 = com.barco.ets.utils.OSProp.isUnix();
		com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(String.valueOf(zmienna3 + "\n"));
		zmienna3 = com.barco.ets.utils.OSProp.isWindows();
		com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(String.valueOf(zmienna3 + "\n"));
		
		*/

		// w googlach use third party dll c++
		//	com.barco.ets.ucam.hypertool.Ulm.cO.show_licenses();

		return;

	}

}

// c:\Programmer\Java\jdk1.7.0_67\bin\javap.exe -public c:\04\com\barco\ets\\ucam\log4ucam\LogViewerGUI.class
//Compiled from "LogViewerGUI.java"
//public class com.barco.ets.ucam.log4ucam.LogViewerGUI extends javax.swing.JDialog implements com.barco.ets.ucam.log4ucam.ILogListener,com.barco.ets.ucam.hypertool.Uisavelayout$UsaveLayoutListener {
//  public static void showLog();
//  public static com.barco.ets.ucam.log4ucam.LogViewerGUI getDialog();
//  public void addMessage(java.lang.String);
//  public static void update();
//  public void setJMenuBar();
//  public void applyLayout();
//  public static boolean isLogVisible();
//  public static void parentDeiconified();
//  public static void parentIconified();
//  public void update_ucam_pos(com.barco.ets.ucam.dtl.T_dict);
//}
