package spelelementen;

import gui_componenten.GameMain;
import tools.Vector;

public class Planeet extends Cirkel {

	public Planeet(Vector plaats, int massa, int straal) {
		super(plaats, massa, straal, new Vector(0, 0));
	}

	public Planeet(int x, int y, int massa, int straal) {
		super(x, y, massa, straal, new Vector(0, 0));
	}

	public Planeet(Vector plaats, int massa, int straal, Vector snelheid) {
		super(plaats, massa, straal, snelheid);
	}

	public Vector zwaartekrachtveld(Vector r) {
		Vector r_min_ri = Vector.aftrekking(r, getPlaats());
		return Vector.scalair_vermenigvuldiging(-GameMain.G * getMassa() / Math.pow(r_min_ri.modulus(), 3), r_min_ri);
	}
}
