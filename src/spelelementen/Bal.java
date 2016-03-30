package spelelementen;

import tools.Vector;

public class Bal extends Cirkel {
	Vector snelheid;

	public Bal(Vector plaats, int massa, int straal) {
		super(plaats, massa, straal);
		this.snelheid = new Vector(0, 0);
	}

	public Bal(int x, int y, int massa, int straal) {
		super(x, y, massa, straal);
	}
	
	
}
