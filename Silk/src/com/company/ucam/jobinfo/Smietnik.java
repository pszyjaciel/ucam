package com.company.ucam.jobinfo;

import com.barco.ets.ucam.hypertool.Ufd;

public class Smietnik {

	
	// smietnik

	// job.to_ext("ger274x", "u:\\ucam-cfg\\cfg\\Cad");	// wywala brak licencji
	
	// vectorfill nie dziala jak bylo brak zmiennych w environment:
	//		set FNTDIR=%ETSCAM_DAT%\fonts
	//		set UFNTDIR=%ETSCAM_DAT%\fonts
	//		set UCAM_LANG=english
	
	
//	Ufd ufd = new Ufd();
//	double diam = 2;
//	double overlap = 1;
//	int nape = 1;
//
//	ktcomp_p.arc_expand(1); // dziala
//	ktcomp_p.select_shape("txt");
//	ktcomp_p.select_shape("com");

	// System.out.println("ktcomp_p.select_count(): " + ktcomp_p.select_count() + "\n");

	// ktcomp_p.vectorfill(diam, overlap, nape, 1, "txt com", "sel", 1, ufd);
	// ktcomp_p.vectorfill(diam, overlap, nape, 1, "txt com", "sel");

	
	// czytaj wszystkie warstwy
	//		int numLayers = job.numlayers();
	//		for (int i = 1; i <= numLayers; ++i) {
	//			Ucamobj layer = job.getlayer("all", "", i);
	//			if (layer.name().equals("ktcomp_p") || layer.name().equals("comp_p") || layer.name().equals("outline_p")) {
	//				textArea.append("File specification : " + layer.spec() + "\n");
	//			}
	//		}

	//		textArea.append("Layer: " + lName + " has been added. \n");
	//		textArea.append("lAttach: " + lAttach + "\n");
	//		textArea.append("lNumber: " + lNumber + "\n");
	//		textArea.append("lClass: " + lClass + "\n");
	//		textArea.append("lSubclass: " + lSubclass + "\n");

	//		boolean lTypeSilk = myUlayObj[j].insubclass("silk");
	//		boolean lTypeOutline = myUlayObj[j].insubclass("outline");
	//		boolean lTypeOuter = myUlayObj[j].insubclass("outer");

	//		public double findClearance(Ulayer layer) {
	//			double x1 = 100;
	//			double y1 = 400;
	//			double x2 = 50;
	//			double y2 = 80;
	//
	//			Upoint p1 = new Upoint(x1, y1);
	//			Upoint p2 = new Upoint(x2, y2);
	//			double radius = 50;
	//
	//			Uapeobj apeobj1 = layer.closestobj(p1, radius);
	//			Uapeobj apeobj2 = layer.closestobj(p2, radius);
	//
	//			double clearance = 0;
	//			// Calculate the clearance when 2 objects were selected.
	//			if ((apeobj1 != null) && (apeobj2 != null)) {
	//				clearance = apeobj1.clearance_to(apeobj2);
	//				textArea.append(String.valueOf(clearance));
	//			} else {
	//				Ucamapp.cO.warning("No object found.");
	//			}
	//			return clearance;
	//		}

	// ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCqWO10CBnWMuJ0QV7/FadW5jboMEz/1jNMG20U9hzjjWldlKH7JHTQc92Hb7U6oL1B2wmkkiZ6lduAmgIWQMX7JDKMxbRe8mVNFMN9UuyHsFpSltHvC7HvR1tv9KH6XwPi3hgaT0LO+ZeMvJsw+UCcrHmoT/kS/1CbbjOK47DeAw== RSA-1024

	//		Properties p = new Properties(System.getProperties());
	//		p.list(System.out);

	//		textArea.append("job.numactivelay(): " + job.numactivelay() + "\n");

	// 		ktcomp_p.getape(num);

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

	// System.out.println(LogViewerGUI.isLogVisible());

