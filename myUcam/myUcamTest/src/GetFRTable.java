// u:\\ucamusers\java\\ucam\home\workspace\myUcamTest\src 

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GetFRTable {

	public static void main(String[] args) {

		// UcamMain.setup();

		String cust = "506";
		String custno = "102";

		String jobNumber = cust + "-" + custno;
		String fileName = "c:\\jobs\\" + cust + "\\" + jobNumber + "\\" + jobNumber + "_p.job";

		System.out.println("fileName: " + fileName);

		getRoutTools("frs.tbl");

	}

	private static boolean isDigit(String str) {

		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			char c = str.charAt(i);
			if (c < '0') {
				if (c != '.') {
					return false;
				}
			} else if (c > '9') {
				return false;

			}
		}
		// System.out.println(str);
		return true;
	}

	// przepisuje tablice bez pustych stringow
	private static String[] rmEmptyValues(String[] myArray) {
		String[] myNewArray = new String[16];
		int i, j = 0;

		for (i = 0; i < myArray.length; i++) {
			if (!myArray[i].equals("")) {
				myNewArray[j] = myArray[i];
				j++;
			}
		}
		return myNewArray;
	}

	private static void prepareToolArray(String[] myArray) {
		// System.out.println("myArray.length: " + myArray.length);
		int i = 0, j = 0;
		double myDia = 0, myTool = 0, myDouble = 0;
		double[] myDoubleArr = new double[100];
		for (i = 0; i < myArray.length; i++) {
			if (!myArray[i].equals("")) {
				// System.out.println("myArray[i]: " + myArray[i]);
				// System.out.println(i);
				boolean result = isDigit(myArray[i]);
				if (result == true) {
					// System.out.println(i);
					// System.out.println(i + ": " + myArray[i]);
					String myStrValue = myArray[i];
					myDouble = Double.valueOf(myStrValue);
					// System.out.println("[" + i + "]: " + myDouble);

					if (i == 5) {
						System.out.println("");

						myDoubleArr[j] = myDouble;
						System.out.print("[" + i + "]: " + myDouble + "\t");
						j++;
					} else if (i == 17) {
						myDoubleArr[j] = myDouble;
						System.out.print("[" + i + "]: " + myDouble + "\t");
						j++;
					} else if (i == 23) {
						myDoubleArr[j] = myDouble;
						System.out.print("[" + i + "]: " + myDouble + "\t");
						j++;
					}

				}

				// System.out.println("nastepny" + i);
			}
		}
	}

	private static float[] getRoutTools(String myRTDB) {

		float[] myToolArray = new float[321];
		// String myTDB = "u:/ucamusers/java/ucam/home/workspace/myUcamTest/BHEGP.TDB";

		BufferedReader myBR;
		FileReader myFR;

		try {
			myFR = new FileReader(myRTDB);
			myBR = new BufferedReader(myFR);

			int beginIndex, endIndex;
			Float myFloat;
			int index = 0;

			String line;
			while ((line = myBR.readLine()) != null) {

				int i = 0;
				if (line.charAt(0) != '#') {
					// System.out.println(line);

					String regex = "[^0-9.]";
					String[] myArray = line.split(regex);
					String[] properArray = rmEmptyValues(myArray);

					// sprawdzenie
					for (i = 0; i < properArray.length; i++) {
						System.out.print("[" + i + "]: " + properArray[i] + " ");
					}
					System.out.println("");

					// prepareToolArray(properArray); // dla kazdej lini z osobna

					beginIndex = 0;
					endIndex = line.indexOf(' ');
					// System.out.println(endIndex);

					// String sMyTool = line.substring(beginIndex + 1, endIndex - 1);
					// System.out.println(sMyTool);
				}

				//				if (!sMyTool.equals("")) {
				//					myFloat = Float.valueOf(sMyTool);
				//					myToolArray[index] = myFloat;
				//				} else {
				//					myToolArray[index] = myToolArray[index - 3];
				//				}
				//				index++;
				//
				//				// speed
				//				beginIndex = endIndex;
				//				endIndex = line.indexOf('F');
				//				String sMySpeed = line.substring(beginIndex + 1, endIndex);
				//
				//				if (!sMySpeed.equals("")) {
				//					myFloat = Float.valueOf(sMySpeed);
				//					myToolArray[index] = myFloat;
				//				} else {
				//					myToolArray[index] = myToolArray[index - 3];
				//				}
				//				index++;
				//
				//				// feed
				//				beginIndex = endIndex;
				//				endIndex = line.indexOf('R');
				//				String sMyFeed = line.substring(beginIndex + 1, endIndex);
				//				if (!sMyFeed.equals("")) {
				//					myFloat = Float.valueOf(sMyFeed);
				//					myToolArray[index] = myFloat;
				//				} else {
				//					myToolArray[index] = myToolArray[index - 3]; // wez od poprzednika
				//				}
				//				index++;
			}
			myBR.close();

		} catch (FileNotFoundException e) {
			System.out.println("Error: missing TDB file." + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		}

		// display whole array
		int i = 0;
		do {
			// System.out.print(myToolArray[i] + "\t");
			i++;
			if (i % 3 == 0) {
				// System.out.println();
			}
		} while (i < myToolArray.length);

		return myToolArray;
	}

}
