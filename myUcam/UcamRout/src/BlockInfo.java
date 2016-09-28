import com.barco.ets.ucam.hypertool.Dpfobj;
import com.barco.ets.ucam.hypertool.Uape;
import com.barco.ets.ucam.hypertool.Uapeobj;
import com.barco.ets.ucam.hypertool.Ubloape;
import com.barco.ets.ucam.hypertool.Ulayer;
import com.barco.ets.ucam.hypertool.Upoint;

public class BlockInfo {

	private int level;
	private int cPnlpPnl;				// ile uzytkow w panelu produkcyjnym
	private int pcbcPnl;				// ile plytek w 1 uzytku
	private double sizepcbx;
	private double sizepcby;
	private double sizepnlx;
	private double sizepnly;
	private int stepprodpnlx;			// step x w panelu produkcyjnym
	private int stepprodpnly;			// step y w panelu produkcyjnym
	private int stepcustpnlx;			// step x w uzytku
	private int stepcustpnly;			// step y w uzytku

	private double ppxrepeat;			// repeat x w uzytku
	private double ppyrepeat;			// repeat y w uzytku
	private double cpxrepeat;			// repeat x w uzytku
	private double cpyrepeat;			// repeat y w uzytku

	// konstruktor
	public BlockInfo() {
		level = 0;
		cPnlpPnl = 0;
		pcbcPnl = 0;
		sizepcbx = 0;
		sizepcby = 0;
		sizepnlx = 0;
		sizepnly = 0;
		stepprodpnlx = 0;
		stepprodpnly = 0;
		stepcustpnlx = 0;
		stepcustpnly = 0;
		ppxrepeat = 0;
		ppyrepeat = 0;
		cpxrepeat = 0;
		cpyrepeat = 0;
	}

	// ta metoda bedzie dla warstwy fr.
	// !uwaga: rekursja
	// patrz tutaj: u:\\ucamusers\java\\ucam\home\jucam\com\barco\ets\\ucam\hypertool\Ubpcb.jad
	public BlockInfo dispApe(Uape uape, int ile_ape) {
		double xSize2, ySize2;
		Upoint[] upointTab = new Upoint[32];

		for (int j = 0; j < ile_ape; j++) {

			// System.out.println(uape.toString());
			if ((uape.is_it("blo")) && (level == 0)) {
				cPnlpPnl = uape.numobj();

			} else if ((uape.is_it("blo")) && (level == 1)) {
				// System.out.println(uape.toString());

				pcbcPnl = uape.numobj();

				Uapeobj uapeobj = uape.firstobj();
				int k, m;
				for (k = 0; k < uape.numobj(); k++) {
					Dpfobj dpfobj = uapeobj.obj();		// np. cir moze byc padem, linia, lukiem..
					if (dpfobj.is_it("fla")) {
						// System.out.println(dpfobj.toString());
						upointTab[k] = dpfobj.fp();		// zapisz koordynaty blokow
					}
					uapeobj = uapeobj.next();
				}

				for (m = 0; m < k; m++) {
					xSize2 = Math.round((upointTab[m].x - upointTab[0].x) * 100) / 100.00;
					if (xSize2 == 0) {
						stepcustpnlx = getStepcustpnlx() + 1;		// zwiekszaj x-stepa, dopuki koordynaty rozne
					}
					// ySize2 = Math.round((upointTab[m].y - upointTab[0].y) * 100) / 100.00;
				}
				stepcustpnly = pcbcPnl / getStepcustpnlx();
			}

			// u:\\ucamusers\java\\ucam\home\jucam\com\barco\ets\\ucam\hypertool\Uquerybox.jad

			// rectangla w blocku 1111 nazwac 'coupon'
			// a w ogole to wymiary nalezy zdejmowac z outline'a a nie fr.
			if ((uape.num() == 1) && uape.is_it("cir") && !uape.name().equals("coupon")) {
				sizepnlx = Math.round((uape.enclosingbox().xsize() - uape.dimension().xsize()) * 100) / 100.00;
				sizepnly = Math.round((uape.enclosingbox().ysize() - uape.dimension().ysize()) * 100) / 100.00;

			} else if (uape.num() == 2) {
				sizepcbx = Math.round((uape.enclosingbox().xsize() - uape.dimension().xsize()) * 100) / 100.00;
				sizepcby = Math.round((uape.enclosingbox().ysize() - uape.dimension().ysize()) * 100) / 100.00;
			}

			if (uape.is_it("blo")) {
				Ubloape ublape = (Ubloape) uape; 					// cast
				Ulayer ubllayer = ublape.lay(); 					// wez definicje bloku
				level++;											// blok w bloku?
				dispApe(ubllayer.firstape(), ubllayer.numapes()); 	// rekursja
			}
			uape = uape.next();
		}
		return null;
	}

