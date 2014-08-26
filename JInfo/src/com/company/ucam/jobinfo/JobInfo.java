package com.company.ucam.jobinfo;

/* patrz na sciezke:
 C:\Program Files\Common Files\Microsoft Shared\Windows Live;
 C:\Program Files (x86)\Common Files\Microsoft Shared\Windows Live;
 %SystemRoot%\system32;%SystemRoot%;%SystemRoot%\System32\Wbem;
 %SYSTEMROOT%\System32\WindowsPowerShell\v1.0\;
 c:\mb\Ucam81\bin;c:\Program Files (x86)\Java\jdk1.5.0_21\bin

 Jak nie dziala licencja to jest komunikat:
 Error: Could not connect to license vendor daemon!

 Gdy inny komunikat, to na pewno nie jest to od licencji.

 Gdy java.awt.AWTError: Assistive Technology not found: com.sun.java.accessibility.AccessBridge
 albo java.lang.NoClassDefFoundError: Could not initialize class ZeroGah
 to dlatego ze wlaczony Java Access Bridge.
 To disable Java Access Bridge, run the following command:
 %JRE_HOME%\bin\jabswitch -disable
 Note: You cannot disable Java Access Bridge through the Windows Ease of Access Center.

 Gdy eclipse nie odpala:
 caly eclipse musi byc w program files (x86)
 Path: c:\mb\Ucam92\bin;c:\Program Files (x86)\Java\jre7\bin\

 ----------------

 Dziala:
 jre7 i ucam.jar oraz Path na c:\mb\Ucam92\bin

 Path=
 C:/Program Files (x86)/Java/jre7/bin/client;	// nie wiem skad sie wzielo
 C:/Program Files (x86)/Java/jre7/bin;			// nie wiem skad sie wzielo
 C:/Program Files (x86)/Java/jre7/lib/i386;		// nie wiem skad sie wzielo

 C:\Program Files\Common Files\Microsoft Shared\Windows Live;	// to jest w cmd/set
 C:\Program Files (x86)\Common Files\Microsoft Shared\Windows Live;
 C:\Windows\system32;
 C:\Windows;
 C:\Windows\System32\Wbem;
 C:\Windows\System32\WindowsPowerShell\v1.0\;
 c:\mb\Ucam92\bin\;

 c:\Program Files (x86)\eclipse;				// nie wiem skad sie wzielo


 UnsatisfiedLinkError:
 jre7 i ucam.jar oraz Path na c:\mb\Ucam81\bin
 albo:
 Path:  c:\mb\Ucam81\bin\;c:\Program Files (x86)\Java\jre7\bin\
 jre7 i ucam.jar

 UnsatisfiedLinkError gdy: jre7 i ucam.jar oraz
 Path:  c:\mb\Ucam81\bin\;c:\Program Files (x86)\Java\jre7\bin\
 Rozwiazanie: zmienic Path na c:\mb\Ucam92\bin;c:\Program Files (x86)\Java\jre7\bin\

 Error:
 com/company/ucam/jobinfo/JobInfo : Unsupported major.minor version 51.0
 Path:  c:\mb\Ucam81\bin\;  
 c:\mb\Jre\jre_1.6.0-30 i classes.jar/ucam.jar


 Po kazdej zmianie zmiennych srodowiskowych obowiazkowy reset eclipsa!

 Gdy Ucam92 sie zawiesza przy uruchomieniu, to dlatego ze jest zla (ta krotka) licencja.
 */

// Standard Ucam packages.
import com.barco.ets.ucam.hypertool.*;

// Need to import because in the standard package.

// Additional packages needed.
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Map;

/**
 * Implements a window with a menubar and an area to display the job
 * information.
 */
public class JobInfo extends JFrame {
 
	
	JTextArea textArea;

	public static void main(String[] args) {

		UcamMain.setup();

		// Create a new window and display it.
		new JobInfo().setVisible(true);
	}