	// UcamErrorPrintStream: funkcja nie udekumentowana. dobry jeste. hehe
	//		File file = new File("text.txt");
	//		try {
	//			OutputStream os = new FileOutputStream(file);
	//			UcamErrorPrintStream ueps = new UcamErrorPrintStream(os, true);
	//			ueps.println("test");
	//
	//			LogViewerGUI dialog = LogViewerGUI.getDialog();
	//			//dialog.setVisible(false);
	//			Window[] wn = dialog.getWindows();
	//
	//			ueps.println(wn[0]);
	//			ueps.println(wn[1]);
	//			ueps.println(wn[2]);
	//			
	//		} catch (FileNotFoundException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}

	//		Ulm.cO.init();
	//		Ulm.cO.map_init();
	//		Ulm.cO.show_licenses(true);
	//		System.out.println(Ulm.cO.getCustomerCode());
	//		System.out.println(Ulm.cO.getCustomerName());
	//		
	//		//System.out.println(Ulm.cO.getNotice("ucam"));  // pusto
	//		//System.out.println(Ulm.cO.getVendorString("ucam_multi"));  // pusto
	//		System.out.println(Ulm.cO.getVendorString("ucam"));  // pusto
	//		
	//
	//		String scn = Ulm.cO.classname();
	//		//System.out.println(scn);
	//
	//		boolean arg0 = true;
	//		Ulm.cO.show_licenses(arg0);
	//
	//		
	//		//System.out.println(Ulm.cO.isMapWithLicensekey("ucam"));
	//		//System.out.println(Ulm.cO.isMapWithLicensekey("ucam_multi"));
	//		
	//		// zwraca ilosc dostepnych licencji
	//		//System.out.println(Ulm.cO.map_num_lic("ucam"));
	//		System.out.println(Ulm.cO.map_num_lic("ucam_multi"));
	//		
	//		Ulm.cO.map_free("ucam");
	//		
	////		System.out.println(Ulm.cO.num_lic("ucam"));
	////		System.out.println(Ulm.cO.num_lic("ucam_multi"));
	//
	//		// Dict val = Ulm.cO.lictable();
	//		Dict val = Ulm.cO.licenseMaps();
	//
	//		String sKey;
	//		Udate date;
	//		for (int i = 0; i < val.size(); i++) {
	//			sKey = val.getKey(i);
	//			date = Ulm.cO.expiration_date(sKey);
	//			// System.out.println(Ulm.cO.map_num_lic(sKey));
	//			//System.out.println(sKey + ": " + date.day() + "/" + date.month() + "/" + date.year());
	//			//System.out.println(val.keyAt(i+1));
	//			//System.out.println(val.getString(key));
	//		}
	//		
	//		String[] st = val.getSymbolTable();
	//		// System.out.println(st.length);
	//		
	//
	//		
	//		String map = UcamMain.licenseMap;
	//		System.out.println(map);
	//		boolean res = UcamMain.enhancedEditCheckLicense();
	//		//System.out.println(res);
	//		
	//		UcamMain.enhancedEditAllocateLicense();
	//		UcamMain.enhancedEditAllocateLicense();
	//		UcamMain.enhancedEditAllocateLicense();
	//		
	//		UcamMain.enhancedEditFreeLicense();
	//		UcamMain.enhancedEditFreeLicense();
	//		UcamMain.enhancedEditFreeLicense();
	//		
	//		boolean costam = Uijob.cO.sqLicense;
	//System.out.println(costam);

	//		Umf.cO.init();
	//		System.out.println(Umf.cO.getVersion());
	//		Umf.cO.showPkgInfo();
	//
	//		Umuri.cO.warning("uornink mesecz");
	//
	//		Umuri.cO.cmessage("sztrink1", "sztrink2", 1);
	//		boolean val = Ustring.cO.isNumber("ten");
	//		System.out.println(val);
	//
	//		val = Ustring.cO.isNumber("10");
	//		System.out.println(val);
	//		
	//		String arg0 = "jakis_szrink";
	//		int arg1 = 2;
	//		int result = Ustring.cO.strtol(arg0, arg1);
	//		System.out.println(result);
	//
	//		// HyperShellToolbox.initialize();
	//		System.out.println(HyperShellToolbox.VALUE);

	
}
