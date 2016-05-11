package spelelementen;

import java.awt.Color;
import java.awt.Graphics;

import tools.Vector;

public class Traject {
	public static void Berekening(Bal golfbal, Planeet[] planeten, Cirkel[] hemellichamen) {
		Vector F_tot = new Vector(0, 0);
		for (Planeet planeet : planeten)
			F_tot = Vector.optelling(F_tot, planeet.zwaartekrachtveld(golfbal.getPlaats()));
		// F_tot = E_tot * m_golfbal
		F_tot = Vector.scalair_vermenigvuldiging((double) golfbal.getMassa(), F_tot);
		// V = V + DeltaV = V + (DeltaT / m_golfbal) * F_tot
		golfbal.getSnelheid()
				.optelling(Vector.scalair_vermenigvuldiging(MainFrame.DeltaT / golfbal.getMassa(), F_tot));
		for (Cirkel hemellichaam : hemellichamen) {
			if (golfbal.isColiding(hemellichaam)) {
				Vector bal_to_hemellichaam = Vector.aftrekking(hemellichaam.getPlaats(), golfbal.getPlaats());
				double theta; // hoek van botsing met planeet
				if (bal_to_hemellichaam.getY() <= 0)
					theta = Math.acos(bal_to_hemellichaam.getX() / bal_to_hemellichaam.modulus());
				else
					theta = 2 * Math.PI - Math.acos(bal_to_hemellichaam.getX() / bal_to_hemellichaam.modulus());
//				Vector snelheid_door_hemellichaam = hemellichaam.getSnelheid();
//				golfbal.setSnelheid(new Vector(
//						MainFrame.COR * (-golfbal.getSnelheid().getX() * Math.cos(2 * theta)
//								+ golfbal.getSnelheid().getY() * Math.sin(2 * theta)),
//						MainFrame.WR * (golfbal.getSnelheid().getX() * Math.sin(2 * theta)
//								+ golfbal.getSnelheid().getY() * Math.cos(2 * theta))));
//				golfbal.setSnelheid(Vector.optelling(golfbal.getSnelheid(), snelheid_door_hemellichaam));
				Vector relatieve_snelheid = Vector.optelling(golfbal.getSnelheid(),hemellichaam.getSnelheid());
				golfbal.setSnelheid(new Vector(
						MainFrame.COR * (-relatieve_snelheid.getX() * Math.cos(2 * theta)
								+ relatieve_snelheid.getY() * Math.sin(2 * theta)),
						MainFrame.WR * (relatieve_snelheid.getX() * Math.sin(2 * theta)
								+ relatieve_snelheid.getY() * Math.cos(2 * theta))));
				golfbal.Correctie(hemellichaam);	//Nathan: frontale botsing de satelliet does weird shit
				break; // Bal kan alleen met 1 planeet botsen
			}
		}
		golfbal.getPlaats().optelling(Vector.scalair_vermenigvuldiging(MainFrame.DeltaT, golfbal.getSnelheid()));
	}
	public static void Aim(Graphics g, Bal golfbal, Planeet[] planeten,Vector muis_positie,Cirkel[] hemellichamen){
		Bal virtuele_bal = golfbal.clone();
		virtuele_bal.setSnelheid(virtuele_bal.InitialSpeed(muis_positie));
		for(int i = 0 ; i < MainFrame.AIM_TIME / MainFrame.DeltaT ; i++){
			Berekening(virtuele_bal,planeten,hemellichamen);
			Cirkel punt = new Cirkel(virtuele_bal.getPlaats(), 0, 1, Color.WHITE);
			punt.paintme(g);
		}	
	}
}
