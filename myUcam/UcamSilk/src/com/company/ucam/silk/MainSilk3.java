package com.company.ucam.silk;

/* patrz na sciezke:
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

// dziala na 100% dla UCAM9.2
// zmienne uzytkownika:
// path: c:\mb\Ucam92\bin;c:\mb\Jre\jre_1.6.0-30\bin
// oraz ucam.jar i c:\mb\Jre\jre_1.6.0-30 

// czyli na 81 tez powinno dzialac: no powinno....
// path: c:\mb\Jre\jre_1.5.0-05\bin;c:\mb\Ucam81\bin 
// classes.jar i c:\mb\Jre\jre_1.5.0-05
// ale ale: uwaga na inna wersje JDK przy kompilacji przy pomocy jc.bat!!!

*/

// Standard Ucam packages.

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import com.barco.ets.ucam.hypertool.Uape;
import com.barco.ets.ucam.hypertool.Ucamapp;
import com.barco.ets.ucam.hypertool.Ucamobj;
import com.barco.ets.ucam.hypertool.Ucamv6;
import com.barco.ets.ucam.hypertool.Ucirape;
import com.barco.ets.ucam.hypertool.Uextlayer;
import com.barco.ets.ucam.hypertool.Ujob;
import com.barco.ets.ucam.hypertool.Ulayer;
import com.barco.ets.ucam.hypertool.Urectangle;
import com.barco.ets.ucam.ui.Urdb;

public class MainSilk3 {

	Ujob job;

	int index = 0;
	int i;

	private Ulayer comp_p = null;
	private Ulayer sold_p = null;
	private Ulayer ktcomp_p = null;
	private Ulayer ktsold_p = null;
	private Ulayer outline_p = null;
	private Ulayer outline_s = null;
	private int num;
	private int numLayers;

	public void main() {

		// Check if there is a job loaded.
		job = Ucamv6.ucam_job;
		if (job == null) {
			Ucamapp.cO.warning("Error: No job loaded or invalid job file. Exiting.");
			// System.out.println("\nError: No job loaded or invalid job file. Exiting.");
			com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(
					"MSGERR:" + "Error: No job loaded or invalid job file. Exiting." + "\n");

			return;
		}

		int caseSilk = 0;

		int k = 0, j = 0;
		numLayers = job.numlayers();
		Ulayer[] myUlayObj = new Ulayer[numLayers];

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

				// System.out.println(lName + " - " + lClass + " - " + lSubclass + " - " + lAttach + "\n");

				if (lName.equals("comp_p") && lClass.equals("layer") && lSubclass.equals("outer")
						&& lAttach.equals("none")) {
					comp_p = (Ulayer) layer;
				} else if (lName.equals("sold_p") && lClass.equals("layer") && lSubclass.equals("outer")
						&& lAttach.equals("none")) {
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
				System.out.println("layer: " + layer + "\n");
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
			Ucamapp.cO.warning("There is no silk at all. Exiting.");
			com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(
					"MSGERR:" + "There is no silk at all. Exiting." + "\n");
			return;
		}

		job.setactive("all", 0); // wylaczenie wszystkich warstw

		// tu sa parametry dla 274x: c:\03\Uout$CO.class (poz. 32% w pliku)
		String[] myParam = new String[3];
		myParam[0] = "MOIN";
		myParam[1] = "FSLAX25Y25";
		myParam[2] = "G04 Creator: SilkExport V3.04";

		job.setactive("all", 0); // wylaczenie warstw

		switch (caseSilk) {

		// silk on top side
		case 1: // 
			System.out.println("\nSilk on top side. \n");
			prepareTopSilk();

			ktcomp_p.setactive(true);
			ktcomp_p.transform("", 90); // ... i obrot 90st
			// comp_p.setactive(false); // don't export
			// comp_p.transform("", 90); // ... i obrot 90st
			outline_p.setactive(true);
			outline_p.transform("", 90); // ... i obrot 90st

			job.to_274x("u:/ucam-cfg/cfg/Cad", myParam);
			break;

		// silk on bottom
		case 2:
			System.out.println("\nSilk on bottom side. \n");
			copyOutline();
			prepareBottomSilk();

			ktsold_p.setactive(true);
			ktsold_p.transform("x", 90); // ... i obrot 90st plus mirror x
			// sold_p.setactive(false); // don't export
			// sold_p.transform("x", 90); // ... i obrot 90st plus mirror x
			outline_s.setactive(true);
			outline_s.transform("x", 90); // ... i obrot 90st plus mirror x

			job.to_274x("u:/ucam-cfg/cfg/Cad", myParam);
			break;

		case 3: // silk on both sides
			System.out.println("\nSilk on both sides. \n");

			copyOutline();
			prepareTopSilk();
			prepareBottomSilk();

			ktcomp_p.setactive(true);
			ktcomp_p.transform("", 90); // ... i obrot 90st
			//			comp_p.setactive(false); // don't export
			//			comp_p.transform("", 90); // ... i obrot 90st
			outline_p.setactive(true);
			outline_p.transform("", 90); // ... i obrot 90st

			ktsold_p.setactive(true);
			ktsold_p.transform("x", 90); // ... i obrot 90st plus mirror x
			//			sold_p.setactive(false); // don't export
			//			sold_p.transform("x", 90); // ... i obrot 90st plus mirror x
			outline_s.setactive(true);
			outline_s.transform("x", 90); // ... i obrot 90st plus mirror x

			job.to_274x("u:/ucam-cfg/cfg/Cad", myParam);
			break;

		default:
			Ucamapp.cO.warning("Nothing was exported. Exiting.");
			break;
		}

		Ucamapp.cO.total_view();
		Ucamapp.cO.jobwindow_update(); 			//odswieza okno JOB_EDIT
		Ucamapp.cO.update_plane();			 	//odswieza glowne okno
		return;

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
		// System.out.println("c: " + outline_p.select_count() + "\n");

		outline_s = job.getlayerbyname("outline_s");
		outline_s.setactive(true);

		// Copies all data from the source object. Either "all" or "sel"
		outline_s.copy(outline_p, "sel");
		outline_p.select_all("-");
		outline_p.setactive(false);
		outline_s.setactive(false);
	}

