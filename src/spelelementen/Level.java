package spelelementen;

import gui_componenten.GameMain;
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
	private final int par;
	private final Vector startPos;

	public Level(Planeet[] planeten, Satelliet[] satellieten, Bal golfbal, Hole hole, Vector startPos, int par) {
		this.planeten = planeten;
		this.satellieten = satellieten;
		this.golfbal = golfbal;
		this.hole = hole;
		this.startPos = startPos;
		this.par = par;

		hemellichamen = new Cirkel[planeten.length + satellieten.length];
		for (int i = 0; i < hemellichamen.length; i++) {
			if (i < planeten.length) {

				hemellichamen[i] = planeten[i];
			} else {
				hemellichamen[i] = getSatellieten()[i - planeten.length];
			}
		}//maakt een lijst aan van alle planeteten en satellieten
	}

	public void ResetBall() {
		if (!golfbal.getPlaats().equals(startPos)) {
			golfbal.setStationary(true);
			golfbal.setPlaats(startPos.clone());
			incrementStrokes();
		}
	}

	public void turn() {
		if (!golfbal.isStationary()) {
			Traject.Berekening(golfbal, planeten, hemellichamen);

			if (golfbal.isCurrently_coliding()) {
				positionaverage.add(golfbal.getPlaats());
				if (positionaverage.average() < GameMain.MINIMAL_AVERAGE_MOVEMENT) {
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
		hole.Score(golfbal);
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

	public void incrementStrokes() {
		nr_strokes++;
	}

	public int getPar() {
		return par;
	}

	public Vector getStartPos() {
		return startPos;
	}

}