	/**
	 * Constructs a new frame and creates the user interface.
	 */
	public JobInfo() {
		// Create a window with title 'JobInfo'.
		super("JobInfo");

		// Make sure the application stops when the user closes
		// the window.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// Create a menubar with a 'File' menu.
		// The file menu has 3 items :
		// - Open
		// - Save
		// - Quit
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");

		JMenuItem item = new JMenuItem("Open");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openJob();
			}
		});
		fileMenu.add(item);

		item = new JMenuItem("Save");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveText();
			}
		});
		fileMenu.add(item);

		fileMenu.addSeparator();

		item = new JMenuItem("Quit");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(item);

		menuBar.add(fileMenu);

		setJMenuBar(menuBar);

		// Create the info area.
		textArea = new JTextArea(35, 60);
		textArea.setEditable(false);
		textArea.setLineWrap(true);

		// Add the info area through a scrollpane to the window.
		getContentPane().add(new JScrollPane(textArea));

		pack();
	}

	/**
	 * Asks the user to select a job, opens it and displays the information.
	 * 
	 * Called when the 'Open' item is selected.
	 */
	private void openJob() {

		textArea.setText("\n");

		//
		// Read in the job.
		String fileName = "c:\\!Aspire\\elcon\\130-11310\\eltest\\130-11310.job";
		Ujob job = Ujob.cO.read(fileName);

		if (job == null) {
			System.out.println("Invalid job file");
			return;
		} else {
			textArea.append("Ujob.cO.read(): " + job + "\n");
		}

		textArea.append("Przed: job.activelayers(): " + job.activelayers() + "\n");
		job.setactive("all");
		textArea.append("Po: job.activelayers(): " + job.activelayers() + "\n");

		Udrilayer myLayer = (Udrilayer) Udrilayer.cO.create();

		int nl = job.numlayers();
		job.insertlayer(myLayer, nl + 1);
		textArea.append("Po: job.numlayers(): " + job.numlayers() + "\n");

		myLayer.setinfo("Jakies info");
		myLayer.setname("NewLayer");
		myLayer.setactive(true);
		
		textArea.append("Udrilayer.cO.create(): " + myLayer + "\n");
		textArea.append("job.activelayers(): " + job.activelayers() + "\n");

		textArea.append("Przed: ul.ape_max_number(): " + myLayer.ape_max_number() + "\n");
		Ubloape block = (Ubloape) Ubloape.cO.create(10, myLayer);
		myLayer.addape(block);

		Uape cir = Ucirape.cO.create(20, 5.0);
		myLayer.addape(cir);
		textArea.append("Po: ul.ape_max_number(): " + myLayer.ape_max_number() + "\n");

		Uape ape = myLayer.firstape();
		myLayer.addape(ape);
		textArea.append("myLayer.firstape(): " + ape + "\n");

		if (Ucamv6.ucam_job == null) {
			textArea.append("Ucamv6.ucam_job == null\n");
			// return;
		}

		Uxjob curJob = Ucamapp.cO.ucam_job();
		if (curJob == null)
			textArea.append("curJob jest null!" + " \n");

		//			
		//		textArea.append("curJob.: " + curJob.toString() + " \n");
		//		textArea.append("curJob.getInfo(): " + curJob.getInfo() + " \n");

		//		Uobjattrlist attrList = Ucamv6.ucam_job.attributes();
		//		int iloscAtt = attrList.used();
		//		textArea.append("attrList.used(): " + iloscAtt + "\n");

		Ulayer ul = Ulayer.cO.read("c:\\!Aspire\\elcon\\130-11310\\eltest\\sold");
		String nejm = ul.name();
		textArea.append("ul.name(): " + nejm + "\n");

		ul.setactive(true); // gdy nie-active to reszta nie dziala
		ul.copy_transform("", 90); // dziala

		int appe = ul.ape_max_number();
		textArea.append("ul.ape_max_number(): " + appe + "\n");

		int maxNum = ul.ape_max_number();
		Uape cir2 = Ucirape.cO.create(maxNum + 5, 50.3); // dziala!
		ul.addape(cir2);
		textArea.append("Po: Ucirape.cO.create(): " + ul.ape_max_number() + "\n");
		ul.save();

		ape = ul.firstape();
		ape.flash(0, 10.0);
		ape.box(10, 20, 0.5, 0.25);

		Ulayer lbn = job.getlayerbyname("dr");
		lbn.setactive(true);
		textArea.append("job.getlayerbyname(): " + lbn + "\n");
		lbn.addape(ape);

		lbn.copy_transform("", 90); // po transformie wynik zapisuje w pliku comp3.tif

		textArea.append("lbn.subclass(): " + lbn.subclass() + "\n");
		lbn.save();

		String dokont = "c:\\!Aspire\\elcon\\130-11310\\eltest\\comp3"; // rozszerzenie nie istotne
		// lbn.laytobitmap(dokont, 600);  // dziala

		job.insertlayer(lbn, 13);

		textArea.append("przed: Number of layers : " + job.numlayers() + "\n");
		Ujob couponJob = Ujob.cO.create();

		if (couponJob == null) {
			textArea.append("couponJob == null" + "\n");
		} else
			textArea.append("couponJob: " + couponJob.toString() + "\n");

		Udrilayer couponLayer = (Udrilayer) Udrilayer.cO.create();
		textArea.append("po: Number of layers : " + job.numlayers() + "\n");

		Udrilayer lay;
		lay = (Udrilayer) job.getlayer("drill", "", 1);

		//		Ulayer myUlayer = new Usiglayer();
		//		Usiglayer.cO.create();
		//		myUlayer.setactive(true);
		//		textArea.append("myUlayer" + myUlayer + "\n");

		curJob = Ucamapp.cO.ucam_job();
		if (curJob == null)
			textArea.append("curJob jest null!" + " \n");
		else
			textArea.append("curJob: " + curJob + " \n");

		// Get information regarding the job.
		textArea.append("Job name : " + job.name() + "\n");
		textArea.append("Customer : " + job.customer() + "\n");

		int numLayers = job.numlayers();
		textArea.append("Number of layers : " + numLayers + "\n\n");

		// Get information for each layer.
		// for (int i = 1; i <= numLayers; ++i) {
		// layer = job.getlayer("all", "", i);
		// textArea.append("Layer " + i + " : " + layer.name() + "\n");
		// textArea.append("File specification : " + layer.spec() + "\n");
		//
		// textArea.append("\n");
		// }

		Map<String, String> variables = System.getenv();

		for (Map.Entry<String, String> entry : variables.entrySet()) {
			String name = entry.getKey();
			String value = entry.getValue();
			// textArea.append(name + "=" + value + "\n");
		}
	}

	private void saveText() {
		textArea.append("Akuku." + "\n");
	}
}
