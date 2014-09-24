package com.company.ucam.jobinfo;

/* 
 * 
 *  -help              : Shows this commandline help.
 -showversion       : Shows the used versions of ucam, java, the OS, and all the default java paths.
 -unit= <curunit>   : Sets the current unit, where curunit can be mm, inch or mil.
 -job <job_file>    : Loads the given job, full path should be added.
 -dpf <dpf_file>    : Loads a new temporary job containing the defined dpf-file.
 -nologwindow       : Disables Ucam's "Messages" window, which is an alternative for Java's Console window.
                This option can and should only be used when starting java instead of javaw,
                because javaw does not have an associated Console window,
                which would leave you without any console output.
 -record= <name.rec>   : Records the started ucam session into the filename name.rec.
 -playback= <name.rec> : Plays back a recorded ucam session with the filename: name.rec.
 
 * patrz na sciezke:
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

 *
 * Gdy No license for 'hypertool'. Exiting...
 * Wtedy Run -> Run Configuration -> Environment i ustawic te zmienne:
 * HOME u:\\ucamusers\java\\ucam\home
 * LM_LICENSE_FILE c:\mb\Flexlm\elcon.da2
 * (patrz tam: u:\\ucamusers\java\\ucam\home\workspace\zmienne.JPG)
 * 
 *
 */




// Standard Ucam packages.
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.barco.ets.ucam.hypertool.Uape;
import com.barco.ets.ucam.hypertool.UcamMain;
import com.barco.ets.ucam.hypertool.Ucamobj;
import com.barco.ets.ucam.hypertool.Uextlayer;
import com.barco.ets.ucam.hypertool.Ufd;
import com.barco.ets.ucam.hypertool.Ujob;
import com.barco.ets.ucam.hypertool.Ulayer;
import com.barco.ets.ucam.log4ucam.ILogListener;



public class UMain extends JFrame implements ILogListener {

	JTextArea textArea;
	Ujob job;

	public static void main(String[] args) {
		UcamMain.setup();
		

		// Create a new window and display it.
		new UMain().setVisible(true);
	}

	/**
	 * Constructs a new frame and creates the user interface.
	 */
	public UMain() {
		// Create a window with title 'JobInfo'.
		super("Silk extractor");

		// Make sure the application stops when the user closes
		// the window.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");

		JMenuItem item = new JMenuItem("Run");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runJob();
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
		textArea = new JTextArea(30, 80);
		textArea.setEditable(false);
		textArea.setLineWrap(true);

		Font f = new Font("Consolas", Font.PLAIN, 15);
		textArea.setFont(f);

		// Add the info area through a scrollpane to the window.
		getContentPane().add(new JScrollPane(textArea));

		pack();
	}

	int index = 0;
	int i;
	private Ulayer[] myUlayObj;

	private Ulayer comp_p = null;
	private Ulayer sold_p = null;
	private Ulayer ktcomp_p = null;
	private Ulayer ktsold_p = null;
	private Ulayer outline_p = null;
	private Ulayer outline_s = null;

	private int num = 0;
	private int numLayers;

