package spelelementen;

import java.awt.Color;

import tools.Vector;

public class Level {
	Bal golfbal;
	Planeet[] planeten;
	Vector SnelheidBijLaatsteBotsing = new Vector(0,0);

	public Level() {
		golfbal = new Bal(250, 150, 1, 10, Color.WHITE);
		planeten = new Planeet[3];
		planeten[0] = new Planeet(250, 350, 500, 35, Color.BLUE);
		planeten[1] = new Planeet(100, 200, 1000, 80, Color.MAGENTA);
		planeten[2] = new Planeet(400, 200, 1000, 65, Color.GREEN);
	}

	public void turn() {
		Vector F_tot = new Vector(0, 0);
		for (Planeet planeet : planeten)
			F_tot = Vector.optelling(F_tot, planeet.zwaartekrachtveld(golfbal.getPlaats()));
		F_tot = Vector.scalair_vermenigvuldiging((double) golfbal.getMassa(), F_tot);
		golfbal.getSnelheid().optelling(Vector.scalair_vermenigvuldiging(MainFrame.DeltaT / golfbal.getMassa(), F_tot));
		for (Planeet planeet : planeten) {
			if (golfbal.isColiding(planeet)) {
				Vector n = Vector.aftrekking(planeet.getPlaats(), golfbal.getPlaats());
				double theta;
				double µ = 0.55;
				if (n.getY() <= 0)
					theta = Math.acos(n.getX() / n.modulus());
				else
					theta = 2 * Math.PI - Math.acos(n.getX() / n.modulus());
				golfbal.setSnelheid(new Vector(
						Bal.COR * (-golfbal.getSnelheid().getX() * Math.cos(2 * theta)
								+ golfbal.getSnelheid().getY() * Math.sin(2 * theta)),
						µ * (golfbal.getSnelheid().getX() * Math.sin(2 * theta)
								+ golfbal.getSnelheid().getY() * Math.cos(2 * theta))));
				if (golfbal.getSnelheid().modulus() < 5.5 || Math.abs(SnelheidBijLaatsteBotsing.modulus() - golfbal.getSnelheid().modulus()) < 0.1) {
					
					golfbal.isStationary = true;
				}
				golfbal.Correctie(planeet);
				System.out.println(SnelheidBijLaatsteBotsing.modulus() - golfbal.getSnelheid().modulus());
				SnelheidBijLaatsteBotsing = golfbal.getSnelheid();
				break;
			}
		}
		golfbal.getPlaats().optelling(Vector.scalair_vermenigvuldiging(MainFrame.DeltaT, golfbal.getSnelheid()));
	}
}