	private void prepareBottomSilk() {
		int iApe;
		String nApe = "";
		String nFrameApe = "";

		// usun blok 2001
		if (outline_p != null) {
			outline_s.setactive(true);
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
			outline_s.select_all("-");
			outline_s.block_expand(); // expand the layer
			// System.out.println(outline_s.name() + ": " + nFrameApe + "(" + num + "): " + cAppeNum
			//		+ " elements were deleted.\n");
		}

		// najpierw usunac zbedne elementy
		if (ktsold_p != null) {
			ktsold_p.setactive(true);
			int cAppeNum = 0;
			int nBlocks = 0;
			int counter = 0;
			Uape ktape = ktsold_p.firstape();
			for (int i = 1; i <= ktsold_p.numapes(); ++i) {
				iApe = ktape.num();
				if (iApe == 2001) {
					// sprawdz czy block
					boolean is2001BLOCK = ktape.is_it("block");
					if (is2001BLOCK != true) {
						// System.out.println("Error: Layer " + ktsold_p.name() + " was expanded before.\n");
						com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(
								"MSGERR:" + "Error: Layer " + ktsold_p.name() + " was expanded before.\n");
					} else {
						nBlocks = ktape.numobj("f");
					}
				} else if (iApe == 101) {
					// sprawdz czy text
					boolean is101TXT = ktape.is_it("txt");
					if (is101TXT != true) {
						//usunac
						ktape.select_curape();
						cAppeNum += ktape.select_count();
						ktape.erase();
					} else {
						counter++;
					}

				} else {
					nFrameApe = Integer.toString(iApe);
					// num = ktape.num(); // Gets the aperture number
					ktape.select_curape();
					cAppeNum += ktape.select_count();
					ktape.erase();
				}

				ktape = ktape.next();
			}

			if (counter == 0 || counter != nBlocks) {
				// System.out.println("Error: Missing or wrong number of TXT-Ape for PCB-Number on production panel.");
				com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(
						"MSGERR:" + "Error: Missing or wrong number of TXT-Ape for PCB-Number on production panel.\n");
			}

			ktsold_p.ape_clean(); // trzeba posprzatac
			ktsold_p.select_all("-");
			ktsold_p.block_expand(); // expand the layer
			// System.out.println(ktsold_p.name() + ": " + nFrameApe + "(" + num + "): " + cAppeNum + " elements were deleted.\n");

			// znajdz ramke UgeKode na ktsold_p i przekopiuj do outline
			cAppeNum = 0;
			counter = 0;
			boolean isCirApe;
			ktape = ktsold_p.firstape();
			for (int i = 1; i <= ktsold_p.numapes(); ++i) {
				iApe = ktape.num();
				nApe = ktape.name();

				if (nApe.equals("ktframe")) {
					nFrameApe = nApe;
					num = ktape.num();
					ktape.select_curape();
					outline_s.copy(ktsold_p, "sel");
					ktape.erase();
					counter++;
				}

				else if (iApe == 104) {
					isCirApe = ktape.is_it("cir");

					Urectangle dim = ktape.dimension();
					if (isCirApe == true && dim.xsize() > 0.298 && dim.xsize() < 0.302) {

						// tu zmien wielkosc na 0.25mm
						Uape newApe = Ucirape.cO.create(iApe, 0.25);
						if (newApe == null)
							// System.out.println("Error: Ucirape.cO.create() was failed.");
							com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(
									"MSGERR:" + "Error: Ucirape.cO.create() was failed.\n");

						ktape.select_curape();
						int res = ktsold_p.replace_aperture(newApe, "sel");

						outline_s.copy(ktsold_p, "sel");
						// System.out.println("Found a frame " + iApe + " on layer: " + ktsold_p.name() + "\n");
						counter++;
					}
					ktape.erase();

				} else {
					// System.out.println("a kuku.");
				}
				ktape = ktape.next();
			}

			if (counter == 0) {
				// System.out.println("'ktframe' was not found on layer: " + ktsold_p.name() + "\n");
				com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(
						"MSGERR:" + "'ktframe' was not found on layer: " + ktsold_p.name() + "\n");
			}

			ktsold_p.ape_clean(); // trzeba posprzatac
			ktsold_p.select_all("-");
			outline_s.select_all("-");
		}
	}