	private void runJob() {

		textArea.setText("\n");

		// Read in the job.
		// String fileName = "u:\\jobs\\130-11310\\silk\\130-11310_p.job";
		// String fileName = "u:\\jobs\\1437-11443\\1437-11443_p.job";
		String fileName = "u:\\jobs\\201-11509\\201-11509_p.job";

		job = Ujob.cO.read(fileName);

		if (job == null) {
			textArea.append("Error: Invalid job file: " + fileName + "\n");
			return;
		}

		int caseSilk = 0;

		int k = 0, j = 0;
		numLayers = job.numlayers();
		myUlayObj = new Ulayer[numLayers];

		/*
		 * zrob liste layerow w jobie i zapisz te obiekty do jakies tablicy  
		 * 
		 * znajdz w tej liscie np silka/top i przypisz do zmiennej np. silkTop
		 * to samo dla silkBottom, comp, sold, outline 
		 */

		for (k = 1; k <= numLayers; k++) {
			Ucamobj layer = job.getlayer("all", "", k);
			if (layer != null) {
				myUlayObj[j] = (Ulayer) layer; // dodaj warstwe do tablicy objektow

				String lName = myUlayObj[j].name();
				String lClass = (String) myUlayObj[j].CLASS();
				String lSubclass = myUlayObj[j].subclass();
				String lAttach = myUlayObj[j].attach();

				// textArea.append(lName + " - " + lClass + " - " + lSubclass + " - " + lAttach + "\n");

				if (lName.equals("comp_p") && lClass.equals("layer") && lSubclass.equals("outer")) {
					comp_p = (Ulayer) layer;
				} else if (lName.equals("sold_p") && lClass.equals("layer") && lSubclass.equals("outer")) {
					sold_p = (Ulayer) layer;
				} else if (lName.equals("ktcomp_p") && lClass.equals("extra") && lSubclass.equals("silk")
						&& lAttach.equals("top")) {
					ktcomp_p = (Ulayer) layer;
				} else if (lName.equals("ktsold_p") && lClass.equals("extra") && lSubclass.equals("silk")
						&& lAttach.equals("bottom")) {
					ktsold_p = (Ulayer) layer;
				} else if (lName.equals("outline_p") && lClass.equals("extra") && lSubclass.equals("outline")
						&& lAttach.equals("none")) {
					outline_p = (Ulayer) layer;
				}
			} else
				textArea.append("layer: " + layer + "\n");
		}

		// silk on top
		if (ktcomp_p != null && ktsold_p == null && outline_p != null) {
			caseSilk = 1;
		}

		// silk on bottom
		else if (ktcomp_p == null && ktsold_p != null && outline_p != null) {
			caseSilk = 2;
		}

		// silk on both sides
		else if (ktcomp_p != null && ktsold_p != null && outline_p != null) {
			caseSilk = 3;
		}

		// no silk
		else {
			textArea.append("There is no silk at all. Exiting. \n");
			return;
		}

		switch (caseSilk) {

		// silk on top side
		case 1: // 
			textArea.append("Silk on top side. \n");
			prepareTopSilk();
			break;

		// silk on bottom
		case 2:
			textArea.append("Silk on bottom side. \n");
			copyOutline();
			prepareBottomSilk();
			break;

		case 3: // silk on both sides
			textArea.append("Silk on both sides. \n");

			copyOutline();
			prepareTopSilk();
			prepareBottomSilk();
			break;

		default:
			break;
		}

		// ------------------------- export do gerbera -----------------------
		job.setactive("all", 0); // wylaczenie warstw

		Ufd ufd = new Ufd();
		double diam = 8;
		double overlap = 1;
		int nape = 1;

		// tu sa parametry dla 274x: c:\03\Uout$CO.class (poz. 32% w pliku)
		String[] myParam = new String[3];
		myParam[0] = "MOIN";
		myParam[1] = "FSLAX25Y25";
		myParam[2] = "G04 Creator: SilkExport V3.01";

		ktcomp_p.setactive(true);

		ktcomp_p.arc_expand(1); // dziala
		ktcomp_p.select_shape("txt");
		ktcomp_p.select_shape("com");
		// textArea.append("ktcomp_p.select_count(): " + ktcomp_p.select_count() + "\n");

		ktcomp_p.vectorfill(diam, overlap, nape, 1, "txt com", "sel", 1, ufd);
		//ktcomp_p.vectorfill(diam, overlap, nape, 1, "txt com", "sel");

		ktcomp_p.transform("", 90); // ... i obrot 90st
		i = job.to_274x("C:\\mb\\cfg\\Cad", myParam);		// nie dziala w wersji 8.1
		textArea.append("\n" + ktcomp_p.name() + " is exported to ext. gerber: " + i);
		ktcomp_p.unload();

		comp_p.setactive(true);
		comp_p.transform("", 90); // ... i obrot 90st
		i = job.to_274x("u:\\ucam-cfg\\cfg\\Cad", myParam);
		textArea.append("\n" + comp_p.name() + " is exported to ext. gerber: " + i);
		comp_p.unload();

		outline_p.setactive(true);
		outline_p.transform("", 90); // ... i obrot 90st
		i = job.to_274x("u:\\ucam-cfg\\cfg\\Cad", myParam);
		textArea.append("\n" + outline_p.name() + " is exported to ext. gerber: " + i);
		outline_p.unload();

		if (ktsold_p != null) {
			ktsold_p.setactive(true);

			//ktsold_p.arc_expand(1); // dziala
			ktsold_p.select_shape("txt");
			ktsold_p.select_shape("com");
			if (ktsold_p.select_count() != 0) {
				// textArea.append("\nktsold_p.select_count(): " + ktsold_p.select_count() + "\n");
				ktsold_p.vectorfill(diam, overlap, nape, 1, "txt, com", "sel", 1, ufd);
			}

			ktsold_p.transform("x", 90); // ... i obrot 90st
			i = job.to_274x("C:\\mb\\cfg\\Cad", myParam);
			textArea.append("\n" + ktsold_p.name() + " is exported to ext. gerber: " + i);
			ktsold_p.unload();
		}

		if (sold_p != null && caseSilk != 1) {
			sold_p.setactive(true);
			sold_p.transform("x", 90); // ... i obrot 90st
			i = job.to_274x("u:\\ucam-cfg\\cfg\\Cad", myParam);
			textArea.append("\n" + sold_p.name() + " is exported to ext. gerber: " + i);
			sold_p.unload();
		}

		if (outline_s != null) {
			outline_s.setactive(true);
			outline_s.transform("x", 90); // ... i obrot 90st
			i = job.to_274x("u:\\ucam-cfg\\cfg\\Cad", myParam);
			textArea.append("\n" + outline_s.name() + " is exported to ext. gerber: " + i);
			outline_s.unload();
		}

		// ------------------------- koniec wlasciwego programu ------------------------

	}

