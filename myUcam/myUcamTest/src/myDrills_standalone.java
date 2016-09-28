// u:\\ucamusers\java\\ucam\home\Workspace\myUcamTest\src 

import java.util.Vector;

import javax.swing.JOptionPane;

import com.barco.ets.ucam.hypertool.UcamMain;
import com.barco.ets.ucam.hypertool.Ucamobj;
import com.barco.ets.ucam.hypertool.Ucirape;
import com.barco.ets.ucam.hypertool.Ujob;
import com.barco.ets.ucam.hypertool.Ulayer;
import com.barco.ets.ui.treetable.VectorData;
import com.barco.ets.ui.treetable.VectorSorter;

public class myDrills_standalone {

	public static boolean doIncrease;
	public static String myUnit;

	// cuts last 4 signs in the name of the layer
	static void changeLayerName(Ulayer myLayer) {
		String lname;
		lname = myLayer.name();
		myLayer.setname(lname.substring(0, lname.length() - 4));
	}

	private static Ujob readJob(String cust, String custno) {

		String jobNumber = cust + "-" + custno;
		String fileName = "u:\\jobs\\" + cust + "\\" + jobNumber + "\\" + jobNumber + "_p.job";

		Ujob ujob;
		ujob = Ujob.cO.read(fileName);

		if (ujob == null) {
			System.out.println("Error: Missing job file: " + fileName + "\n");
			return null;
		}

		else {
			System.out.println("pojszlo..");
		}

		return ujob;
	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatching thread:
		// creating and showing this application's GUI.

		System.out.println("start programa..");

		UcamMain.setup();
		// UcamMain.loadLibraries(); 			// zaladuj deelelke - nie trza gdy jest UcamMain.setup()

		// Ucamapp.cO.setunit("mm"); 			// wywala
		// Ucamobj.cO.setunit("mm"); 			// ustaw aktualny unit na milsy

		// JOptionPane.showMessageDialog(null, "this is a pop up message");
		//		JOptionPane.showMessageDialog(null, "alert", "alert", JOptionPane.ERROR_MESSAGE);
		//		JOptionPane.showConfirmDialog(null, "choose one", "choose one", JOptionPane.YES_NO_OPTION);

		Object[] options = { "With", "Without" };
		int result = JOptionPane.showOptionDialog(null, "Sorting with or without \nincreasing holes?", "Question",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

		if (result == 0) {
			doIncrease = true;
			//			String mesecz = "Pressed " + options[0];
			//			JOptionPane.showMessageDialog(null, mesecz);
		} else if (result == 1) {
			doIncrease = false;
			//			String mesecz = "Pressed " + options[1];
			//			JOptionPane.showMessageDialog(null, mesecz);
		}

		Ujob myJob;
		// Ujob myJob = readJob("109", "187");

		// podaje aktualne jednostki
		myUnit = Ucamobj.cO.unit();
		System.out.println("Actual unit: " + myUnit);

		String fileName = "U:\\jobs\\109\\109-187\\109-187_s.job";
		myJob = Ujob.cO.read(fileName);

		if (myJob == null) {
			System.out.println("kucha.");
			return;
		}

		// MyUcamUtils.changeAllLayerNames(myJob);

		// wyswietle sciezke i nazwe dzoba:
		System.out.println("Job: " + myJob.path() + myJob.name());

		Ulayer[] myLayers = MyUcamUtils.getLayerList(myJob);

		int index = MyUcamUtils.getLayerIndexByName(myLayers, "dr");
		if (index == -1) {
			System.out.println("index: " + index);
			return;
		}
		
		

		Ulayer myLayer = myLayers[index];
		boolean isLoaded = myLayer.is_loaded();
		if (isLoaded != true) {
			myLayer.load();
		}

		// incDrills(myLayer);
		// System.out.println("pszet:");
		// Ucirape[] myHoles = MyUcamUtils.getHoles(myLayer);
		// for (int i = 1; i < myHoles.length; i++) {
		// System.out.println(i + ": " + myHoles[i].outer());
		// }

		if (!myLayer.active()) {
			myLayer.setactive(true);
		}

		// System.out.println("pszet:");
		// Uape[] myHoles2 = MyUcamUtils.getHoles2(myLayer);
		// for (int i = 1; i < myHoles2.length; i++) {
		// System.out.println(i + ": " + myHoles2[i].dimension().xsize());
		// }
		//
		// System.out.println(myHoles2[7].num() + ": " + myHoles2[7].dimension().xsize() + " \t\t " + myHoles2[1].num()
		// + ": " + myHoles2[1].dimension().xsize());
		// myLayer.apeinsertbefore(myHoles2[7], myHoles2[1]);
		//
		// System.out.println("ipo:");
		// myHoles2 = MyUcamUtils.getHoles2(myLayer);
		// for (int i = 1; i < myHoles2.length; i++) {
		// System.out.println(i + ": " + myHoles2[i].dimension().xsize());
		// }

		// MyUcamUtils.increaseHoles(myLayer, 4);
		//
		// System.out.println("ipo:");
		// myHoles = MyUcamUtils.getHoles(myLayer);
		// for (int i = 1; i < myHoles.length; i++) {
		// System.out.println(i + ": " + myHoles[i].outer());
		// }

		MyUcamUtils.sortHoles(myLayer, myUnit);		// sortuje i zaokragla
		
		Ucirape[] myHolesPrzed = MyUcamUtils.getHoles(myLayer);
		for (int i = 1; i < myHolesPrzed.length; i++) {
			System.out.println(myHolesPrzed[i].num() + ": " + myHolesPrzed[i].outer());
		}

		if (doIncrease == true) {
			MyUcamUtils.increaseHoles(myLayer, myUnit);		// powieksza ale nie zaokragla 
		}
		
		System.out.println("\n");
		
		Ucirape[] myHolesPo = MyUcamUtils.getHoles(myLayer);
		for (int i = 1; i < myHolesPo.length; i++) {
			System.out.println(myHolesPo[i].num() + ": " + myHolesPo[i].outer());

			// System.out.println(myHolesPrzed[i].num() + ": " + myHolesPrzed[i].outer() + "\t\t" + myHolesPo[i].num()
			// + ": " + myHolesPo[i].outer());
		}

		// proponuje wiec DrillTableData
		VectorData myVectorData;
		VectorSorter myVectorSorter;

		// Vector<Double> dri_vector = new Vector<Double>();
		Vector dri_vector = new Vector();

		dri_vector.removeAllElements();
		dri_vector.add(0, 1.1);
		dri_vector.add(1, 0.3);
		dri_vector.add(2, 0.2);
		dri_vector.add(3, 0.15);
		System.out.println("dri_vector.size(): " + dri_vector.size());

		boolean dri_sortAsc;

		// Collections.sort(dri_vector, new DrillComparator(0, dri_sortAsc));

		// AbstractTableModel atm = new DrillTableData();

		// myJob.save();
		System.out.println("koniec programa...");
	}
}
