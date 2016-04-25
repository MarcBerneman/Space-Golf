package spelelementen;

import java.awt.Color;

import tools.Vector;

public class Level {
	Bal golfbal;
	Planeet[] planeten;
	final static double DeltaT = 0.01;

	public Level() {
		golfbal = new Bal(250, 150, 1, 5, Color.WHITE);
		planeten = new Planeet[2];
		planeten[0] = new Planeet(300, 300, 100000, 35, Color.BLUE);
		planeten[1] = new Planeet(100, 200, 100000, 80, Color.MAGENTA);
	}

	public void turn() {
		Vector F_tot = new Vector(0, 0);
		for (Planeet planeet : planeten)
			F_tot = Vector.optelling(F_tot, planeet.zwaartekrachtveld(golfbal.plaats));
		F_tot = Vector.scalair_vermenigvuldiging((double) golfbal.massa, F_tot);
		golfbal.snelheid.optelling(Vector.scalair_vermenigvuldiging(DeltaT / golfbal.massa, F_tot));
		for (Planeet planeet : planeten) {
			if (golfbal.isColiding(planeet)) {
				Vector n = Vector.aftrekking(planeet.plaats, golfbal.plaats);
				double theta;
				if(n.y >= 0) 
					theta = Math.acos(n.x / n.modulus());
				else
					theta = 2*Math.PI - Math.acos(n.x / n.modulus());
				golfbal.snelheid = new Vector(
						-golfbal.snelheid.x * Math.cos(2 * theta) - golfbal.snelheid.y * Math.sin(2 * theta),
						-golfbal.snelheid.x * Math.sin(2 * theta) + golfbal.snelheid.y * Math.cos(2 * theta));
				golfbal.snelheid.scalair_vermenigvuldiging(Bal.COR);
				break;
			}
		}
		golfbal.plaats.optelling(Vector.scalair_vermenigvuldiging(DeltaT, golfbal.snelheid));
	}
}