	private void copyOutline() {
		Uextlayer cl = (Uextlayer) Uextlayer.cO.create();

		cl.setname("outline_s");
		cl.setsubclass("outline");
		cl.setattach("bottom");

		numLayers = job.numlayers();
		job.insertlayer(cl, numLayers + 1);

		// i dolacz do joba
		job.addlayer(cl);

		outline_p.setactive(true);
		outline_p.select_all("+");
		// textArea.append("outline_p.select_count(): " + outline_p.select_count() + "\n");

		outline_s = job.getlayerbyname("outline_s");
		outline_s.setactive(true);

		// Copies all data from the source object. Either "all" or "sel"
		outline_s.copy(outline_p, "sel");

	}

	private void prepareBottomSilk() {
		int iApe;
		String nApe = "";
		String nFrameApe = "";

		sold_p.setactive(true);
		ktsold_p.setactive(true);
		outline_s.setactive(true);

		// usun blok 2001
		int cAppeNum = 0;
		Uape olape = outline_s.firstape();
		for (int i = 1; i <= outline_s.numapes(); ++i) {
			nApe = olape.name();
			if (nApe.equals("frame") || olape.num() == 2001) {
				nFrameApe = nApe;
				num = olape.num();
				olape.select_curape();
				cAppeNum += olape.select_count();
				olape.erase();
			}
			olape = olape.next();
		}
		outline_s.ape_clean(); // trzeba posprzatac
		// textArea.append(outline_s.name() + ": " + nFrameApe + "(" + num + "): " + cAppeNum
		//		+ " elements were deleted.\n");
		// textArea.append("\n");

		cAppeNum = 0;
		Uape soldape = sold_p.firstape();
		for (int i = 1; i <= sold_p.numapes(); ++i) {
			iApe = soldape.num();
			if (iApe != 2001 && iApe != 9005) {
				nFrameApe = Integer.toString(iApe);
				num = soldape.num();
				soldape.select_curape();
				cAppeNum += soldape.select_count();
				soldape.erase();
			}
			soldape = soldape.next();
		}
		// textArea.append(sold_p.name() + ": " + nFrameApe + "(" + num + "): " + cAppeNum + " elements were deleted.\n");
		sold_p.ape_clean(); // trzeba posprzatac

		// najpierw usunac zbedne elementy
		cAppeNum = 0;
		Uape ktape = ktsold_p.firstape();
		for (int i = 1; i <= ktsold_p.numapes(); ++i) {
			iApe = ktape.num();
			if (iApe != 2001 && iApe != 9005) {
				nFrameApe = Integer.toString(iApe);
				num = ktape.num(); // Gets the aperture number
				ktape.select_curape();
				cAppeNum += ktape.select_count();
				ktape.erase();
			}
			ktape = ktape.next();
		}
		// textArea.append(ktsold_p.name() + ": " + nFrameApe + "(" + num + "): " + cAppeNum + " elements were deleted.\n");
		ktsold_p.ape_clean(); // trzeba posprzatac

		// expanduj warstwy
		sold_p.block_expand(); // expand the layer
		ktsold_p.block_expand(); // expand the layer
		outline_s.block_expand(); // expand the layer

		// znajdz ramke UgeKode i przekopiuj do outline
		cAppeNum = 0;
		ktape = ktsold_p.firstape();
		for (int i = 1; i <= ktsold_p.numapes(); ++i) {
			nApe = ktape.name();
			if (nApe.equals("ktframe")) {
				nFrameApe = nApe;
				num = ktape.num();
				ktape.select_curape();
				cAppeNum += ktape.select_count();
				if (cAppeNum > 0) {
					outline_s.copy(ktsold_p, "sel");
				}
				ktape.erase();
			}
			ktape = ktape.next();
		}
		if (cAppeNum == 0) {
			textArea.append("'ktframe' was not found on layer: " + ktsold_p.name() + "\n");
		} else {
			// textArea.append(ktsold_p.name() + ": " + nFrameApe + "(" + num + "): " + cAppeNum
			// 		+ " elements were copied and deleted.\n");
		}
		ktsold_p.ape_clean(); // trzeba posprzatac

	}

