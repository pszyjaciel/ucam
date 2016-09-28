import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.barco.ets.ucam.hypertool.Uape;
import com.barco.ets.ucam.hypertool.Uextlayer;
import com.barco.ets.ucam.hypertool.Ujob;
import com.barco.ets.ucam.hypertool.Ulayer;
import com.barco.ets.ucam.hypertool.Utxtape;

public class Calculate {

	private JTextArea textArea;
	private JLabel actionLabel;

	public Calculate(JTextArea jta, JLabel jal) {
		// constructor
		this.textArea = jta;
		this.actionLabel = jal;
	}

	private int countPCB(Ujob ujob) {

		Ulayer myLayer = ujob.getlayerbyname("outline_p");
		if (myLayer == null) {
			textArea.append("Error: layer does not exists? \n");
			return 0;
		} else {
			myLayer.setactive(true); // uaktywnij przed jakakolwiek operacja - inaczej wywala w c
		}

		int k1 = myLayer.getObjAttrNameIx("uImage");
		// System.out.println("k1: " + k1); // daje -3
		if (k1 >= 0) {
			myLayer.getObjAttrValue(k1);

			k1 = myLayer.getObjAttrLastNameIx();
			myLayer.getObjAttrValue(k1);

			k1 = myLayer.getObjAttrLastValueIx();
			k1++; // trzeba zwiekszyc o 1.
		}
		return k1;
	}

	private float findFeed(float[] myToolArray, float tool) {
		// tu jest kucha dla toola 3.3
		int tLength = myToolArray.length;
		for (int i = 0; i < tLength; i += 3) {
			if (myToolArray[i] == tool) {
				// System.out.print("tool: " + myToolArray[i] + "\t");
				// System.out.print("speed: " + myToolArray[i + 1] + "\t");
				// System.out.print("feed: " + myToolArray[i + 2] + "\n");
				return myToolArray[i + 2];
			}
		}

		return 1 / 10; // uwaga: dzielenie przez 0!
	}

	/**
	 * Compare to floats for (almost) equality. Will check whether they are
	 * at most 5 ULP apart.
	 */
	public static boolean isFloatingEqual(float v1, float v2) {
		if (v1 == v2)
			return true;
		float absoluteDifference = Math.abs(v1 - v2);
		float maxUlp = Math.max(Math.ulp(v1), Math.ulp(v2));
		return absoluteDifference < 5 * maxUlp;
	}

	public Uextlayer findDroLayer(Ujob ujob) {
		Uextlayer uextra = null;
		int layNumber;
		int j1 = ujob.numlayers("extra");
		for (layNumber = 1; layNumber <= j1; layNumber++) {
			uextra = (Uextlayer) ujob.getlayer("extra", null, layNumber);
			if (uextra.subclass().equals("dro") && uextra.name().endsWith("_dro")) {
				// textArea.append("dro exists. layer number: " + layNumber + "\n");
				// uextra.setplane(1);
				return uextra;
			}
		}
		return null;
	}

	// typ 17 to blok
	public void displayAppes(Ulayer myLayer) {
		int napes = myLayer.numapes();
		for (int n = 1; n <= napes; n++) {
			Uape myApe = myLayer.getape(n);
			System.out.println(n + " : " + myApe.num() + " : " + myApe.getType() + " : " + myApe.name());
		}
	}

	public void deleteDro(Ujob ujob) {

		Uextlayer uextra = null;
		int layNumber;
		int j1 = ujob.numlayers("extra");
		ujob.setactive("all", 0);

		for (layNumber = 1; layNumber <= j1; layNumber++) {
			uextra = (Uextlayer) ujob.getlayer("extra", null, layNumber);
			if (uextra.subclass().equals("dro") && uextra.name().endsWith("_dro")) {
				textArea.append("dro exists and will be deleted now. \n");
				uextra.setactive(true);
			}
		}
		textArea.append("ujob.activelayers(): " + ujob.activelayers() + "\n");
		uextra.erase("all");
		uextra.ape_clean();
		// ujob.erase();
	}

