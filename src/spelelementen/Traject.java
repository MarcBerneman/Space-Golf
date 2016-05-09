package spelelementen;

import java.awt.Color;
import java.awt.Graphics;

import tools.Vector;

public class Traject {
	public static Vector muis_positie;
	public static void Berekening(Bal golfbal, Planeet[] planeten) {
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
		golfbal.getPlaats().optelling(Vector.scalair_vermenigvuldiging(MainFrame.DeltaT, golfbal.getSnelheid()));
	}
	public void Aim(Graphics g, Bal golfbal, Planeet[] planeten){
		Bal virtuele_bal = golfbal.clone();
		virtuele_bal.setSnelheid(virtuele_bal.InitialSpeed(muis_positie));
		double afstand = Vector.aftrekking(virtuele_bal.getPlaats(), golfbal.getPlaats()).modulus();
		while(afstand<100){
			Berekening(virtuele_bal,planeten);
			Cirkel punt = new Cirkel(virtuele_bal.getPlaats(), 1, 1, Color.WHITE);
			punt.paintme(g);
		}	
	}
}
