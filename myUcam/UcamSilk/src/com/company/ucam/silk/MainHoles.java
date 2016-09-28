package com.company.ucam.silk;

import javax.swing.JOptionPane;

import com.barco.ets.ucam.hypertool.ApertureManager;
import com.barco.ets.ucam.hypertool.Uape;
import com.barco.ets.ucam.hypertool.Ucamapp;
import com.barco.ets.ucam.hypertool.Ucamobj;
import com.barco.ets.ucam.hypertool.Ucamv6;
import com.barco.ets.ucam.hypertool.Ucirape;
import com.barco.ets.ucam.hypertool.Ujob;
import com.barco.ets.ucam.hypertool.Ulayer;

public class MainHoles {

	private String myUnit;
	private boolean doIncrease;
	private static final String VERSION = "v2.3";

	public static boolean containsIgnoreCase(String src, String what) {
		final int length = what.length();
		if (length == 0)
			return true; // Empty string is contained

		final char firstLo = Character.toLowerCase(what.charAt(0));
		final char firstUp = Character.toUpperCase(what.charAt(0));

		for (int i = src.length() - length; i >= 0; i--) {
			// Quick check before calling the more expensive regionMatches() method:
			final char ch = src.charAt(i);
			if (ch != firstLo && ch != firstUp)
				continue;

			if (src.regionMatches(true, i, what, 0, length))
				return true;
		}
		return false;
	}

	// returns an index of Ulayer array if layer exists, if not returns -1
	public static int getLayerIndexByName(Ulayer[] myLayers, String myLayerName) {
		int i;
		String lname;
		boolean rs;
		for (i = 0; i < myLayers.length; i++) {
			lname = myLayers[i].name();
			if (lname.equals(myLayerName) == true) {
				return i;
			} else {
				rs = containsIgnoreCase(lname, myLayerName);		// is myLayerName included in layer name?
				if ((rs == true) && (myLayers[i].classname().equals("udrilayer"))) {
					System.out.println("used layer: " + lname);
					return i;
				}
			}
		}
		return -1;
	}

	// returns an array of all layers as Ulayer object
	public static Ulayer[] getLayerList(Ujob myJob) {

		int i;
		int lnum = myJob.numlayers();
		Ulayer[] ulayArr = new Ulayer[lnum];

		for (i = 1; i <= lnum; i++) {
			ulayArr[i - 1] = (Ulayer) myJob.getlayer("all", "", i);
		}
		return ulayArr;
	}

	public static Ucirape[] getHoles(Ulayer myLayer) {

		Ucirape[] myHoles = new Ucirape[myLayer.numapes() + 1];
		for (int i = 1; i < myHoles.length; i++) {
			myHoles[i] = (Ucirape) myLayer.getape(i);
		}
		return myHoles;
	}

	// to powiekszanie na etapie camu nie powinno miec miejsca
	// autodrill robi dokladnie to samo, ale na razie ma byc
	public static void increaseHoles(Ulayer myLayer, String myUnit) {

		double holeSize = 0;
		Ucirape[] myHoles = getHoles(myLayer);

		for (int i = 1; i < myHoles.length; i++) {
			if (myUnit.equals("mm")) {
				holeSize = myHoles[i].outer() + 0.1;
			} else if (myUnit.equals("mil")) {
				holeSize = myHoles[i].outer() + 4.0;
			}
			myHoles[i].setouter(holeSize);
		}
	}

	// sortuje tabele otworow od najmniejszego, zaokragla i ustawia numery aperturow
	public static boolean sortHoles(Ulayer drLayer, String myUnit) {
		Uape myUape, myUapeNext;
		Ucirape myUcirape = null;
		double mySize, mySizeNext;

		int arrLength = drLayer.numapes();
		int k;

		for (int m = arrLength; m > 0; m--) {
			for (int i = 0; i < arrLength; i++) {
				k = i + 1;
				myUape = drLayer.getape(i);
				mySize = drLayer.getape(i).dimension().xsize();
				myUapeNext = drLayer.getape(k);
				mySizeNext = myUapeNext.dimension().xsize();

				if (mySizeNext < mySize) {
					drLayer.apeinsertbefore(myUapeNext, myUape);
				}
			}
			myUape = drLayer.getape(m);
			try {
				myUcirape = (Ucirape) myUape;
			} catch (ClassCastException ex) {
				System.out.println("ClassCastException occured. Exiting.");
				return false;
			}

			double sizeRoundet = 0;
			if (myUnit.equals("mm")) {
				sizeRoundet = (double) (Math.round(myUcirape.outer() * 20.0) / 20.0);
			} else if (myUnit.equals("mil")) {
				sizeRoundet = (double) (Math.round(myUcirape.outer() * 1.00D) / 1.00D);
			}
			myUcirape.setouter(sizeRoundet);
			myUape.setnum(m);
		}
		return true;
	}

	private static Ulayer getDrillLayer(Ulayer[] myUlayerList) {

		for (int j = 0; j < myUlayerList.length; j++) {
			String lClass = (String) myUlayerList[j].CLASS();
			String lSubclass = myUlayerList[j].subclass();
			// String lAttach = myUlayerList[j].attach();

			if ((lClass.equals("drill")) && (lSubclass.equals("drill"))) {
				return myUlayerList[j];
			}
		}
		return null;
	}

	public void main() {

		// Check if there is a job loaded.
		Ujob myJob = Ucamv6.ucam_job;
		if (myJob == null) {
			Ucamapp.cO.warning("Error: No job loaded or invalid job file. Exiting.");
			com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(
					"MSGERR:" + "Error: No job loaded or invalid job file. Exiting." + "\n");
			return;
		}

		// podaje aktualne jednostki
		myUnit = Ucamobj.cO.unit();
		System.out.println("Actual unit: " + myUnit);

		Ulayer[] myLayers = getLayerList(myJob);
		Ulayer dr = getDrillLayer(myLayers);

		if (dr == null) {
			Ucamapp.cO.warning("Error: Drill layer not found. Exiting.");
			com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(
					"MSGERR:" + "Error: Drill layer not found. Exiting." + "\n");
			return;
		}

		Object[] options = { "With", "Without" };
		String title = "Sort holes ";
		int result = JOptionPane.showOptionDialog(null, "Sorting with or without \nincreasing of holes?", title
				+ VERSION, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		if (result == 0) {
			doIncrease = true;
		} else if (result == 1) {
			doIncrease = false;
		}

		boolean isLoaded = dr.is_loaded();
		if (isLoaded != true) {
			dr.load();
		}

		if (!dr.active()) {
			dr.setactive(true);
		}

		boolean rs = sortHoles(dr, myUnit);				// sortuje i zaokragla
		if (rs != true) {
			Ucamapp.cO.warning("Error: Possible block-ape on drill layer. Exiting.");
			com.barco.ets.ucam.log4ucam.LogViewerGUI.getDialog().addMessage(
					"MSGERR:" + "Error: Possible block-ape on drill layer. Exiting." + "\n");
			return;
		}

		if (doIncrease == true) {
			increaseHoles(dr, myUnit);		// powieksza ale nie zaokragla 
		}

		Ucamapp.cO.info("Command successful.");
		ApertureManager.showDialog();

		// koniec programa
	}
}
