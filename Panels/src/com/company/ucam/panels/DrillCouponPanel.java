/*
 * DrillCouponPanel.java
 *
 * Copyright (c) 1999 BARCO n.v. All rights reserved.
 */

// Defines the package this class resides in.
// This should be the first line of code in the source file.
package com.company.ucam.panels;

// Standard Ucam packages.
import com.barco.ets.ucam.dtl.*;
import com.barco.ets.ucam.ui.*;
import com.barco.ets.ucam.hypertool.*;

/**
 * Custom Upanel subclass which overloads the
 * fill_coupon_list method.
 */
public class DrillCouponPanel extends Upanel.CO {
	/**
	 * Fills up the coupons list with all the coupons
	 * for this panel.
	 * A new Ucoupon is constructed for the 'DrillCoupon'
	 * coupon.
	 */
	public void fill_coupon_list(Uframe frm, Uresult res) {
		super.fill_coupon_list(frm, res);

		if (coupons == null) {
			return;
		}

		Ucoupon coupon = new Ucoupon("DrillCoupon");
		coupon.setjob(make_coupon_job(frm));
		coupons.add(coupon);
	}

	/**
	 * Create a job for the DrillCoupon.
	 * The job contains one drillLayer.
	 */
	public Ujob make_coupon_job(Uframe frm) {
		// Create a drill layer.
		Udrilayer couponLayer = (Udrilayer) Udrilayer.cO.create();
		if (couponLayer == null) {
			return null;
		}

		// Create a job.
		Ujob couponJob = Ujob.cO.create();
		if (couponJob == null) {
			return null;
		}

		couponLayer.setactive(true);

		// For each of the drill layers in the job to panelize,
		// add the drill apertures to the couponLayer.
		Ujob job;
		Udrilayer lay;
		for (int i = 1; i <= jobs.count(); ++i) {
			job = (Ujob) jobs.get(i);
			for (int j = 1; j <= job.numlayers("drill"); ++j) {
				lay = (Udrilayer) job.getlayer("drill", "", j);
				add_drills(lay, couponLayer);
			}
		}

		// Flash each of the drill apertures once.
		Uape ape = couponLayer.firstape();
		double pos = 0;

		for (int i = 1; i <= couponLayer.numapes(); ++i) {
			ape.flash(0, pos);
			pos += 100 * Ucamv6._PCT_mil();
			ape = ape.next();
		}

		// Add the resulting layer to the job.
		couponJob.addlayer(couponLayer);
		return couponJob;
	}

	/**
	 * Look for all the flashed circular apertures in the layer
	 * and add them to the couponLayer.
	 */
	public void add_drills(Udrilayer lay, Udrilayer couponLayer) {
		Uape ape = lay.firstape();
		Uape cirApe;

		for (int i = 1; i <= lay.numapes(); ++i) {
			if (ape.is_it("cir") && !ape.reverse() && (ape.numobj("f") != 0)) {
				cirApe = couponLayer.apesearch(ape, "");
				if (cirApe == null) {
					cirApe = ape.copydef();
					couponLayer.addape(cirApe);
				}
			}
			ape = ape.next();
		}
	}
}