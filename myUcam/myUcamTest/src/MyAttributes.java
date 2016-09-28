// source: u:\\ucamusers\java\\ucam\home\workspace\myUcamTest\src\MyAttributes.java 

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.barco.ets.ucam.hypertool.Ujob;
import com.barco.ets.ucam.hypertool.UcamMain;
import com.barco.ets.ucam.hypertool.Ulayer;
import com.barco.ets.ucam.hypertool.Uobjattrlist;

public class MyAttributes {

	private static float[] getTools() {

		float[] myToolArray = new float[220];
		// String myTDB = "u:/ucamusers/java/ucam/home/workspace/myUcamTest/BHEGP.TDB";
		String myTDB = "BHEGP.TDB";

		BufferedReader br;
		FileReader fr;

		try {
			fr = new FileReader(myTDB);
			br = new BufferedReader(fr);
			int beginIndex, endIndex;
			Float myFloat;
			int index = 0;

			String line;
			while ((line = br.readLine()) != null) {
				// System.out.println(line);

				beginIndex = 0;
				endIndex = line.indexOf('S');
				String sMyTool = line.substring(beginIndex + 1, endIndex - 1);
				if (!sMyTool.equals("")) {
					myFloat = Float.valueOf(sMyTool);
					myToolArray[index] = myFloat;
				} else {
					myToolArray[index] = 0;
				}
				index++;

				beginIndex = endIndex;
				endIndex = line.indexOf('F');
				/*
				String sMySpeed = line.substring(beginIndex + 1, endIndex);

				if (!sMySpeed.equals("")) {
					myFloat = Float.valueOf(sMySpeed);
					myToolArray[index] = myFloat;
				} else {
					myToolArray[index] = 0;
				}
				index++;
				*/

				beginIndex = endIndex;
				endIndex = line.indexOf('R');
				String sMyFeed = line.substring(beginIndex + 1, endIndex);

				if (!sMyFeed.equals("")) {
					myFloat = Float.valueOf(sMyFeed);
					myToolArray[index] = myFloat;
				} else {
					myToolArray[index] = 0;
				}
				index++;
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return myToolArray;

	}

	public static void main(String[] args) {

		// UcamMain.setup();

		String cust = "506";
		String custno = "102";

		String jobNumber = cust + "-" + custno;
		String fileName = "c:\\jobs\\" + cust + "\\" + jobNumber + "\\" + jobNumber + "_p.job";

		System.out.println("fileName: " + fileName);

		/*
		Ujob ujob;
		ujob = Ujob.cO.read(fileName);

		if (ujob == null) {
			System.out.println("Error: Missing job file: " + fileName + "\n");
			return;
		}

		Ulayer dr_p = ujob.getlayerbyname("dr_p");
		if (dr_p == null) {
			System.out.println("Error: layer does not exists? \n");
			return;
		}

		System.out.println(dr_p.name());

		// Uobjattrlist uobjattrlist = dr_p.apeAttributes();
		 */

		float[] aTools = getTools();
		int aLength = aTools.length;
		for (int i = 0; i < aLength; i++) {
			if (i % 2 == 0) {
				// System.out.println("");
			}

			// System.out.print(aTools[i] + "\t");

		}

		// przyklad z 'if - continue'
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				System.out.println(i);
				System.out.println("parzyste");
				continue;		// pomin kolejne 2 linie i idz do nastepnego 'for'
			}

			System.out.println("i: " + i);
			System.out.println("nie-parzyste");
		}

		
		double next = 13.1112;
		double prev = 7.8889;

		double rs = Math.round((next - prev) * 100) / 100.00; // zaokraglenie
		System.out.println(rs);

	}
}

// parametry narzedzi tutaj: u:\pk\bor\bak_pc\Smwdata\TDB\BHEGP.TDB

// -------------------------------------------------
// ------------------ smietnik -------------------

// String myNewTool = myTool.replace(".", ",");
// System.out.println(myNewTool);

