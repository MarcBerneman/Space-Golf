package spelelementen;

import java.awt.Color;
import java.awt.Graphics;

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
		getGolfbal().setStationary(true);
		getGolfbal().setPlaats(startPos.clone());
	}

	public void turn() {
		if (!getGolfbal().isStationary()) {
			Traject.Berekening(golfbal, planeten);
			for (Planeet planeet : getPlaneten()) {
				if (getGolfbal().isColiding(planeet)) {
					positionaverage.add(getGolfbal().getPlaats());
					if (positionaverage.average() < MainFrame.MINIMAL_AVERAGE_MOVEMENT) {
						// Bal mag alleen stoppen op planeet
						getGolfbal().setStationary(true);
						positionaverage.initialize();
						// Voorbereiding op volgend schot
					}
					break; // Bal kan alleen met 1 planeet botsen
				}
			}
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
