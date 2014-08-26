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
import com.barco.ets.ucam.hypertool.*;

// Need to import because in the standard package.

// Additional packages needed.
import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class UMain extends JFrame {

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

	public double findClearance(Ulayer layer) {
		double x1 = 100;
		double y1 = 400;
		double x2 = 50;
		double y2 = 80;

		Upoint p1 = new Upoint(x1, y1);
		Upoint p2 = new Upoint(x2, y2);
		double radius = 50;

		Uapeobj apeobj1 = layer.closestobj(p1, radius);
		Uapeobj apeobj2 = layer.closestobj(p2, radius);

		double clearance = 0;
		// Calculate the clearance when 2 objects were selected.
		if ((apeobj1 != null) && (apeobj2 != null)) {
			clearance = apeobj1.clearance_to(apeobj2);
			textArea.append(String.valueOf(clearance));
		} else {
			Ucamapp.cO.warning("No object found.");
		}
		return clearance;
	}

	private void runJob() {

		textArea.setText("\n");

		// Read in the job.
		String fileName = "u:\\jobs\\130-11310\\silk\\130-11310_p.job";

		job = Ujob.cO.read(fileName);

		if (job == null) {
			System.out.println("Invalid job file");
			return;
		}

		// czytaj wszystkie wartswy
		int numLayers = job.numlayers();
		for (int i = 1; i <= numLayers; ++i) {
			Ucamobj layer = job.getlayer("all", "", i);
			if (layer.name().equals("ktcomp_p") || layer.name().equals("comp_p") || layer.name().equals("outline_p")) {
				textArea.append("File specification : " + layer.spec() + "\n");
			}
		}

		// uaktywnij warstwy
		Ulayer ktcomp_p = job.getlayerbyname("ktcomp_p");
		ktcomp_p.setactive(true);
		textArea.append("ktcomp_p.active(): " + ktcomp_p.active() + "\n");

		Ulayer comp_p = job.getlayerbyname("comp_p");
		comp_p.setactive(true);
		textArea.append("comp_p.active(): " + comp_p.active() + "\n");

		Ulayer outline_p = job.getlayerbyname("outline_p");
		outline_p.setactive(true);
		textArea.append("outline_p.active(): " + outline_p.active() + "\n");

		int cAppeNum = 0;
		int num = 0;
		String nApe = "";
		int iApe;
		String nFrameApe = "";
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
		textArea.append(comp_p.name() + ": " + nFrameApe + "(" + num + "): " + cAppeNum + " elements were deleted.\n");
		comp_p.ape_clean(); // trzeba posprzatac

		// najpierw usunac zbedne elementy
		Uape ktape = ktcomp_p.firstape();
		for (int i = 1; i <= ktcomp_p.numapes(); ++i) {
			iApe = ktape.num();
			if (!nApe.equals("ktframe") && iApe != 2001 && iApe != 9005) {
				nFrameApe = Integer.toString(iApe);
				num = ktape.num(); // Gets the aperture number
				ktape.select_curape();
				cAppeNum += ktape.select_count();
				ktape.erase();
			}
			ktape = ktape.next();
		}
		textArea.append(ktcomp_p.name() + ": " + nFrameApe + "(" + num + "): " + cAppeNum + " elements were deleted.\n");
		ktcomp_p.ape_clean(); // trzeba posprzatac

		// expanduj warstwy
		comp_p.block_expand(); // expand the layer
		ktcomp_p.block_expand(); // expand the layer
		outline_p.block_expand(); // expand the layer

		// znajdz ramke UgeKode i przekopiuj do outline
		ktape = ktcomp_p.firstape();
		for (int i = 1; i <= ktcomp_p.numapes(); ++i) {
			nApe = ktape.name();
			if (nApe.equals("ktframe")) {
				nFrameApe = nApe;
				num = ktape.num();
				ktape.select_curape();
				cAppeNum += ktape.select_count();
				outline_p.copy(ktcomp_p, "sel");
				ktape.erase();
			}
			ktape = ktape.next();
		}
		textArea.append(ktcomp_p.name() + ": " + nFrameApe + "(" + num + "): " + cAppeNum
				+ " elements were copied and deleted.\n");
		ktcomp_p.ape_clean(); // trzeba posprzatac

		// usun blok 2001
		Uape olape = outline_p.firstape();
		for (int i = 1; i <= outline_p.numapes(); ++i) {
			nApe = olape.name();
			if (nApe.equals("frame")) {
				nFrameApe = nApe;
				num = olape.num();
				olape.select_curape();
				cAppeNum += olape.select_count();
				olape.erase();
			}
			olape = olape.next();
		}
		outline_p.ape_clean(); // trzeba posprzatac
		textArea.append(outline_p.name() + ": " + nFrameApe + "(" + num + "): " + cAppeNum
				+ " elements were deleted.\n");
		textArea.append("\n");

		job.setactive("all", 0); // wylaczenie warstw

		ktcomp_p.setactive(true);
		ktcomp_p.transform("", 90); // ... i obrot 90st

		Ufd ufd = new Ufd();
		double diam = 8;
		double overlap = 1;
		int nape = 1;
		// job.to_ext("ger274x", "u:\\ucam-cfg\\cfg\\Cad");	// wywala brak licencji

		ktcomp_p.arc_expand(1); // dziala
		ktcomp_p.select_shape("txt");
		ktcomp_p.select_shape("com");
		textArea.append("ktcomp_p.select_count(): " + ktcomp_p.select_count() + "\n");

		ktcomp_p.vectorfill(diam, overlap, nape, 1, "txt com", "sel", 1, ufd);
		// vectorfill nie dziala jak bylo brak zmiennych w environment:
		//		set FNTDIR=%ETSCAM_DAT%\fonts
		//		set UFNTDIR=%ETSCAM_DAT%\fonts
		//		set UCAM_LANG=english

		i = job.to_274x("C:\\mb\\cfg\\Cad");
		textArea.append("\nktcomp_p is exported to ext. gerber: " + i);
		ktcomp_p.unload();

		comp_p.setactive(true);
		comp_p.transform("", 90); // ... i obrot 90st
		i = job.to_274x("u:\\ucam-cfg\\cfg\\Cad");
		textArea.append("\ncomp_p is exported to ext. gerber: " + i);
		comp_p.unload();

		outline_p.setactive(true);
		outline_p.transform("", 90); // ... i obrot 90st
		i = job.to_274x("u:\\ucam-cfg\\cfg\\Cad");
		textArea.append("\noutline_p is exported to ext. gerber: " + i);
		outline_p.unload();

		//		Properties p = new Properties(System.getProperties());
		//		p.list(System.out);

		//		textArea.append("job.numactivelay(): " + job.numactivelay() + "\n");

		// ktcomp_p.getape(num);

		// ------------------------- koniec wlasciwego programu ------------------------

		// smietnik

		//		outline_p.select_object("d");
		//		i = outline_p.select_count();
		//		textArea.append("\noutline_p.select_count(): " + i + "\n");
		//
		//		outline_p.select_all("-");
		//
		//		outline_p.select_object("f");
		//		i = outline_p.select_count();
		//		textArea.append("outline_p.select_count(): " + i + "\n");
		//
		//		outline_p.select_all("-");
		//
		//		outline_p.select_object("a");
		//		i = outline_p.select_count();
		//		textArea.append("outline_p.select_count(): " + i + "\n");
		//
		//		outline_p.select_all("-");
		//
		//		outline_p.select_object("v");
		//		i = outline_p.select_count();
		//		textArea.append("outline_p.select_count(): " + i + "\n");

		// // utworz nowa warstwe
		// Udrilayer cl = (Udrilayer) Udrilayer.cO.create();
		//
		// cl.setname("ANewDrillLayer_" + numLayers);
		// job.insertlayer(cl, numLayers + 1);
		//
		// // i dolacz do joba
		// job.addlayer(cl);
		//
		// // stworz nowa appe
		// index++;
		// int amax = cl.ape_max_number();
		// Uape cir = Ucirape.cO.create(amax + 5, 35 + index);
		// cl.addape(cir);
		//
		// Uape rec = Urecape.cO.create(amax + 5, 90 + index, 12 + index);
		// cl.addape(rec);
		//
		// Uape box = Uboxape.cO.create(amax + 5, 25 + index, 15 + index, "r",
		// 1.5, 1.2);
		// cl.addape(box);
		//
		// // narysuj cos przy pomocy nowo stworzonej aperczury
		// Uape ape = cl.firstape();
		//
		// // Urectangle(double xmin, double xmax, double ymin, double ymax)
		// Urectangle urect = new Urectangle(0, 1150, 400, 3000); // x1:0,
		// y1:400 / x2:1150, y2:3000
		// ape.addrectangle(urect);
		//
		// boolean czyValid = urect.is_empty();
		// textArea.append("urect.is_empty(): " + czyValid + "\n");
		//
		// double xmax = urect.xmax;
		// double xmin = urect.xmin;
		// double ymax = urect.ymax;
		// double ymin = urect.ymin;
		// textArea.append("xmax: " + xmax + "\n" + "xmin: " + xmin + "\n" +
		// "ymax: " + ymax + "\n" + "ymin: " + ymin
		// + "\n\n");
		//
		// ape.flash(0, 0);
		// ape.flash(xmin + 100, ymin + 100);
		// ape.flash(xmax - 100, ymax - 100);
		//
		// Upoint tl = urect.topleft();
		// double xTopLeft = tl.x;
		// double yTopLeft = tl.y;
		// textArea.append("TopLeft: " + xTopLeft + " / " + yTopLeft + "\n");
		//
		// Upoint tr = urect.topright();
		// double xTopRight = tr.x;
		// double yTopRight = tr.y;
		// textArea.append("TopRight: " + xTopRight + " / " + yTopRight + "\n");
		//
		// Upoint bl = urect.botleft();
		// double xBottomLeft = bl.x;
		// double yBottomLeft = bl.y;
		// textArea.append("BottomLeft: " + xBottomLeft + " / " + yBottomLeft +
		// "\n");
		//
		// Upoint br = urect.botright();
		// double xBottomRight = br.x;
		// double yBottomRight = br.y;
		// textArea.append("BottomRight: " + xBottomRight + " / " + yBottomRight
		// + "\n\n");
		//
		// Urectangle jobSize = job.enclosingbox("all");
		// textArea.append("topleft: " + jobSize.topleft().x + " / " +
		// jobSize.topleft().y + "\n");
		// textArea.append("topright: " + jobSize.topright().x + " / " +
		// jobSize.topright().y + "\n");
		// textArea.append("botleft: " + jobSize.botleft().x + " / " +
		// jobSize.botleft().y + "\n");
		// textArea.append("botright: " + jobSize.botright().x + " / " +
		// jobSize.botright().y + "\n\n");
		//
		// int apeNum = job.ape_max_number() + 1;
		//
		// String clStr = (String) cl.CLASS(); // Returns: "layer", "drill" or
		// "extra".
		// textArea.append("Type of loaded layer: " + clStr + "\n\n");
		// Ulayer blockLayer = Ulayer.cO.create(clStr);
		// blockLayer.copy(cl, "all"); // Copies all data from the source
		// object. Either "all" or "sel"
		// cl.erase("all");
		// cl.ape_clean();
		// Ubloape block = (Ubloape) Ubloape.cO.create(apeNum, blockLayer);
		// cl.addape(block);
		// block.repeat(4, xmax - xmin + 100, 0, 2, ymax - ymin + 100, 0);
		//
		// // Czytaj warstwe z joba
		// Ulayer comp = job.getlayerbyname("comp");
		// textArea.append("job.getlayerbyname(): " + comp + "\n"); // tez
		// dziala
		// comp.setactive(true);
		// textArea.append("comp.active(): " + comp.active() + "\n");
		//
		// Ulayer sold = job.getlayerbyname("sold");
		// textArea.append("job.getlayerbyname(): " + sold + "\n"); // tez
		// dziala
		// sold.setactive(true);
		// textArea.append("sold.active(): " + sold.active() + "\n");
		//
		// comp.copy(sold, "all"); // Copies all data from the source object.
		// Either "all" or "sel"

		// --------------- DOTAD ZROBILEM ---------------

		// Ucamobj lbn = job.getlayer("layer", "outer", 1);
		// textArea.append("job.getlayer(): " + lbn + "\n");
		//
		// lbn = job.getlayer("layer", "outer", 2);
		// textArea.append("job.getlayer(): " + lbn + "\n");

		// lbn.copy_transform("", 45); // obraca z bledami, niektore elementy
		// nie sa obracane
		// String dokont = "c:\\jobs\\155-11182\\" + name; // rozszerzenie nie
		// istotne
		// lbn.laytobitmap(dokont, 600); // dziala, zapisuje TIFFa

		// textArea.append("przed: Number of layers : " + job.numlayers() +
		// "\n");
		// Ujob couponJob = Ujob.cO.create();
		//
		// if (couponJob == null) {
		// textArea.append("couponJob == null" + "\n");
		// } else
		// textArea.append("couponJob: " + couponJob.toString() + "\n");
		//
		// Udrilayer couponLayer = (Udrilayer) Udrilayer.cO.create();
		// textArea.append("po: Number of layers : " + job.numlayers() + "\n");
		//
		// Udrilayer lay;
		// lay = (Udrilayer) job.getlayer("drill", "", 1);

		// Ulayer myUlayer = new Usiglayer();
		// Usiglayer.cO.create();
		// myUlayer.setactive(true);
		// textArea.append("myUlayer" + myUlayer + "\n");

		//		Uxjob curJob = Ucamapp.cO.ucam_job();
		//		if (curJob == null)
		//			textArea.append("curJob jest null!" + " \n");
		//		else
		//			textArea.append("curJob: " + curJob + " \n");
		//
		//		// Get information regarding the job.
		//		textArea.append("Job name : " + job.name() + "\n");
		//		textArea.append("Customer : " + job.customer() + "\n");
		//
		//		// --------------------------------
		//		textArea.append("Przed: job.activelayers(): " + job.activelayers() + "\n");
		//		job.setactive("all");
		//		textArea.append("Po: job.activelayers(): " + job.activelayers() + "\n");
		//
		//		Udrilayer myLayer = (Udrilayer) Udrilayer.cO.create();
		//
		//		myLayer.setinfo("Jakies info");
		//		myLayer.setname("NewLayer");
		//		myLayer.setactive(true);
		//
		//		textArea.append("Udrilayer.cO.create(): " + myLayer + "\n");
		//		textArea.append("job.activelayers(): " + job.activelayers() + "\n");
		//
		//		textArea.append("Przed: ul.ape_max_number(): " + myLayer.ape_max_number() + "\n");
		//		Ubloape block2 = (Ubloape) Ubloape.cO.create(10, myLayer);
		//		myLayer.addape(block2);
		//
		//		Uape cir2 = Ucirape.cO.create(20, 5.0);
		//		myLayer.addape(cir2);
		//		textArea.append("Po: ul.ape_max_number(): " + myLayer.ape_max_number() + "\n");
		//
		//		if (Ucamv6.ucam_job == null) {
		//			textArea.append("Ucamv6.ucam_job == null\n");
		//			// return;
		//		}
		//
		//		curJob = Ucamapp.cO.ucam_job();
		//		if (curJob == null)
		//			textArea.append("curJob jest null!" + " \n");

		//
		// textArea.append("curJob.: " + curJob.toString() + " \n");
		// textArea.append("curJob.getInfo(): " + curJob.getInfo() + " \n");

		// Uobjattrlist attrList = Ucamv6.ucam_job.attributes();
		// int iloscAtt = attrList.used();
		// textArea.append("attrList.used(): " + iloscAtt + "\n");

		// Ulayer ul = Ulayer.cO.read("c:\\jobs\\155-11182\\comp");
		//
		// // trza wziasc go z joba
		// String nejm = ul.name();
		// textArea.append("ul.name(): " + nejm + "\n");
		//
		// ul.setactive(true); // gdy nie-active to reszta nie dziala
		// // ul.copy_transform("", 90); // dziala
		//
		// int appe = ul.ape_max_number();
		// textArea.append("ul.ape_max_number(): " + appe + "\n");
		//
		// int maxNum = ul.ape_max_number();
		// Uape cir3 = Ucirape.cO.create(maxNum + 5, 50.3); // dziala!
		// ul.addape(cir3);
		// textArea.append("Po: Ucirape.cO.create(): " + ul.ape_max_number() +
		// "\n");
		// ul.save();
		//
		// Uape ape = ul.firstape();
		// ul.addape(ape);
		//
		// ape = ul.firstape();
		// ul.addape(ape);
		// textArea.append("ul.firstape(): " + ape + "\n");
		// //ul.save(); // wywala
		//
		// ape = ul.firstape();
		// ape.flash(0, 10.0);
		// ape.box(10, 20, 0.5, 0.25);

		// Get information for each layer.
		// for (int i = 1; i <= numLayers; ++i) {
		// layer = job.getlayer("all", "", i);
		// textArea.append("Layer " + i + " : " + layer.name() + "\n");
		// textArea.append("File specification : " + layer.spec() + "\n");
		//
		// textArea.append("\n");
		// }

		// http://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html

		//				URDPATH=C:/mb/Ucam92/env/dat
		//				HOME=u:/ucamusers/java/ucam/home
		//				ETSCAM_INSTALL=C:/mb/Ucam92
		//				ETSCAM_DAT=C:/mb/Ucam92/env/dat
		//				UCAM_LANG=english
		//				CLASS_PATH=C:/mb/Ucam92/classes
		//				FNTDIR=C:/mb/Ucam92/env/dat/fonts
		//				UFNTDIR=C:/mb/Ucam92/env/dat/fonts

		// system properties to nie to samo co system environment variables
		//		FileInputStream propFile;
		//		try {
		//			propFile = new FileInputStream("myProperties.txt");
		//			Properties p = new Properties(System.getProperties());
		//			p.load(propFile);
		//			System.setProperties(p);
		//
		//		} catch (FileNotFoundException e1) {
		//			e1.getMessage();
		//		} catch (IOException e) {
		//			e.getMessage();
		//		}

	}

	private void saveText() {
		job.save();
		textArea.append("Job zapisany do pliku." + "\n");
	}
}
