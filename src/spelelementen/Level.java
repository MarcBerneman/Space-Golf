package spelelementen;

import java.awt.Color;

import tools.PositionAverage;
import tools.Vector;

public class Level {
	private final Bal golfbal;
	private final Planeet[] planeten;
	private final PositionAverage positionaverage = new PositionAverage(10);
	private final Vector startPos;
	private final Hole hole;

	public Level() {
		startPos = new Vector(250, 150);
		golfbal = new Bal(startPos.clone(), 1, 10, Color.WHITE);
		planeten = new Planeet[3];
		getPlaneten()[0] = new Planeet(250, 350, 500, 35, Color.BLUE);
		getPlaneten()[1] = new Planeet(100, 200, 1000, 80, Color.MAGENTA);
		getPlaneten()[2] = new Planeet(400, 200, 1000, 65, Color.GREEN);
		hole = new Hole(getPlaneten()[0],Math.PI, 10);
	}

	public void ResetBall() {
		getGolfbal().isStationary = true;
		getGolfbal().setPlaats(startPos.clone());
	}

	public void turn() {
		if (!getGolfbal().isStationary) {
			Vector F_tot = new Vector(0, 0);
			for (Planeet planeet : getPlaneten())
				F_tot = Vector.optelling(F_tot, planeet.zwaartekrachtveld(getGolfbal().getPlaats()));
			// F_tot = E_tot * m_golfbal
			F_tot = Vector.scalair_vermenigvuldiging((double) getGolfbal().getMassa(), F_tot);
			// V = V + DeltaV = V + (DeltaT / m_golfbal) * F_tot
			getGolfbal().getSnelheid()
					.optelling(Vector.scalair_vermenigvuldiging(MainFrame.DeltaT / getGolfbal().getMassa(), F_tot));
			for (Planeet planeet : getPlaneten()) {
				if (getGolfbal().isColiding(planeet)) {
					Vector bal_to_planeet = Vector.aftrekking(planeet.getPlaats(), getGolfbal().getPlaats());
					double theta; // hoek van botsing met planeet
					if (bal_to_planeet.getY() <= 0)
						theta = Math.acos(bal_to_planeet.getX() / bal_to_planeet.modulus());
					else
						theta = 2 * Math.PI - Math.acos(bal_to_planeet.getX() / bal_to_planeet.modulus());
					getGolfbal().setSnelheid(new Vector(
							MainFrame.COR * (-getGolfbal().getSnelheid().getX() * Math.cos(2 * theta)
									+ getGolfbal().getSnelheid().getY() * Math.sin(2 * theta)),
							MainFrame.WR * (getGolfbal().getSnelheid().getX() * Math.sin(2 * theta)
									+ getGolfbal().getSnelheid().getY() * Math.cos(2 * theta))));
					getGolfbal().Correctie(planeet);
					positionaverage.add(getGolfbal().getPlaats());
					if (positionaverage.average() < MainFrame.MINIMAL_AVERAGE_MOVEMENT) {
						// Bal mag alleen stoppen op planeet
						getGolfbal().isStationary = true;
						positionaverage.initialize();
						// Voorbereiding op volgend schot
					}
					break; // Bal kan alleen met 1 planeet botsen
				}
			}
			getGolfbal().getPlaats().optelling(Vector.scalair_vermenigvuldiging(MainFrame.DeltaT, getGolfbal().getSnelheid()));
		}
		if(hole.Score(golfbal)){
			System.out.println("Score!!!!!!!");
		}
	}

	public Bal getGolfbal() {
		return golfbal;
	}

	public Planeet[] getPlaneten() {
		return planeten;
	}
	public Hole getHole(){
		return hole;
	}
}
