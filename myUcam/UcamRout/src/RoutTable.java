import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RoutTable {

	private static void printDoubleArray(double[] myStrArr) {
		for (int i = 0; i < myStrArr.length; i++) {
			System.out.println("[" + i + "]: " + myStrArr[i]);
		}
		System.out.println("");

	}

	// zwraca tabele 4-elementowa: dia, speed, feed, hfeed
	private static double[] toolMap(String[] myStrArr) {
		int i = 0;
		int j = 0;
		int k = 0;

		double[] myDoubleArr = new double[4]; // bo 16 kolumn w frs.tbl
		do {
			if (!myStrArr[i].equals("")) {
				if (j == 2) {
					myDoubleArr[k] = Double.valueOf(myStrArr[i]); // string2double
					k++;
				} else if (j == 5) {
					myDoubleArr[k] = Double.valueOf(myStrArr[i]); // string2double
					k++;
				} else if (j == 6) {
					myDoubleArr[k] = Double.valueOf(myStrArr[i]); // string2double
					k++;
				} else if (j == 10) {
					myDoubleArr[k] = Double.valueOf(myStrArr[i]); // string2double
					k++;
				}
				j++;
			}
			i++;
		} while (i < myStrArr.length);

		return myDoubleArr;
	}

	private static double[][] getRoutToolTable(String myRoutTDB) {

		BufferedReader br;
		FileReader fr;

		int i = 0;

		double[] myTmpArr;
		double[][] myToolArr = new double[100][];

		String regex = "[^0-9.]"; // wyklucza litery z podzialu

		try {
			fr = new FileReader(myRoutTDB);
			br = new BufferedReader(fr);

			String line;
			while ((line = br.readLine()) != null) {
				// System.out.println(line);

				String[] mySplitStrArr = line.split(regex); // podziel 1 linie

				if (mySplitStrArr.length > 70) {
					myTmpArr = toolMap(mySplitStrArr); //
					printDoubleArray(myTmpArr);

					myToolArr[i] = myTmpArr;
					i++;
				}
			}
			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("Error: missing file: " + myRoutTDB + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return myToolArr;

	}

	// zwraca tablice obiektow (to samo co toolMap() ale na obiektach)
	public RoutTool[] getRoutToolObjTable(String myRoutTDB) {

		BufferedReader br;
		FileReader fr;
		RoutTool[] myRoutToolArr = new RoutTool[100];
		RoutTool rt = new RoutTool();

		String regex = "[^0-9.]"; // wyklucza litery z podzialu
		int i = 0;

		try {
			fr = new FileReader(myRoutTDB);
			br = new BufferedReader(fr);

			String line;
			while ((line = br.readLine()) != null) {
				// System.out.println(line);

				String[] mySplitStrArr = line.split(regex); // podziel 1 linie

				if (mySplitStrArr.length > 70) {
					myRoutToolArr[i] = rt.toolParam(mySplitStrArr);
					// printDoubleArray(myTmpArr);
					i++;
				}
			}
			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("Error: missing file: " + myRoutTDB + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return myRoutToolArr;
	}
}
