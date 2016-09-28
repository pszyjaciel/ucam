import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class IniFile {

	private String myFile;
	private HashMap<String, Double> map;

	public IniFile(String myFile) {
		// constructor
		this.myFile = myFile;
		this.map = new HashMap<String, Double>();
	}

	// zwraca tablice key-value
	public HashMap<String, Double> readIniFile() {

		String sMyKey;
		String sMyValue;
		double myDouble;

		BufferedReader br;
		FileReader fr;

		try {
			fr = new FileReader(myFile);
			br = new BufferedReader(fr);

			int beginIndex, endIndex;
			String line;
			while ((line = br.readLine()) != null) {
				//	System.out.println(line);

				beginIndex = 0;
				endIndex = line.indexOf('=');

				// gdy spacja to wez nastepny znak
				while (line.charAt(beginIndex) == ' ') {
					beginIndex++;
				}

				// gdy endIndex jest -1 to nie znaleziono znaku 
				if (endIndex != -1) {
					sMyKey = line.substring(beginIndex, endIndex - 1);
					beginIndex = endIndex + 1;
					endIndex = line.length();

					// gdy spacja to wez nastepny znak
					while (line.charAt(beginIndex) == ' ') {
						beginIndex++;
					}

					if (endIndex != -1) {
						sMyValue = line.substring(beginIndex, endIndex);
						myDouble = Double.valueOf(sMyValue);

						map.put(sMyKey, myDouble); // wstaw klucz-wartosc
					}
				}
			}
			br.close();

		} catch (NumberFormatException ex) {
			System.out.println("Error: config.ini has wrong format." + "\n");

		} catch (FileNotFoundException e) {
			System.out.println("Error: config.ini cannot be found." + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
