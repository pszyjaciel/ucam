import com.barco.ets.ucam.hypertool.Dpfobj;
import com.barco.ets.ucam.hypertool.Uape;
import com.barco.ets.ucam.hypertool.Uapeobj;
import com.barco.ets.ucam.hypertool.Ubloape;
import com.barco.ets.ucam.hypertool.Ulayer;
import com.barco.ets.ucam.hypertool.Upoint;
import com.barco.ets.ucam.hypertool.Urectangle;

/**
 * 04/02/2016
 */

/**
 * @author root
 * 04/02/2016 18.53.02
 */
public class myTrash {


/*
 * taki se smietnik..

			if (uape.is_it("cir")) {
				// System.out.println("cir");
			} else if (uape.is_it("rec")) {
				System.out.println("rec");
			} else if (uape.is_it("box")) {
				System.out.println("box");
			} else if (uape.is_it("oct")) {
				System.out.println("oct");
			} else if (uape.is_it("con")) {
				System.out.println("con");
			} else if (uape.is_it("com")) {
				System.out.println("com");
			} else if (uape.is_it("the")) {
				System.out.println("the");
			} else if (uape.is_it("txt")) {
				System.out.println("txt");
			} else if (uape.is_it("blo")) {
			
			else if (uape.is_it("squ")) {
				System.out.println("squ");
			} else if (uape.is_it("don")) {
				System.out.println("don");
			}


		// znajduje najgrubszego tracka
		Uape myBigTrack = layer_fr.BiggestTrack();

		// System.out.println("ape_max_number(): " + myBigTrack.ape_max_number());
		// System.out.println("numobj(): " + myBigTrack.numobj());
		// System.out.println("numobj(): " + myBigTrack.numobj("d"));
		// System.out.println("numobj(): " + myBigTrack.numobj("f"));

		System.out.println("num(): " + myBigTrack.num());

		Uapeobj myUapobj = myBigTrack.firstobj();
		for (int i = 0; i < myBigTrack.numobj(); i++) {
			// System.out.print(myUapobj.obj().kind() + "\t");
			myUapobj = myUapobj.next();
		}



		System.out.println("");

		Uape myBigPad = layer_fr.BiggestPad();
		// System.out.println("numobj(): " + myBigPad.numobj());
		// System.out.println("num(): " + myBigPad.num());
		// System.out.println("");

		Uape mySmallPad = layer_fr.SmallestPad();
		// System.out.println("numobj(): " + mySmallPad.numobj());
		// System.out.println("num(): " + mySmallPad.num());
		// System.out.println("");

		Uape mySmallTrack = layer_fr.SmallestTrack();
		// System.out.println("numobj(): " + mySmallTrack.numobj());
		// System.out.println("num(): " + mySmallTrack.num());
		// System.out.println("");

		// int rs = layer_frp.block_expand();
		// System.out.println(rs);

		// Uape fape = layer_fr.firstape();
		// System.out.println("layer_fr.numapes(): " + layer_fr.numapes());


				// Uapeobj uapeobj = uape.firstobj();
				// for (int k = 0; k < uape.numobj(); k++) {
				// Dpfobj dpfobj = uapeobj.obj(); // np. cir moze byc padem, linia, lukiem..
				// if (dpfobj.is_it("fla")) {
				// System.out.println(dpfobj.toString());
				// // System.out.println("flash: " + k);
				// } else if (dpfobj.is_it("dra")) {
				// // System.out.println("draw: " + k);
				// } else if (dpfobj.is_it("arc")) {
				// // System.out.println("arc: " + k);
				// }
				// uapeobj = uapeobj.next();
				// }

		// for (int i = 0; i < fape.numobj("flash"); i++) {
		//
		// // System.out.println("i: " + i);
		//
		// dpfobj = myApeObj.obj(); // metody dpfobj to koordynaty
		// dpfobj.fp();
		//
		// myFirstPoint = dpfobj.fp();
		//
		// // if (fape.shape().equals("blo")) {
		// if ((fape.shape().equals("blo")) && (layer_fr.getObjAttrValue(k1).equals("1"))) {
		// // System.out.println("fape.toString(): " + fape.toString());
		// pxTab[i] = myFirstPoint.x(); // tablica punktow
		// pyTab[i] = myFirstPoint.y();
		//
		// // System.out.println(fape.attributes());
		// // tylko 1 wywolanie
		//
		// ubloape = (Ubloape) fape; // cast
		// // dispInnerBlock(ubloape);
		// }
		// myApeObj = myApeObj.next();
		// }
		// fape = fape.next();
		// }

		// dispBlock(layer_fr);

		// stepX = getStep(pxTab);
		// stepY = getStep(pyTab);

		// System.out.println("stepX: " + stepX);
		// System.out.println("stepY: " + stepY);

		// System.out.println(Ucamv6.FRAME_NAME);
		// System.out.println(Ucamv6.VERSION);

		// Ucamapp.cO.startprogress();

		// System.out.println(ujob.getInfo());
		// System.out.println(ujob.getJobSizeSource());
		// System.out.println(ujob.getPosPath(""));
		// System.out.println(ujob.get_core_info());
		// System.out.println(ujob.name());
		// System.out.println(ujob.user_data);



// uape = ulayer.getape(j);
			// System.out.println("j: " + j + "\t\tk: " + k);



System.out.println("fape.num(): " + fape.num());
System.out.println("layer_frp.firstape()): " + layer_frp.firstape());
System.out.println("layer_frp.lastape()): " + layer_frp.lastape());
			// System.out.println("myApeObj.obj().kind(): " + myApeObj.obj().kind());
		System.out.println("myApeObj.obj().kind(): " + myApeObj.obj().kind());
		System.out.println("myApeObj.obj().name(): " + myApeObj.obj().name());
		System.out.println("fape.name(): " + fape.name());

		// myApeObj = fape.firstobj();
		// System.out.println("nr: " + fape.num() + "\tname: " + myApeObj.obj().name());
		//
		// for (int i = 0; i < fape.numobj(); i++) {
		// myFirstPoint = myApeObj.obj().fp();
		// System.out.println("x: " + myFirstPoint.x() + "\ty: " + myFirstPoint.y());
		// myApeObj = myApeObj.next();
		// }
		//
		// fape = fape.next();
		//
		// myApeObj = fape.firstobj();
		// System.out.println("nr: " + fape.num() + "\tname: " + myApeObj.obj().name());
		//
		// for (int i = 0; i < fape.numobj(); i++) {
		// myFirstPoint = myApeObj.obj().fp();
		// System.out.println("x: " + myFirstPoint.x() + "\ty: " + myFirstPoint.y());
		// myApeObj = myApeObj.next();
		// }

		// myApeObj = fape.next();
		 * "\tname: " + myApeObj.obj().name() 
 System.out.println("fape.is_it(): " + fape.is_it("blo"));

					int iAttributeName = layer_frp.getObjAttrLastNameIx();
		int iAttributeValue = layer_frp.getObjAttrLastValueIx();
		System.out.println("layer_frp.getObjAttrName(0): " + layer_frp.getObjAttrName(0));
		System.out.println("layer_frp.getObjAttrName(1): " + layer_frp.getObjAttrName(1));
		System.out.println("layer_frp.getObjAttrName(2): " + layer_frp.getObjAttrName(2));
		
		System.out.println("layer_frp.getObjAttrValue(0): " + layer_frp.getObjAttrValue(0));
		System.out.println("layer_frp.getObjAttrValue(1): " + layer_frp.getObjAttrValue(1));
		System.out.println("layer_frp.getObjAttrValue(2): " + layer_frp.getObjAttrValue(2));
		
		int k1 = layer_frp.getObjAttrNameIx("uImage");
//		System.out.println("k1: " + k1);
//		System.out.println(layer_frp.getObjAttrLastNameIx());
		System.out.println("layer_frp.getObjAttrLastValueIx(): " + layer_frp.getObjAttrLastValueIx());
		System.out.println(layer_frp.getObjAttrName(k1));
		
					// System.out.println(myApeObj.getObjAttr(iAttributeName));

		// myApeObj = myBlockLayerApe.firstobj();
		// System.out.println("num: " + myBlockLayerApe.num() + "\t\tshape: " + myBlockLayerApe.shape() + "\t\tnumobj: "
		// + myBlockLayerApe.numobj());
		//
		// myBlockLayerApe = myBlockLayerApe.next();
		// myApeObj = myBlockLayerApe.firstobj();
		// System.out.println("num: " + myBlockLayerApe.num() + "\t\tshape: " + myBlockLayerApe.shape() + "\t\tnumobj: "
		// + myBlockLayerApe.numobj());
		//
		// myBlockLayerApe = myBlockLayerApe.next();
		// myApeObj = myBlockLayerApe.firstobj();
		// System.out.println("num: " + myBlockLayerApe.num() + "\t\tshape: " + myBlockLayerApe.shape() + "\t\tnumobj: "
		// + myBlockLayerApe.numobj());

		// System.out.println("fape.num(): " + myBlockLayerApe.num() + "\t\tfape.shape(): " + myBlockLayerApe.shape()
		// + "\t\tfape.numobj(): " + myBlockLayerApe.numobj());
		
							// System.out.println("xmax: " + myDim.xmax() + "\t\tymax: " + myDim.ymax());
					// System.out.println("xmin: " + myDim.xmin() + "\t\tymin: " + myDim.ymin());
					// System.out.println("xsize: " + myDim.xsize() + "\t\tysize: " + myDim.ysize());
					 * 
	
					 * 	// dziala
	private static void dispApe2(Ulayer ulayer, Uape uape) {

		int m = ulayer.numapes();
		uape = ulayer.firstape();
		for (int i = 0; i < m; i++) {
			if (!uape.is_it("blo")) {
				System.out.println("to nie block.");
				uape = uape.next();

			} else {
				System.out.println("to block");
				Ubloape ubloape = (Ubloape) uape; // cast
				Ulayer ubllayer = ubloape.lay();

				int k = ubllayer.numapes();
				Uape ublape = ubllayer.firstape();
				for (int j = 0; j < k; j++) {
					System.out.println("ublape.num(): " + ublape.num() + "\t\tublape.shape(): " + ublape.shape()
							+ "\t\tublape.numobj(): " + ublape.numobj());

					if (!ublape.is_it("blo")) {
						// System.out.println("uape.toString(): " + uape.toString());
						ublape = ublape.next();

					} else {
						dispApe2(ubllayer, ublape); // rekursja
						ublape = ublape.next();
					}
				}

			}
			System.out.println("count2: " + count2);
			count2++;
		}
		System.out.println("count1: " + count1);
		count1++;
	}

	// !uwaga: rekursja
	// patrz tutaj: u:\\ucamusers\java\\ucam\home\jucam\com\barco\ets\\ucam\hypertool\Ubpcb.jad
	private static void dispInnerBlock(Uape uape) {

		Ubloape ubloape = (Ubloape) uape; // cast
		Ulayer ulayer = ubloape.lay();

		int k = ulayer.numapes();
		uape = ulayer.firstape();
		for (int j = 0; j < k; j++) {
			System.out.println("uape.num(): " + uape.num() + "\t\tuape.shape(): " + uape.shape()
					+ "\t\tuape.numobj(): " + uape.numobj());

			if (!uape.is_it("blo")) {
				// System.out.println("uape.toString(): " + uape.toString());
				uape = uape.next();

			} else {

				// System.out.println("uape.toString(): " + uape.toString());
				// System.out.println(uape.num() + "\t" + uape.name());

				// System.out.println("uape.num(): " + uape.num() + "\t\tuape.shape(): " + uape.shape()
				// + "\t\tuape.numobj(): " + uape.numobj());

				// System.out.println(uape.numobj());
				// xPos = uapeobj1.obj().fp().x + uapeobj.obj().fp().x;
				// yPos = uapeobj1.obj().fp().y + uapeobj.obj().fp().y;
				double xSize = uape.enclosingbox().xsize();
				double ySize = uape.enclosingbox().ysize();
				// System.out.println(xSize + "\t" + ySize);

				// uape = uape.next();
				dispInnerBlock((Ubloape) uape); // rekursja
				uape = uape.next();
			}
		}
	}

	private static void checkBlock2(Ulayer ubloape) {

		Uape uape;

		// System.out.println(ubloape.getObjAttrLastNameIx());
		// System.out.println(ubloape.getObjAttrLastValueIx());
		// System.out.println(ubloape.hasOpenContours());
		// System.out.println(ubloape.index());

		// System.out.println(ubloape.is_loaded());
		uape = ubloape.firstape();

		Ubloape myUbloape;
		Ulayer ulayer;

		double xPos;
		double yPos;
		double xSize;
		double ySize;

		for (int i = 0; i < ubloape.numapes(); i++) {
			if (uape.is_it("blo") == true) {
				System.out.println("uape.toString(): " + uape.toString());

				myUbloape = (Ubloape) uape;
				ulayer = myUbloape.lay();
				uape = ulayer.firstape();
				Uapeobj uapeobj1 = uape.firstobj();
				// xPos = uapeobj1.obj().fp().x + uapeobj.obj().fp().x;
				// yPos = uapeobj1.obj().fp().y + uapeobj.obj().fp().y;
				xSize = uapeobj1.enclosingbox().xsize();
				ySize = uapeobj1.enclosingbox().ysize();
				System.out.println(xSize + "\t" + ySize);

			} else {

			}
			uape = uape.next();
		}

		// System.out.println("ubloape.numapes(): " + ubloape.numapes());
		// Uape uape = ubloape.firstape();
		// System.out.println("uape.toString(): " + uape.toString());
		// System.out.println(uape.num());

		// Uapeobj uapeobj1 = uape.firstobj();
		// double xSize = uapeobj1.enclosingbox().xsize();
		// double ySize = uapeobj1.enclosingbox().ysize();
		// // System.out.println(xSize + "\t" + ySize);
		//
		// // System.out.println(uape.numobj());
		// uape = uape.next();
		// System.out.println("uape.toString(): " + uape.toString());
		//
		// uape = uape.next();
		// System.out.println("uape.toString(): " + uape.toString());
		//
		// uapeobj1 = uapeobj1.next();

		// xSize = uapeobj1.enclosingbox().xsize();
		// ySize = uapeobj1.enclosingbox().ysize();
		// System.out.println(xSize + "\t" + ySize);

	}


					 * // Uapeobj myApeObj;
// Dpfobj dpfobj;
//
// Upoint myFirstPoint;
// double pxTab[] = new double[20];
// double pyTab[] = new double[40];

// myApeObj = fape.firstobj();
// System.out.println("fape.num(): " + fape.num() + "\t\tfape.shape(): " + fape.shape()
// + "\t\tfape.numobj(): " + fape.numobj());

// for (int i = 0; i < fape.numobj(); i++) {
// dpfobj = myApeObj.obj(); // metody dpfobj to koordynaty
// dpfobj.fp();
//
// myFirstPoint = dpfobj.fp();
// if (fape.shape().equals("blo")) {
// System.out.println("fape.toString(): " + fape.toString());
// pxTab[i] = myFirstPoint.x(); // tablica punktow
// pyTab[i] = myFirstPoint.y();

// ubloape = ((Ubloape) fape).lay();
// checkBlock2(ubloape);

// checkBlock(fape);
// myBloDim = fape.dimension();
// System.out.println(myBloDim.toString());

// botlx = myBloDim.botleft().x();
// botly = myBloDim.botleft().y();
// toprx = myBloDim.topright().x();
// topry = myBloDim.topright().y();
//
// System.out.println(botlx + "\t" + botly + "\t" + toprx + "\t" + topry);
// System.out.println(botlx + toprx);
// System.out.println(topry + botly);

// System.out.println(dpfobj.toString());

// System.out.println("for: i: " + i + "\t\tx: " + pxTab[i] + "\t\ty: " + pyTab[i]);
// }
// myApeObj = myApeObj.next();
// }
// fape = fape.next();
// }
//
// ubloape = ((Ubloape) fape).lay();
// checkBlock(fape);
// myBloDim = fape.dimension();
// System.out.println(myBloDim.toString());

// botlx = myBloDim.botleft().x();
// botly = myBloDim.botleft().y();
// toprx = myBloDim.topright().x();
// topry = myBloDim.topright().y();
//
// System.out.println(botlx + "\t" + botly + "\t" + toprx + "\t" + topry);
// System.out.println(botlx + toprx);
// System.out.println(topry + botly);

// System.out.println(dpfobj.toString());

// System.out.println("for: i: " + i + "\t\tx: " + pxTab[i] + "\t\ty: " + pyTab[i]);

// }


		// Uape fape = layer_fr.firstape();
		// int j = layer_fr.numapes();
		// for (j = 0; j < layer_fr.numapes(); j++) {
		//
		// System.out.println("fape.num(): " + fape.num() + "\t\tfape.shape(): " + fape.shape()
		// + "\t\tfape.numobj(): " + fape.numobj());
		//
		// if (fape.is_it("blo")) {
		// dispInnerBlock(fape); // rekursja
		// }
		// fape = fape.next();
		// }
 *
 */