	// Feed per Revolution (fr) = Feed Speed of the Main (vf) / Rotational Speed of the Main Spindle (n)
	// zwraca posuw na obrot w [m/min]
	private float findSpindleFeed(float[] myToolArray, float tool) {

		// Drilling time = (HoleDepth * numberOfHoles) / (SpindleSpeed * FeedPerRevolution)

		float fpr = 0;
		float spindleFeed;
		float spindleSpeed;
		float ssfpr;

		int tLength = myToolArray.length;
		for (int i = 0; i < tLength; i = i + 3) {
			if (myToolArray[i] == tool) {

				spindleFeed = myToolArray[i + 2];
				if ((int) spindleFeed == 0) {
					spindleFeed = 1;
				}

				spindleSpeed = myToolArray[i + 1]; // [m/min]
				if ((int) spindleSpeed == 0) {
					spindleSpeed = 1;
				}

				fpr = spindleFeed / spindleSpeed;
				ssfpr = spindleSpeed * fpr;

				return ssfpr;
			}
		}
		return 1; // uwaga: dzielenie przez 0!
	}

	public void calculateDrillTime(String cust, String custno) {

		// Log4Ucam mylogger = Log4Ucam.getLogger();
		// mojTest(cust, custno); // tu sa moje testy

		textArea.setText(null); // czyszczenie okna text area

		String jobNumber = cust + "-" + custno;
		String fileName = "u:\\jobs\\" + cust + "\\" + jobNumber + "\\" + jobNumber + "_p.job";

		textArea.append("Drill time for: " + fileName + "\n");
		Ujob ujob;
		ujob = Ujob.cO.read(fileName);
		if (ujob == null) {
			Color fg = new Color(255, 0, 0);
			actionLabel.setForeground(fg);
			actionLabel.setText("Wrong number.");
			textArea.append("Error: Missing job file: " + fileName + "\n");
			return;
		}

		// takie tam moje..
		//	int ile = countPCB(ujob);
		//	textArea.append("PCBs on panel: " + ile + "\n");

		Color fg = new Color(0, 0, 0);
		actionLabel.setForeground(fg);
		actionLabel.setText("calculating..");

		// powiem krotko: jak dro istnieje to trza go skasowac. 
		Uextlayer udro = findDroLayer(ujob);
		if (udro == null) {
			// textArea.append("Info: udro does not exists. \n");
		} else {
			textArea.append("Info: udro exists and will be deleted now. \n");
			ujob.deletelayer(udro);
		}

		// Ulayer dr = (Ulayer) ujob.getlayer("drill", "drill", 1);
		Ulayer dr_p = ujob.getlayerbyname("dr_p");
		if (dr_p == null) {
			textArea.append("Error: layer does not exists? \n");
			return;
		}

		ujob.setactive("all", 0);
		dr_p.setactive(true); // uaktywnij przed optymalizacja
		dr_p.load();
		dr_p.block_expand();
		// ujob.drill_thermal_optimise(1, 4, "all", 1, 1, 1, 10000000); // po optymalizacji powstaje dro i jest aktywne.
		ujob.drill_optimise(1, 4, "all"); // tworzy warstwe dro (aktywna)
		// System.out.println("extra layers after optimalization: " + ujob.numlayers("e"));

		dr_p.setactive(false); // dro pozostaje aktywne 
		udro = findDroLayer(ujob);
		udro.load();
		// displayAppes(udro);

		float[] tool = new float[120];
		int[] path = new int[120];
		int[] pads = new int[120];
		int pos = 0;

		Uape myApe;
		Float myFloat;
		int napes = udro.numapes();
		// textArea.append("napes: " + napes + "\n");

		for (int n = 1; n <= napes; n++) {
			myApe = udro.getape(n);
			if (myApe.is_it("TXT")) {
				Utxtape myUtxtape = (Utxtape) udro.getape(n);
				String myString = myUtxtape.string();
				if (myString.endsWith("MM")) {
					textArea.append(myString + "\n");

					// new path
					int elif = myString.lastIndexOf(" MM");
					int blif = myString.lastIndexOf(" ", elif - 1);
					String myValue = myString.substring(blif + 1, elif);
					Integer myInt = Integer.valueOf(myValue);
					path[pos] = myInt;
					// System.out.println("new path: " + myInt + " mm\t");

					// old path
					elif = myString.lastIndexOf(" MM", blif);
					blif = myString.lastIndexOf(" ", elif - 1);
					myValue = myString.substring(blif + 1, elif);
					myInt = Integer.valueOf(myValue);
					// System.out.println("old path: " + myInt + "\t");

					// pads
					elif = myString.lastIndexOf(" MM", blif);
					if (elif != -1) {
						elif = elif + 11;
						blif = myString.lastIndexOf(" ", elif - 1);
						myValue = myString.substring(blif + 1, elif);
						myInt = Integer.valueOf(myValue);
						pads[pos] = myInt;
						// System.out.println("pads: " + myInt + " \t");
					}

					// tool
					elif = myString.lastIndexOf(" MM", blif);
					blif = myString.lastIndexOf(" ", elif - 1);
					if (blif != -1 || elif != -1) {
						myValue = myString.substring(blif + 1, elif);
						myFloat = Float.valueOf(myValue);
					} else {
						myFloat = (float) 0.0;
					}
					tool[pos] = myFloat;
					// System.out.println("tool: " + myFloat + " mm");

					pos++; // wskaznik na kolejny element
				}
			}

			actionLabel.setText("Done.");
		}

		// wyliczenie czasu - u:\\ucamusers\java\\ucam\home\workspace\UOptimize\drilltime2.xls 
// http://www.mitsubishicarbide.net/contents/mmus/enus/html/product/technical_information/information/formula3.html
		// spindle feed: posuw wrzeciona
		// Feed per Revolution: posuw na 1 obrot

		// Drilling time = (HoleDepth * numberOfHoles) / (SpindleSpeed * FeedPerRevolution)
		// Drilling time = (HoleDepth * numberOfHoles) / SpindleFeed

		// wartosci domyslne gdy config.ini zawiedzie..
		double IN = 7.95; // i to jest wielka niewiadoma..
		double PANELS = 3;
		double PANEL_THICKNESS = 1.6;
		double ALU_PLATE = 0.25;
		double OUT = 0.8;
		double POSITION_SPEED = 24 * 1000 / 60; // [mm/sec];
		double BACKFEED = 20 * 1000 / 60;
		double TIME_TOOL_CHANGE = 30;
		double TIME_LASER_CHECK = 15;

		double oneToolPositionTime, drill_hight;
		double oneToolDrillTime, drill_time, time = 0;
		double time_all;
		float SpindleFeed, ssfpr;
		int numberOfHoles;

		// wczytaj wartosci z config.ini i przyporzadkuj do zmiennych
		IniFile mif = new IniFile("config.ini");
		HashMap<String, Double> myHashMap = mif.readIniFile();

		try {
			IN = myHashMap.get("IN");
			PANELS = myHashMap.get("PANELS");
			PANEL_THICKNESS = myHashMap.get("PANEL_THICKNESS");
			ALU_PLATE = myHashMap.get("ALU_PLATE");
			OUT = myHashMap.get("OUT");
			POSITION_SPEED = myHashMap.get("POSITION_SPEED");
			BACKFEED = myHashMap.get("BACKFEED");
			TIME_TOOL_CHANGE = myHashMap.get("TIME_TOOL_CHANGE");
			TIME_LASER_CHECK = myHashMap.get("TIME_LASER_CHECK");
		}

		catch (NullPointerException npe) {
			textArea.append("The file config.ini was not found. \nStandard parameters will be used for calculation. \n");
		}

		// przygotuj tablice narzedzi z pliku BHEGP.TDB 
		float[] aTools = getTools("BHEGP.TDB");

		for (int n = 0; n < pos; n++) { // pos to wskaznik do ostatniego elementu

			drill_hight = IN + ALU_PLATE + (PANELS * PANEL_THICKNESS) + OUT;

			// Drilling time = (drill_hight * numberOfHoles) / SpindleFeed
			ssfpr = findSpindleFeed(aTools, tool[n]);
			// System.out.println("ssfpr: " + ssfpr + " [m/min]");

			// SpindleFeed = findFeed(aTools, tool[n]);
			SpindleFeed = ssfpr;

			SpindleFeed = (SpindleFeed * 1000) / 60; // [mm/sec]
			// System.out.println("SpindleFeed: " + SpindleFeed + " [mm/sec]");

			drill_time = (drill_hight / SpindleFeed) + (drill_hight / BACKFEED);
			drill_time = drill_time + (drill_time * 0.01); // 1% wiecej na luzy na wrzecionie
			// System.out.println("drill_time: " + drill_time);

			numberOfHoles = pads[n];
			oneToolDrillTime = drill_time * numberOfHoles;
			// System.out.println("oneToolDrillTime: " + oneToolDrillTime);

			// czas dojazdu
			oneToolPositionTime = path[n] / POSITION_SPEED;
			oneToolPositionTime = oneToolPositionTime + (oneToolPositionTime * 0.01); // 1% wiecej na luzy 
			//System.out.println("oneToolPositionTime: " + oneToolPositionTime);

			time_all = oneToolPositionTime + oneToolDrillTime; // dojazd + wiercenie
			// System.out.println("time_all: " + time_all);

			// dodaj czas na zmiane narzedzia plus czek na laserze ale tylko dla ilosci otworow > 2 
			if (numberOfHoles > 2) {
				time_all = TIME_TOOL_CHANGE + TIME_LASER_CHECK + time_all;
			}

			time = time + time_all;
			// System.out.println("time: " + time);

		}

		long total = Math.round(time);

		int hours = (int) (total / 60 / 60);
		int minutes = (int) (total - (hours * 60 * 60)) / 60;
		int seconds = (int) (total - ((hours * 60 * 60) + (minutes * 60)));

		textArea.append("drill time: " + total + " [sec] \n");
		textArea.append("drill time: " + hours + ":" + minutes + ":" + seconds + " [hh:mm:ss] \n");

		actionLabel.setText(hours + ":" + minutes + ":" + seconds);
		// actionLabel.setText(Long.toString(total) + " sec.");

	}

