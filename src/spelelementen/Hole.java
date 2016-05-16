package spelelementen;

import tools.Vector;

public class Hole extends Cirkel {
	private boolean scored = false;

	public Hole(Planeet planeet, double theta, int straal) {
		super(new Vector(planeet.getStraal() * Math.cos(theta) + planeet.getPlaats().getX(),
				planeet.getStraal() * Math.sin(theta) + planeet.getPlaats().getY()), 0, straal);
	}

	public void Score(Bal golfbal) {
		if (golfbal.isColiding(this)) {
			scored = true;
		}
	}

	public boolean getScored() {
		return scored;
	}

	public void setScored(boolean b) {
		scored = b;
	}
}