	// System.out.println("uape.num(): " + uape.num() + "\t\tape.shape(): " + uape.shape() + "\t\tuape.numobj(): "
	// + uape.numobj());

	// xsize = Math.round(uape.dimension().xsize() * 100) / 100.00;
	// ysize = Math.round(uape.dimension().ysize() * 100) / 100.00;
	// System.out.println("uape.num(): " + uape.num() + "\txsize: " + xsize + "\tysize: " + ysize);

	// no tez fajne, ale to nie tego potrzebuje
	// double xSize = Math.round(uape.enclosingbox().xsize() * 100) / 100.00;
	// double ySize = Math.round(uape.enclosingbox().ysize() * 100) / 100.00;
	// System.out.println("uape.num(): " + uape.num() + "\txSize: " + xSize + "\tySize: " + ySize);

//	xmin = Math.round(uape.dimension().xmin() * 100) / 100.00;
//	ymin = Math.round(uape.dimension().ymin() * 100) / 100.00;
//	xmax = Math.round(uape.dimension().xmax() * 100) / 100.00;
//	ymax = Math.round(uape.dimension().ymax() * 100) / 100.00;
//	xenSize = Math.round(uape.enclosingbox().xsize() * 100) / 100.00;
//	yenSize = Math.round(uape.enclosingbox().ysize() * 100) / 100.00;
//	xSize = Math.round(uape.dimension().xsize() * 100) / 100.00;
//	ySize = Math.round(uape.dimension().ysize() * 100) / 100.00;
	