	private float[] getTools(String myTDB) {

		float[] myToolArray = new float[321];
		// String myTDB = "u:/ucamusers/java/ucam/home/workspace/myUcamTest/BHEGP.TDB";

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

				// tool
				beginIndex = 0;
				endIndex = line.indexOf('S');
				String sMyTool = line.substring(beginIndex + 1, endIndex - 1);
				if (!sMyTool.equals("")) {
					myFloat = Float.valueOf(sMyTool);
					myToolArray[index] = myFloat;
				} else {
					myToolArray[index] = myToolArray[index - 3];
				}
				index++;

				// speed
				beginIndex = endIndex;
				endIndex = line.indexOf('F');
				String sMySpeed = line.substring(beginIndex + 1, endIndex);

				if (!sMySpeed.equals("")) {
					myFloat = Float.valueOf(sMySpeed);
					myToolArray[index] = myFloat;
				} else {
					myToolArray[index] = myToolArray[index - 3];
				}
				index++;

				// feed
				beginIndex = endIndex;
				endIndex = line.indexOf('R');
				String sMyFeed = line.substring(beginIndex + 1, endIndex);
				if (!sMyFeed.equals("")) {
					myFloat = Float.valueOf(sMyFeed);
					myToolArray[index] = myFloat;
				} else {
					myToolArray[index] = myToolArray[index - 3]; // wez od poprzednika
				}
				index++;
			}
			br.close();

		} catch (FileNotFoundException e) {
			textArea.append("Error: missing TDB file." + "\n");

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
