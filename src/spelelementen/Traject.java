package spelelementen;

import java.awt.Color;
import java.awt.Graphics;

import gui_componenten.GameMain;
import tools.Vector;

public class Traject {
	public static void Berekening(Bal golfbal, Planeet[] planeten, Cirkel[] hemellichamen) {
		Vector F_tot = new Vector(0, 0);
		for (Planeet planeet : planeten)
			F_tot = Vector.optelling(F_tot, planeet.zwaartekrachtveld(golfbal.getPlaats()));
		// F_tot = E_tot * m_golfbal
		
		F_tot = Vector.scalair_vermenigvuldiging((double) golfbal.getMassa(), F_tot);
		// V = V + DeltaV = V + (DeltaT / m_golfbal) * F_tot
		
		golfbal.getSnelheid().optelling(Vector.scalair_vermenigvuldiging(GameMain.DeltaT / golfbal.getMassa(), F_tot));
		
		for (Cirkel hemellichaam : hemellichamen) {
			if (golfbal.isColiding(hemellichaam)) {
				golfbal.setCurrently_coliding(true);
				Vector bal_to_hemellichaam = Vector.aftrekking(hemellichaam.getPlaats(), golfbal.getPlaats());
				double theta; // hoek van botsing met planeet
				
				if (bal_to_hemellichaam.getY() <= 0)
					theta = Math.acos(bal_to_hemellichaam.getX() / bal_to_hemellichaam.modulus());
				
				else
					theta = 2 * Math.PI - Math.acos(bal_to_hemellichaam.getX() / bal_to_hemellichaam.modulus());
				
				Vector relatieve_snelheid = Vector.aftrekking(golfbal.getSnelheid(), hemellichaam.getSnelheid());
				
				golfbal.setSnelheid(new Vector(
						GameMain.COR * (-relatieve_snelheid.getX() * Math.cos(2 * theta)
								+ relatieve_snelheid.getY() * Math.sin(2 * theta)),
						GameMain.WR * (relatieve_snelheid.getX() * Math.sin(2 * theta)
								+ relatieve_snelheid.getY() * Math.cos(2 * theta))));
				
				golfbal.Correctie(hemellichaam);
				break; // Bal kan alleen met 1 planeet botsen
			}
		}
		golfbal.getPlaats().optelling(Vector.scalair_vermenigvuldiging(GameMain.DeltaT, golfbal.getSnelheid()));
	}

	public static void Aim(Graphics g, Bal golfbal, Planeet[] planeten, Vector muis_positie, Cirkel[] hemellichamen) {
		//berekent het traject dat de bal gaat hebben en tekent het
		Bal virtuele_bal = golfbal.clone();
		virtuele_bal.setSnelheid(virtuele_bal.InitialSpeed(muis_positie));
		for (int i = 0; i < GameMain.AIM_TIME / GameMain.DeltaT; i++) {
			Berekening(virtuele_bal, planeten, hemellichamen);
			Vector plaats = virtuele_bal.getPlaats();
			int straal = 1;
			g.setColor(Color.WHITE);
			g.fillOval((int) (plaats.getX() - straal), (int) (plaats.getY() - straal), 2 * straal, 2 * straal);
		}
	}
}