	private void prepareTopSilk() {
		int iApe = 0;
		String nApe = "";
		String nFrameApe = "";

		// usun blok 2001 z outline
		if (outline_p != null && (ktcomp_p != null)) {
			outline_p.setactive(true);
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
			outline_p.select_all("-");
			outline_p.block_expand(); // expand the layer
			// System.out.println(outline_p.name() + ": " + nFrameApe + "(" + num + "): " + cAppeNum
			//		+ " elements were deleted.\n");

			// najpierw usunac zbedne elementy poza BLOCK(2001) i TXT(101)
			ktcomp_p.setactive(true);
			cAppeNum = 0;
			Uape ktape = ktcomp_p.firstape();
			for (int i = 1; i <= ktcomp_p.numapes(); ++i) {
				iApe = ktape.num();
				if (iApe == 2001) {
					// sprawdz czy block
					boolean isBLOCK = ktape.is_it("block");
					if (isBLOCK != true) {
						// System.out.println("Error: Layer " + ktcomp_p.name() + " was expanded before.");
						com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(
								"MSGERR:" + "Error: Layer " + ktcomp_p.name() + " was expanded before.\n");
					}
				} else if (iApe == 101) {
					// sprawdz czy text
					boolean isTXT = ktape.is_it("txt");
					if (isTXT != true) {
						//usunac
						ktape.select_curape();
						cAppeNum += ktape.select_count();
						ktape.erase();
					}

				} else {
					nFrameApe = Integer.toString(iApe);
					// num = ktape.num(); // Gets the aperture number
					ktape.select_curape();
					cAppeNum += ktape.select_count();
					ktape.erase();
				}
				ktape = ktape.next();
			}
			ktcomp_p.ape_clean(); // trzeba posprzatac
			ktcomp_p.select_all("-");
			ktcomp_p.block_expand(); // expand the layer
			// System.out.println(ktcomp_p.name() + ": " + nFrameApe + "(" + num + "): " + cAppeNum + " elements were deleted.\n");

			// znajdz ramke UgeKode i przekopiuj do outline
			cAppeNum = 0;
			int counter = 0;
			boolean isCirApe;

			ktape = ktcomp_p.firstape();

			for (int i = 1; i <= ktcomp_p.numapes(); ++i) {

				iApe = ktape.num();
				nApe = ktape.name();

				if (nApe.equals("ktframe")) { // nowy UL powinien miec appe-name 'ktframe'
					nFrameApe = nApe;
					num = ktape.num();
					ktape.select_curape();
					outline_p.copy(ktcomp_p, "sel");
					ktape.erase();
					counter++;
				}

				else if (iApe == 104) {
					isCirApe = ktape.is_it("cir");

					Urectangle dim = ktape.dimension();
					if (isCirApe == true && dim.xsize() > 0.298 && dim.xsize() < 0.302) {

						// tu zmien wielkosc na 0.25mm
						Uape newApe = Ucirape.cO.create(iApe, 0.25);
						if (newApe == null)
							System.out.println("Error: Ucirape.cO.create() was failed.");

						ktape.select_curape();
						int res = ktcomp_p.replace_aperture(newApe, "sel");

						outline_p.copy(ktcomp_p, "sel");
						// System.out.println("Found a frame " + iApe + " on layer: " + ktsold_p.name() + "\n");
						counter++;
					}
					ktape.erase();

				} else {
					String aName = ktape.name();
					int aNum = ktape.num();
					// System.out.println("aName: " + aName + " aNum: " + aNum);
				}
				ktape = ktape.next();
			}

			if (counter == 0) {
				// System.out.println("'ktframe' was not found on layer: " + ktcomp_p.name() + "\n");
				com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(
						"MSGERR:" + "'ktframe' was not found on layer: " + ktcomp_p.name() + "\n");
			}

			ktcomp_p.ape_clean(); // trzeba posprzatac
			ktcomp_p.select_all("-");
			outline_p.select_all("-");
		}
	}

	private static JTextPane m_MessagePane = null;

	public void addMessage(String s) {
		if (s == null)
			throw new IllegalArgumentException();
		StyledDocument styleddocument = m_MessagePane.getStyledDocument();
		try {
			s = Urdb.cO.ascii2unicode(s);
			if (s.startsWith("MSGERR:")) {
				s = s.substring("MSGERR:".length());
				styleddocument.insertString(styleddocument.getLength(), s, styleddocument.getStyle("red"));
			} else {
				styleddocument.insertString(styleddocument.getLength(), s, styleddocument.getStyle("regular"));
			}
		} catch (BadLocationException badlocationexception) {
		}
	}

}