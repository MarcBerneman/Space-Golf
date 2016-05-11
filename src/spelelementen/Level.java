package spelelementen;

import java.awt.Color;

import tools.PositionAverage;
import tools.Vector;

public class Level {
	private final Bal golfbal;
	private final Planeet[] planeten;
	private final PositionAverage positionaverage = new PositionAverage(10);
	private final Hole hole;
	private final Satelliet[] satellieten;
	private final Cirkel[] hemellichamen;
	private int nr_strokes = 0;
<<<<<<< HEAD
	private final int par=3;
	private final Vector startPos;
	
	
	public Level(Planeet[] planeten, Satelliet[] satellieten, Bal golfbal, Hole hole, Vector startPos) {
		this.planeten= planeten;
		this.satellieten =satellieten;
		this.golfbal= golfbal;
		this.hole= hole;
		this.startPos= startPos;
		
		
//		startPos = new Vector(250, 150);
//		golfbal = new Bal(startPos.clone(), 1, 10, Color.WHITE);
//		planeten = new Planeet[4];
//		planeten[0] = new Planeet(700, 500, 500, 35, Color.BLUE);
//		planeten[1] = new Planeet(100, 200, 1000, 80, Color.MAGENTA);
//		planeten[2] = new Planeet(400, 200, 1000, 65, Color.GREEN);
//		planeten[3] = new Planeet(500, 400, 2000, 65, Color.PINK);
//		satellieten = new Satelliet[1];//in vervolg if voor als er wel of geen satellieten zijn
//		getSatellieten()[0] = new Satelliet(2, planeten[2], 50, Color.CYAN, Math.PI);
		hemellichamen  = new Cirkel[planeten.length+satellieten.length];
		for(int i=0;i<hemellichamen.length;i++){
			if(i<planeten.length){
=======
	private final int par = 3;

	public Level() {
		startPos = new Vector(250, 150);
		golfbal = new Bal(startPos.clone(), 1, 10, Color.WHITE);
		planeten = new Planeet[4];
		planeten[0] = new Planeet(700, 500, 500, 35, Color.BLUE);
		planeten[1] = new Planeet(100, 200, 1000, 80, Color.MAGENTA);
		planeten[2] = new Planeet(400, 200, 1000, 65, Color.GREEN);
		planeten[3] = new Planeet(500, 400, 2000, 65, Color.PINK);
		satellieten = new Satelliet[1];// in vervolg if voor als er wel of geen
										// satellieten zijn
		getSatellieten()[0] = new Satelliet(2, planeten[2], 50, Color.CYAN, Math.PI);
		hemellichamen = new Cirkel[planeten.length + satellieten.length];
		for (int i = 0; i < hemellichamen.length; i++) {
			if (i < planeten.length) {
>>>>>>> 2ba86463586b6afb630431c96403b92aa486c653
				hemellichamen[i] = planeten[i];
			} else {
				hemellichamen[i] = getSatellieten()[i - planeten.length];
			}
		}
//		hole = new Hole(planeten[0], (Math.PI * 3) / -2, 20);
	}

	public void ResetBall() {
		golfbal.setStationary(true);
		golfbal.setPlaats(startPos.clone());
	}

	public void turn() {
		if (!golfbal.isStationary()) {
			Traject.Berekening(golfbal, planeten, hemellichamen);

			if (golfbal.isCurrently_coliding()) {
				positionaverage.add(golfbal.getPlaats());
				if (positionaverage.average() < MainFrame.MINIMAL_AVERAGE_MOVEMENT) {
					// Bal mag alleen stoppen op planeet
					golfbal.setStationary(true);
					positionaverage.initialize();
					// Voorbereiding op volgend schot
				}
				golfbal.setCurrently_coliding(false);
			}

		}
		for (Satelliet satelliet : satellieten) {
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

	public Satelliet[] getSatellieten() {
		return satellieten;
	}

	public Cirkel[] getHemellichamen() {
		return hemellichamen;
	}

	public int getNr_strokes() {
		return nr_strokes;
	}

	public void setNr_strokes(int nr_strokes) {
		this.nr_strokes = nr_strokes;
	}

	public int getPar() {
		return par;
	}

}
