// gdy ucam.jar to jre 1.6, gdu classes.jar to jre 1.5
// gdy unsatisfied custam to znaczy ze zamiast ucam.jar (v9.2.3) jest classes.jar z v8.1

import com.barco.ets.ucam.ee.EnhancedEditConstruct;
import com.barco.ets.ucam.ee.EnhancedEditMath;
import com.barco.ets.ucam.hypertool.Displaypar;
import com.barco.ets.ucam.hypertool.Uape;
import com.barco.ets.ucam.hypertool.Uapeobj;
import com.barco.ets.ucam.hypertool.UcamMain;
import com.barco.ets.ucam.hypertool.Ucamapp;
import com.barco.ets.ucam.hypertool.Ucamv6;
import com.barco.ets.ucam.hypertool.Ulayer;
import com.barco.ets.ucam.hypertool.Upoint;

public class MyUpoint {

	public static void main(String[] args) {

		// UcamMain.setup();		// zakomentowac, gdy wykonuje z consoli ucama

		Upoint circlePoint2 = new Upoint(20.5, 10.5);
		Upoint circlePoint3 = new Upoint(40.5, 0.6);
		Upoint lineStart;
		double j;

		j = Ucamv6._PCT_inch();
		System.out.println("Ucamv6._PCT_inch(): " + j);

		for (double i = 0; i < 5; i++) {
			j = (i / 10);
			Upoint circlePoint1 = new Upoint(0.5 + (j / 10), 0.5 - (j / 10));
			lineStart = EnhancedEditMath.threePointCircleCenter(circlePoint1, circlePoint2, circlePoint3);
			System.out.println(lineStart.x() + " : " + lineStart.y()); // oba dzialaja
			System.out.println(lineStart.x + " : " + lineStart.y);
		}

		double x1 = 141.54099; // koordynaty do istniejacych obiektow
		double y1 = 84.27;
		Upoint p1 = new Upoint(x1, y1);

		double x2 = 245.491;
		double y2 = 84.27;
		Upoint p2 = new Upoint(x2, y2);

		Displaypar dsp = Ucamapp.cO.curdsp();	// dziala ale spod consoli ucama, inaczej wywala.
		double radius = dsp.stow_val(5);

		Ulayer lay = Ucamv6.ucam_job.lay_in_plane(1);
		if (lay == null) {
			return;
		}

		Uapeobj apeobj1 = lay.closestobj(p1, radius);
		Uapeobj apeobj2 = lay.closestobj(p2, radius);

		// Calculate the clearance when 2 objects were selected.
		if ((apeobj1 != null) && (apeobj2 != null)) {
			double myClearance = apeobj1.clearance_to(apeobj2);
			Upoint myUpoint = apeobj1.closest_pnt(p1);
			Upoint myConvertedUpoint = myUpoint.wtos(dsp); // konversja na punkty ekranowe

			System.out.println(myUpoint.x + " : " + myUpoint.y);
			System.out.println(myConvertedUpoint.x + " : " + myConvertedUpoint.y);

			System.out.println(myClearance);
		} else {
			System.out.println("apeobj jest nullem.");
		}

		// dziala!
		Uape uape = lay.firstape();
		Uapeobj uapeobj = null;
		if (uape != null)
			uapeobj = uape.firstobj();
		if (uape == null || uapeobj == null) {
			System.out.println("internal error, hehe.");
		}

		Upoint startPoint = new Upoint(10.0, 100.0);
		Upoint endPoint = new Upoint(85.9, 99.9);

		EnhancedEditConstruct eec = new EnhancedEditConstruct();
		eec.drawFinalLine(startPoint, endPoint, uapeobj, "jakis_sztrink");

		// wniosek: po expandowaniu warstwy dr_p optymalizacja sciezki podaje prawdziwa wartosc. 
		// (no prawie ok. roznica ok 1m)

	}

}
