package spelelementen;

import tools.Vector;

public class Planeet extends Cirkel {

	final static double G = 0.0000000000667;

	public Planeet(Vector plaats, int massa, int straal) {
		super(plaats, massa, straal);
	}

	public Planeet(int x, int y, int massa, int straal) {
		super(x, y, massa, straal);
	}

	public Vector zwaartekrachtveld(Vector r) {
		Vector r_min_ri = Vector.aftrekking(r, plaats);
		return Vector.scalair_vermenigvuldiging(-G * massa / Math.pow(r_min_ri.modulus(), 3), r_min_ri);
	}
}