	// System.out.println("uape.num(): " + uape.num() + "\txmin: " + xmin + "\t\tymin: " + ymin);
	// System.out.println("uape.num(): " + uape.num() + "\txmax: " + xmax + "\t\tymax: " + ymax);
	// System.out.println("uape.num(): " + uape.num() + "\txenSize: " + xenSize + "\tyenSize: " + yenSize);
	// System.out.println("uape.num(): " + uape.num() + "\txsize: " + xSize + "\t\tysize: " + ySize);
/*
 * 
 * 	// private static void coTo(int i, Dpfobj dpfobj, Uape fape) {
	private static void checkBlock(Uape fape) {

		Upoint myFirstPoint;
		Uape myBlockLayerApe;
		Uapeobj myApeObj;
		Dpfobj dpfobj;
		Urectangle myBloDim, myCirDim;

		double stepX, stepY;
		double pxBlockTab[] = new double[20];
		double pyBlockTab[] = new double[40];
		double botlx, botly, toprx, topry;

		Ubloape myBlockApe = (Ubloape) fape;
		Ulayer myBlockLayer = myBlockApe.lay();
		int myNumapes = myBlockLayer.numapes();
		// System.out.println("myBlockLayer.numapes(): " + myNumapes);

		myBlockLayerApe = myBlockLayer.firstape();

		for (int i = 0; i < myNumapes; i++) {
			myApeObj = myBlockLayerApe.firstobj();

			for (int k = 0; k < myBlockLayerApe.numobj(); k++) {
				dpfobj = myApeObj.obj();
				myFirstPoint = dpfobj.fp();
				if (myBlockLayerApe.shape().equals("blo")) {
					System.out.println("myBlockLayerApe.num(): " + myBlockLayerApe.num()
							+ "\t\tmyBlockLayerApe.shape(): " + myBlockLayerApe.shape()
							+ "\t\tmyBlockLayerApe.numobj(): " + myBlockLayerApe.numobj());
					// System.out.println("k: " + k + "\t\tx: " + myFirstPoint.x() + "\t\ty: " + myFirstPoint.y());

					myBloDim = myBlockLayerApe.dimension();

					botlx = myBloDim.botleft().x();
					botly = myBloDim.botleft().y();
					toprx = myBloDim.topright().x();
					topry = myBloDim.topright().y();

					// System.out.println(botlx + "\t" + botly + "\t" + toprx + "\t" + topry);
					// System.out.println(botlx + toprx);
					// System.out.println(topry + botly);
				} else if (myBlockLayerApe.shape().equals("cir")) {
					System.out.println("mam cira.");
					myCirDim = myBlockLayerApe.dimension();

					// botlx = myCirDim.botleft().x();
					// botly = myCirDim.botleft().y();
					// toprx = myCirDim.topright().x();
					// topry = myCirDim.topright().y();
					// // System.out.println(botlx + "\t" + botly + "\t" + toprx + "\t" + topry);

					// System.out.println(myCirDim.xmin() + "\t" + myCirDim.xmax());
					System.out.println(myCirDim.xsize() + "\t" + myCirDim.ysize());

				}

				myApeObj = myApeObj.next();
			}
			myBlockLayerApe = myBlockLayerApe.next();

		}
	}

 */
	
}