	// ta metoda bedzie dla warstwy outline
	public BlockInfo dispOutlineApe(Uape uape, int ile_ape) {
		double xSize2 = 0, ySize2 = 0;
		Upoint[] upointTab = new Upoint[32];

		for (int j = 0; j < ile_ape; j++) {

			if ((uape.is_it("blo")) && (level == 0)) {
				System.out.println("production panel:");

				cPnlpPnl = uape.numobj();
				sizepnlx = Math.round((uape.dimension().xsize()) * 100) / 100.00;
				sizepnly = Math.round((uape.dimension().ysize()) * 100) / 100.00;

			} else if ((uape.is_it("blo")) && (level == 1)) {
				System.out.println("customer panel:");

				pcbcPnl = uape.numobj();
				sizepcbx = Math.round((uape.dimension().xsize()) * 100) / 100.00;
				sizepcby = Math.round((uape.dimension().ysize()) * 100) / 100.00;

				Uapeobj uapeobj = uape.firstobj();
				// System.out.println("uapeobj.toString(): " + uapeobj.toString());
				int k, m;
				for (k = 0; k < uape.numobj(); k++) {
					Dpfobj dpfobj = uapeobj.obj();		// np. cir moze byc padem, linia, lukiem..
					if (dpfobj.is_it("fla")) {
						upointTab[k] = dpfobj.fp();		// zapisz koordynaty blokow
						// System.out.println(dpfobj.toString());
						System.out.println(dpfobj.fp().x + " \t" + dpfobj.fp().y);
					}
					uapeobj = uapeobj.next();
				}

				for (m = 0; m < k; m++) {
					xSize2 = Math.round((upointTab[0].x - upointTab[m].x) * 100) / 100.00;
					if (xSize2 == 0) {
						stepcustpnly++;							// zwiekszaj y-stepa, dopuki koordynaty rozne
					}
					ySize2 = Math.round((upointTab[m].y - upointTab[0].y) * 100) / 100.00;
					if (ySize2 == 0) {
						stepcustpnlx++;							// zwiekszaj x-stepa, dopuki koordynaty rozne
					}
				}

//				if (xSize2 == 0) {
//					xSize2 = 1;
//				}

				System.out.println("xSize2: " + xSize2);
				System.out.println("ySize2: " + ySize2);

//				System.out.println("stepcustpnlx: " + stepcustpnlx);
//				System.out.println("stepcustpnly: " + stepcustpnly);

//				cpxrepeat = xSize2 / (stepcustpnlx - 1);
//				cpyrepeat = ySize2 / (stepcustpnly - 1);

//				System.out.println("cpxrepeat: " + cpxrepeat);
//				System.out.println("step: " + stepcustpnlx + " / " + stepcustpnly);
//				System.out.println("repeat: " + cpxrepeat + " / " + cpyrepeat);

				// stepcustpnly = pcbcPnl / stepcustpnlx;

			} else if ((uape.is_it("blo")) && (level == 2)) {
				System.out.println("even deeper..");
				System.out.println(uape.toString());
				System.out.println("level2");

			} else if ((uape.is_it("blo")) && (level == 3)) {
				System.out.println(uape.toString());
				System.out.println("level3");
			}

			if (uape.is_it("blo")) {
				Ubloape ublape = (Ubloape) uape; 							// cast
				Ulayer ubllayer = ublape.lay(); 							// wez definicje bloku
				level++;													// blok w bloku?
				dispOutlineApe(ubllayer.firstape(), ubllayer.numapes()); 	// rekursja
			}
			uape = uape.next();
		}
		return null;
	}

	public int getcPnlpPnl() {
		return cPnlpPnl;
	}

	public int getStepcustpnlx() {
		return stepcustpnlx;
	}

	public int getStepcustpnly() {
		return stepcustpnly;
	}

	public double getSizepnlx() {
		return sizepnlx;
	}

	public double getSizepnly() {
		return sizepnly;
	}

	public double getSizepcbx() {
		return sizepcbx;
	}

	public double getSizepcby() {
		return sizepcby;
	}

	public double getCpxrepeat() {
		return cpxrepeat;
	}

	public double getCpyrepeat() {
		return cpyrepeat;
	}

	public int getPcbcPnl() {
		return pcbcPnl;
	}

	public int getStepprodpnlx() {
		return stepprodpnlx;
	}

	public int getStepprodpnly() {
		return stepprodpnly;
	}

	public double getPpxrepeat() {
		return ppxrepeat;
	}

	public double getPpyrepeat() {
		return ppyrepeat;
	}

}
