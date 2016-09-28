
public class Smietnisko {

	
	/*
				public static boolean isFloatingEqual(float v1, float v2) {
				    if (v1 == v2)
				        return true;
				    float absoluteDifference = Math.abs(v1 - v2);
				    float maxUlp = Math.max(Math.ulp(v1), Math.ulp(v2));
				    return absoluteDifference < 5 * maxUlp;
				}
				
				if (java.lang.Float.compare(spindleSpeed, 0) == 0) {
					System.out.println("spindleSpeed = 1");
					spindleSpeed = 1;
				}


*/

	
/*
	private static double[] reWrite2double2(String[] myStrArr) {
		int i = 0;
		int j;

		String[] myTmpArr = new String[16]; // bo 16 kolumn w frs.tbl
		do {
			if (myStrArr[i].equals("")) {
				for (j = i; j < myStrArr.length - 1; j++) {
					myStrArr[j] = myStrArr[j + 1];
					myStrArr[j + 1] = "nul"; // trza jeszcze jakos zaznaczyc koniec.
				}
				i--;
			}
			i++;
		} while (i < myStrArr.length);

		// przepisanie tablic
		double myDouble;
		double[] myDoubleArr = new double[myTmpArr.length];
		for (j = 0; j < myTmpArr.length; j++) {
			if (j == 2) {
				myDouble = Double.valueOf(myStrArr[j]); // string2double
				myDoubleArr[j] = myDouble;
			}
		}

		return myDoubleArr;
	}


	private static String[] reWrite(String[] myStrArr) {
		String[] myNewStrArr = new String[myStrArr.length];
		// wyswielenie
		int i, j = 0;
		for (i = 0; i < myStrArr.length; i++) {
			if (!myStrArr[i].equals("")) {
				myNewStrArr[j] = myStrArr[i];
				j++;
			}
		}
		return myNewStrArr;
	}


// String splitStr = " 43. # jakis 0.887 sztr34ing 12.";
		// String regex = "[^0-9.]"; // wyklucza litery z podzialu
		// String regex = "[^a-z]"; // wyklucza cyfry z podzialu
		// String regex = "[a-z#. -]"; // wyklucza litery, hasza, kropke z podzialu


*/
}
