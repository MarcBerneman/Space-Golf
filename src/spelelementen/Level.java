package spelelementen;

import java.awt.Color;

import tools.MovingAverage;
import tools.Vector;

public class Level {
	Bal golfbal;
	Planeet[] planeten;
	MovingAverage movingaverage = new MovingAverage(10);

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
				if (n.getY() <= 0)
					theta = Math.acos(n.getX() / n.modulus());
				else
					theta = 2 * Math.PI - Math.acos(n.getX() / n.modulus());
				golfbal.setSnelheid(new Vector(
						MainFrame.COR * (-golfbal.getSnelheid().getX() * Math.cos(2 * theta)
								+ golfbal.getSnelheid().getY() * Math.sin(2 * theta)),
						MainFrame.µ * (golfbal.getSnelheid().getX() * Math.sin(2 * theta)
								+ golfbal.getSnelheid().getY() * Math.cos(2 * theta))));
				golfbal.Correctie(planeet);
				movingaverage.add(golfbal.getPlaats());
				if (movingaverage.average() < MainFrame.MINIMAL_AVERAGE_MOVEMENT) {
					golfbal.isStationary = true;
					movingaverage.initialize();
				}
				break;
			}
		}
		if(!golfbal.isStationary)
			golfbal.getPlaats().optelling(Vector.scalair_vermenigvuldiging(MainFrame.DeltaT, golfbal.getSnelheid()));
	}
}
