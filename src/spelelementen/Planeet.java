package spelelementen;

import java.awt.Image;

import gui_componenten.GameMain;
import tools.Vector;

public class Planeet extends Cirkel {

	public Planeet(Vector plaats, int massa, int straal, Image image) {
		super(plaats, massa, straal, new Vector(0, 0), image);
	}

	public Planeet(int x, int y, int massa, int straal, Image image) {
		super(x, y, massa, straal, new Vector(0, 0), image);
	}


	public Vector zwaartekrachtveld(Vector r) {
		Vector r_min_ri = Vector.aftrekking(r, getPlaats());
		return Vector.scalair_vermenigvuldiging(-GameMain.G * getMassa() / Math.pow(r_min_ri.modulus(), 3), r_min_ri);
	}
}
