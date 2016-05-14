package spelelementen;

import java.awt.Color;

import gui_componenten.GameMain;
import tools.Vector;

public class Planeet extends Cirkel {
	
	public Planeet(Vector plaats, int massa, int straal, Color kleur) {
		super(plaats, massa, straal, kleur, new Vector(0,0));
	}

	public Planeet(int x, int y, int massa, int straal, Color kleur) {
		super(x, y, massa, straal, kleur, new Vector(0,0));
	}
	public Planeet(Vector plaats, int massa, int straal, Color kleur, Vector snelheid){
		super(plaats, massa, straal, kleur, snelheid);
	}

	public Vector zwaartekrachtveld(Vector r) {
		Vector r_min_ri = Vector.aftrekking(r, getPlaats());
		return Vector.scalair_vermenigvuldiging(-GameMain.G * getMassa() / Math.pow(r_min_ri.modulus(), 3), r_min_ri);
	}
}
