package spelelementen;

import java.awt.Color;

import tools.Vector;

public class Bal extends Cirkel {
	Vector snelheid;

	public Bal(Vector plaats, int massa, int straal, Color kleur) {
		super(plaats, massa, straal, kleur);
		this.snelheid = new Vector(0, 0);
	}

	public Bal(int x, int y, int massa, int straal, Color kleur) {
		super(x, y, massa, straal, kleur);
		this.snelheid = new Vector(0,0);
	}

}
