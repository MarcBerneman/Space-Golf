package spelelementen;

import tools.Vector;

public class Traject {
	public Vector Berekening(Bal golfbal, Planeet[] planeten) {
		if (!golfbal.isStationary()) {
			Vector F_tot = new Vector(0, 0);
			for (Planeet planeet : planeten)
				F_tot = Vector.optelling(F_tot, planeet.zwaartekrachtveld(golfbal.getPlaats()));
			// F_tot = E_tot * m_golfbal
			F_tot = Vector.scalair_vermenigvuldiging((double) golfbal.getMassa(), F_tot);
			// V = V + DeltaV = V + (DeltaT / m_golfbal) * F_tot
			golfbal.getSnelheid()
					.optelling(Vector.scalair_vermenigvuldiging(MainFrame.DeltaT / golfbal.getMassa(), F_tot));
			for (Planeet planeet : planeten) {
				if (golfbal.isColiding(planeet)) {
					Vector bal_to_planeet = Vector.aftrekking(planeet.getPlaats(), golfbal.getPlaats());
					double theta; // hoek van botsing met planeet
					if (bal_to_planeet.getY() <= 0)
						theta = Math.acos(bal_to_planeet.getX() / bal_to_planeet.modulus());
					else
						theta = 2 * Math.PI - Math.acos(bal_to_planeet.getX() / bal_to_planeet.modulus());
					golfbal.setSnelheid(new Vector(
							MainFrame.COR * (-golfbal.getSnelheid().getX() * Math.cos(2 * theta)
									+ golfbal.getSnelheid().getY() * Math.sin(2 * theta)),
							MainFrame.WR * (golfbal.getSnelheid().getX() * Math.sin(2 * theta)
									+ golfbal.getSnelheid().getY() * Math.cos(2 * theta))));
					golfbal.Correctie(planeet);
				}
				break; // Bal kan alleen met 1 planeet botsen
			}
		}
		golfbal.getPlaats().optelling(Vector.scalair_vermenigvuldiging(MainFrame.DeltaT, golfbal.getSnelheid()));
		return golfbal.getPlaats();
	}
	public 
}
