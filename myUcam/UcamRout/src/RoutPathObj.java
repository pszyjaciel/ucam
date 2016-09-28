import com.barco.ets.ucam.hypertool.Uape;
import com.barco.ets.ucam.hypertool.Ucirape;
import com.barco.ets.ucam.hypertool.Uextlayer;
import com.barco.ets.ucam.hypertool.Ulayer;
import com.barco.ets.ucam.hypertool.Urout;

public class RoutPathObj {

	public int mytool;
	public double mydia;
	public double mylength;

	RoutPathObj[] myRoutToolArray = new RoutPathObj[10];

	public RoutPathObj() {
		// pusty konstruktor
	}

	public RoutPathObj(int tool, double dia, double length) {
		// konstruktor
		this.mytool = tool;
		this.mydia = dia;
		this.mylength = length;

	}

	private void printArray(int length) {
		// sprawdzenie
		int lenRoutPathObj = length;
		System.out.println("myRoutToolArray.length: " + myRoutToolArray.length);
		int i = 0;

		do {
			System.out.print("tool: " + myRoutToolArray[i].mytool + "\t");
			System.out.print("dia: " + myRoutToolArray[i].mydia + "\t");
			System.out.println("path: " + myRoutToolArray[i].mylength);
			i++;
			lenRoutPathObj--;
		} while (lenRoutPathObj > 0);
	}

	// na podstawie oryginalnego kodu
	public RoutPathObj[] myReportLayer(Ulayer ulayer, String s) {
		double totalLength = 0.0D;
		if (ulayer == null)
			return null;

		Uextlayer uextlayer = (Uextlayer) Uextlayer.cO.create();
		uextlayer.copy(ulayer, s);
		uextlayer.block_expand();
		uextlayer.ape_group();
		uextlayer.ape_clean();
		Uape uape = uextlayer.firstape();

		System.out.println("uextlayer.numapes(): " + uextlayer.numapes());

		double myApeOuter;
		int j = 0;
		for (int i = 0; i < uextlayer.numapes(); i++) {
			double apeLength = Urout.cO.reportGetApertureLength(uape);
			totalLength += apeLength;
			if (apeLength > 0.0D) {

				mytool = Integer.valueOf(uape.num());

				if (uape.is_it("cir")) {
					myApeOuter = ((Ucirape) uape).outer();
					mydia = Double.valueOf(myApeOuter);
				}

				mylength = Double.valueOf(apeLength);

				myRoutToolArray[j] = new RoutPathObj(mytool, mydia, mylength);
				j++;
			}
			uape = uape.next();
		}

		System.out.println("TOTAL LENGTH: " + Double.valueOf(totalLength));
//		printArray(j);
		return myRoutToolArray;

	}
}
