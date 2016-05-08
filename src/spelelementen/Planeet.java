package spelelementen;

import java.awt.Color;

import tools.Vector;

public class Planeet extends Cirkel {

	public Planeet(Vector plaats, int massa, int straal, Color kleur) {
		super(plaats, massa, straal, kleur);
	}

	public Planeet(int x, int y, int massa, int straal, Color kleur) {
		super(x, y, massa, straal, kleur);
	}

	public Vector zwaartekrachtveld(Vector r) {
		Vector r_min_ri = Vector.aftrekking(r, getPlaats());
		return Vector.scalair_vermenigvuldiging(-MainFrame.G * getMassa() / Math.pow(r_min_ri.modulus(), 3), r_min_ri);
	}
}
