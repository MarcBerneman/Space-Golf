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
	private final Satelliet[] satellieten;
	private final Cirkel[] hemellichamen;
	
	public Level() {
		startPos = new Vector(250, 150);
		golfbal = new Bal(startPos.clone(), 1, 10, Color.WHITE);
		planeten = new Planeet[3];
		getPlaneten()[0] = new Planeet(250, 350, 500, 35, Color.BLUE);
		getPlaneten()[1] = new Planeet(100, 200, 1000, 80, Color.MAGENTA);
		getPlaneten()[2] = new Planeet(400, 200, 1000, 65, Color.GREEN);
		satellieten = new Satelliet[1];//in vervolg if voor als er wel of geen satellieten zijn
		getSatellieten()[0] = new Satelliet(2, getPlaneten()[2], 50, Color.CYAN, Math.PI);
		hemellichamen  = new Cirkel[planeten.length+satellieten.length];
		for(int i=0;i<hemellichamen.length;i++){
			if(i<planeten.length){
				getHemellichamen()[i] = getPlaneten()[i];
			}
			else{
				getHemellichamen()[i] = getSatellieten()[i-planeten.length];
			}
		}
		hole = new Hole(getPlaneten()[0], (Math.PI * 3) / -2, 10);
	}

	public void ResetBall() {
		getGolfbal().setStationary(true);
		getGolfbal().setPlaats(startPos.clone());
	}

	public void turn() {
		if (!getGolfbal().isStationary()) {
			Traject.Berekening(golfbal, planeten, hemellichamen);
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
		for(Satelliet satelliet : satellieten){
			satelliet.move();
		}
		if (hole.Score(golfbal)) {
			System.out.println("Score!!!!!!!");
		}
		
	}

	public Bal getGolfbal() {
		return golfbal;
	}

	public Planeet[] getPlaneten() {
		return planeten;
	}

	public Hole getHole() {
		return hole;
	}
	public Satelliet[] getSatellieten(){
		return satellieten;
	}
	public Cirkel[] getHemellichamen(){
		return hemellichamen;
	}
}
