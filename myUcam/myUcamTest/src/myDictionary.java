import com.barco.ets.ucam.dtl.DTLBuiltin;
import com.barco.ets.ucam.dtl.Dict;

public class myDictionary {

	public static void main(String[] args) {

		Dict myDict = new Dict(20);
		myDict.set(1, "cir");		// nie ma indexu 0
		myDict.set(2, "square");
		myDict.set(3, "thermal");
		System.out.println(myDict.getString(2));
		myDict.typa();

		Dict myDict2 = new Dict(20);
		myDict2.set("mape", "Mape");
		myDict2.set("wessel", "Wessel");
		myDict2.set("posalux", "Posalux");
		myDict2.set("hitachi", "Hitachi");
		myDict2.set("sum1000", "Sieb & Meyer 1000");
		myDict2.set("sum3000", "Sieb & Meyer 3000");
		myDict2.set("trudrill", "Trudrill");
		myDict2.set("excellon1", "Excellon 1");
		myDict2.set("excellon2", "Excellon 2");
		System.out.println(myDict2.getString(2));
		// myDict2.typa();

		Dict myDict3 = new Dict(20);
		myDict3.set(1, 2.1);
		myDict3.set(4, 1.2);
		myDict3.set(6, 0.4);
		myDict3.set(5, 1.6);
		myDict3.set(3, 3.1);
		myDict3.set(7, 1.7);
		myDict3.set(2, 3.3);

		Dict myDict4 = new Dict(20);
		myDict4 = DTLBuiltin.sort(myDict3);
		System.out.println(myDict3.getDouble(1));
		System.out.println(myDict3.getDouble(2));
		System.out.println(myDict3.getDouble(3));

		String huj = "i_huj_ci_w_dupe";
		System.out.println(huj);

		System.out.println(huj.substring(6));
		CharSequence target = "_w_dupe";
		CharSequence replacement = "_do_pizdy";
		System.out.println(huj.replace(target, replacement));

		String str;
		str = huj.substring(0, huj.length() - 7);
		System.out.println(str);

	}

}
