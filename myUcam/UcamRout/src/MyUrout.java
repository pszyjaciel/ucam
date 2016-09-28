/*
 	u:\\ucamusers\java\\ucam\home\workspace\URout923b\src\ 

	zeby uruchomic kod dla wersji 9.23:
	do PATH dodac c:\mb\Ucam923\Ucam92\bin (inaczej Unsatisfied link error)
	do project -> properties -> java build path wstawic: c:\mb\Ucam923\Ucam92\classes\\ucam.jar  
*/

import com.barco.ets.ucam.hypertool.Dpffla;
import com.barco.ets.ucam.hypertool.Dpfobj;
import com.barco.ets.ucam.hypertool.Uape;
import com.barco.ets.ucam.hypertool.Uapeobj;
import com.barco.ets.ucam.hypertool.Ubloape;
import com.barco.ets.ucam.hypertool.UcamMain;
import com.barco.ets.ucam.hypertool.Ucamobj;
import com.barco.ets.ucam.hypertool.Ujob;
import com.barco.ets.ucam.hypertool.Ulayer;
import com.barco.ets.ucam.hypertool.Upoint;
import com.barco.ets.ucam.hypertool.Urectangle;

public class MyUrout {

	static int count1 = 0;
	static int count2 = 0;

	private static Ujob readJob(String cust, String custno, boolean panel) {

		String fileName;
		String jobNumber = cust + "-" + custno;
		if (panel == true) {
			fileName = "u:\\jobs\\" + cust + "\\" + jobNumber + "\\" + jobNumber + "_p.job";
		} else {
			fileName = "u:\\jobs\\" + cust + "\\" + jobNumber + "\\" + jobNumber + ".job";
		}

		System.out.println("fileName: " + fileName);
		Ujob ujob;
		ujob = Ujob.cO.read(fileName);

		if (ujob == null) {
			System.out.println("Error: Missing job file: " + fileName + "\n");
			return null;
		}
		return ujob;
	}

	private static double calculateRoutTime(RoutPathObj[] resultArray) {
		// TODO Auto-generated method stub
		System.out.println("the rout time takes very long...");

		RoutTable rt = new RoutTable();
		// String myRoutTDB = "u:/ucamusers/java/ucam/home/workspace/JavaTests/frs.tbl";
		String myRoutTDB = "u:/ucamusers/java/ucam/home/workspace/URout923b/frs.tbl";

		RoutTool[] myResult = rt.getRoutToolObjTable(myRoutTDB);

		System.out.print("dia: " + myResult[0].dia + "\t");
		System.out.print("speed: " + myResult[0].speed + "\t");
		System.out.print("feed: " + myResult[0].feed + "\t");
		System.out.println("hfeed: " + myResult[0].hfeed);

		System.out.print("dia: " + myResult[10].dia + "\t");
		System.out.print("speed: " + myResult[10].speed + "\t");
		System.out.print("feed: " + myResult[10].feed + "\t");
		System.out.println("hfeed: " + myResult[10].hfeed);

		return 0.0;
	}

	private static double getStep(double[] pTab) {
		double prev, next, rs;
		for (int i = 0; i < pTab.length; i++) {
			prev = pTab[i];
			next = pTab[i + 1];
			// System.out.println("i: " + i + "\tpTab[i]: " + pTab[i] + "\tprev: " + prev + "\tnext: " + next);

			if (next - prev >= 0) {
				rs = Math.round((next - prev) * 100) / 100.00; // zaokraglenie
				return rs;
			}
			// System.out.println("i: " + i + "\tpTab[i]: " + pTab[i]);
		}
		return 0;
	}

	public static void main(String[] args) {

		// String myLayerStr = "fr";
		String myLayerStr = "outline";

		UcamMain.setup(); // inaczej java.lang.UnsatisfiedLinkError

		System.out.println("calculating...");
		// Ujob ujob = readJob("130", "11504", true); // true na koncu czyli panel (_p)
		// Ujob ujob = readJob("130", "11484", true);
		// Ujob ujob = readJob("130", "11310", true);
		// Ujob ujob = readJob("130", "11643", true);
		// Ujob ujob = readJob("284", "040", true);
		// Ujob ujob = readJob("530", "10839", true);
		// Ujob ujob = readJob("103", "523", true);
		// Ujob ujob = readJob("2073", "11855", true);
		// Ujob ujob = readJob("2073", "11859", true);
		// Ujob ujob = readJob("2073", "11933", true);
		// Ujob ujob = readJob("2073", "11962", true);
		// Ujob ujob = readJob("623", "406", true);
		// Ujob ujob = readJob("524", "621", true);
		Ujob ujob = readJob("2073", "12023", true);

		if (ujob == null) {
			System.out.println("Error: Invalid job file.");
			return;
		}

		String myLayer_p = myLayerStr + "_p";
		Ulayer layer = ujob.getlayerbyname(myLayer_p);
		if (layer == null) {
			myLayerStr = myLayerStr.substring(0, myLayerStr.length() - 2);
			layer = ujob.getlayerbyname(myLayerStr);
			if (layer == null) {
				System.out.println("Error: Missing layer " + myLayerStr + " or " + myLayer_p + ". Exit.");
				return;
			}
		}
		layer.load();
		System.out.println("layer " + layer.name() + " is loaded: " + layer.is_loaded());

		layer.setactive(true);
		System.out.println("layer " + layer.name() + " is active: " + layer.active());

		Ucamobj.cO.setunit("mm");
		// System.out.println("aktualna jednostka: " + Ucamobj.cO.unit());

		// ilosc uzytkow
		// System.out.println("ilosc uzytkow: " + (layer_fr.getObjAttrLastValueIx() + 1));

		BlockInfo bi = new BlockInfo();
		Uape myFirstApe = layer.firstape();
		double panelx = Math.round((myFirstApe.enclosingbox().xsize() - myFirstApe.dimension().xsize()) * 100) / 100.00;
		double panely = Math.round((myFirstApe.enclosingbox().ysize() - myFirstApe.dimension().ysize()) * 100) / 100.00;

		System.out.println("panel: " + panelx + " / " + panely);
		bi.dispOutlineApe(myFirstApe, layer.numapes());			// rekursja
		int pcbs = bi.getcPnlpPnl() * (bi.getStepcustpnlx() * bi.getStepcustpnly());
		System.out.println("pcbs: " + bi.getcPnlpPnl() + " x (" + bi.getStepcustpnlx() + " x " + bi.getStepcustpnly() + ") = " + pcbs);
		
		System.out.println("panel_size: " + bi.getSizepnlx() + " / " + bi.getSizepnly());
		System.out.println("pcb_size: " + bi.getSizepcbx() + " / " + bi.getSizepcby());

		// ------------------------------------------------------
		// RoutPathObj rpo = new RoutPathObj();
		// RoutPathObj[] resultArray = rpo.myReportLayer(layer, "all");
		//
		// int lenRoutPathObj = resultArray.length;
		// System.out.println("lenRoutPathObj: " + lenRoutPathObj);
		// int i = 0;
		//
		// do {
		// if (resultArray[i] != null) {
		// // System.out.print("tool: " + resultArray[i].mytool + "\t");
		// // System.out.print("dia: " + resultArray[i].mydia + " [mm] \t");
		// // System.out.println("path: " + resultArray[i].mylength + " [mm]");
		// i++;
		// }
		// lenRoutPathObj--;
		// } while (lenRoutPathObj > 0);

		// calculateRoutTime(resultArray);

		System.out.println("koniec programa..");
	}
}