	private void prepareTopSilk() {
		int iApe;
		String nApe = "";
		String nFrameApe = "";

		ktcomp_p.setactive(true);
		comp_p.setactive(true);
		outline_p.setactive(true);

		// usun blok 2001
		int cAppeNum = 0;
		Uape olape = outline_p.firstape();
		for (int i = 1; i <= outline_p.numapes(); ++i) {
			nApe = olape.name();
			if (nApe.equals("frame") || olape.num() == 2001) {
				nFrameApe = nApe;
				num = olape.num();
				olape.select_curape();
				cAppeNum += olape.select_count();
				olape.erase();
			}
			olape = olape.next();
		}
		outline_p.ape_clean(); // trzeba posprzatac
		// textArea.append(outline_p.name() + ": " + nFrameApe + "(" + num + "): " + cAppeNum
		//		+ " elements were deleted.\n");
		// textArea.append("\n");

		cAppeNum = 0;
		Uape compape = comp_p.firstape();
		for (int i = 1; i <= comp_p.numapes(); ++i) {
			iApe = compape.num();
			if (iApe != 2001 && iApe != 9005) {
				nFrameApe = Integer.toString(iApe);
				num = compape.num();
				compape.select_curape();
				cAppeNum += compape.select_count();
				compape.erase();
			}
			compape = compape.next();
		}
		// textArea.append(comp_p.name() + ": " + nFrameApe + "(" + num + "): " + cAppeNum + " elements were deleted.\n");
		comp_p.ape_clean(); // trzeba posprzatac

		// najpierw usunac zbedne elementy
		cAppeNum = 0;
		Uape ktape = ktcomp_p.firstape();
		for (int i = 1; i <= ktcomp_p.numapes(); ++i) {
			iApe = ktape.num();
			if (iApe != 2001 && iApe != 9005) {
				nFrameApe = Integer.toString(iApe);
				num = ktape.num(); // Gets the aperture number
				ktape.select_curape();
				cAppeNum += ktape.select_count();
				ktape.erase();
			}
			ktape = ktape.next();
		}
		// textArea.append(ktcomp_p.name() + ": " + nFrameApe + "(" + num + "): " + cAppeNum + " elements were deleted.\n");
		ktcomp_p.ape_clean(); // trzeba posprzatac

		// expanduj warstwy
		comp_p.block_expand(); // expand the layer
		ktcomp_p.block_expand(); // expand the layer
		outline_p.block_expand(); // expand the layer

		// znajdz ramke UgeKode i przekopiuj do outline
		cAppeNum = 0;
		ktape = ktcomp_p.firstape();
		for (int i = 1; i <= ktcomp_p.numapes(); ++i) {
			nApe = ktape.name();
			if (nApe.equals("ktframe")) {
				nFrameApe = nApe;
				num = ktape.num();
				ktape.select_curape();
				cAppeNum += ktape.select_count();
				if (cAppeNum > 0) {
					outline_p.copy(ktcomp_p, "sel");
				}
				ktape.erase();
			}
			ktape = ktape.next();
		}
		if (cAppeNum == 0) {
			textArea.append("'ktframe' was not found on layer: " + ktcomp_p.name() + "\n");
		} else {
			// textArea.append(ktcomp_p.name() + ": " + nFrameApe + "(" + num + "): " + cAppeNum
			//		+ " elements were copied and deleted.\n");
		}
		ktcomp_p.ape_clean(); // trzeba posprzatac

	}

	private void saveText() {
		job.save();
		textArea.append("\nJob zapisany do pliku." + "\n");
	}

	/* (non-Javadoc)
	 * @see com.barco.ets.ucam.log4ucam.ILogListener#addMessage(java.lang.String)
	 */
	public void addMessage(String arg0) {
		// TODO Auto-generated method stub
		
	}


}
