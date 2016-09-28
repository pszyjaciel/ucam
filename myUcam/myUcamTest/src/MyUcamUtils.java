// U:\\ucamusers\java\\ucam\home\Workspace\myUcamTest\src\MyUcamUtils.java
// to bedzie moja klasa utilsow dla moich programow dla ucama

import com.barco.ets.ucam.hypertool.Uape;
import com.barco.ets.ucam.hypertool.Ucirape;
import com.barco.ets.ucam.hypertool.Ujob;
import com.barco.ets.ucam.hypertool.Ulayer;

public final class MyUcamUtils {

	private MyUcamUtils() {

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

	// cuts last 4 signs in the name of the layer
	// f. ex. remove _p_s from the layer name
	public static void changeAllLayerNames(Ujob myJob) {

		String lname;
		Ulayer[] myLayers = getLayerList(myJob);

		int layLength = myLayers.length;
		for (int i = 0; i < layLength; i++) {
			myLayers[i].load();
			lname = myLayers[i].name();
			myLayers[i].setname(lname.substring(0, lname.length() - 4));
		}
	}

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
					System.out.println("used: " + lname);
					return i;
				}
			}
		}
		return -1;
	}

	public static Uape[] getHoles2(Ulayer myLayer) {

		Uape[] myHoles = new Ucirape[myLayer.numapes() + 1];
		for (int i = 1; i < myHoles.length; i++) {
			myHoles[i] = myLayer.getape(i);
		}
		return myHoles;
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
	public static void sortHoles(Ulayer drLayer, String myUnit) {
		Uape myUape, myUapeNext;
		Ucirape myUcirape;
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
			myUcirape = (Ucirape) myUape;
			double sizeRoundet = 0;
			if (myUnit.equals("mm")) {
				sizeRoundet = (double) (Math.round(myUcirape.outer() * 20.0) / 20.0);
			} else if (myUnit.equals("mil")) {
				sizeRoundet = (double) (Math.round(myUcirape.outer() * 1.00D) / 1.00D);
			}
			myUcirape.setouter(sizeRoundet);
			myUape.setnum(m);
		}
	}
}
