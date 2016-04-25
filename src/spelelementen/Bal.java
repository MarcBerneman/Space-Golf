package spelelementen;

import java.awt.Color;

import tools.Vector;

public class Bal extends Cirkel {
	Vector snelheid;
	final static double COR = 0.8; // Coefficient of Restitution

	public Bal(Vector plaats, int massa, int straal, Color kleur) {
		super(plaats, massa, straal, kleur);
		this.snelheid = new Vector(0, 0);
	}

	public Bal(int x, int y, int massa, int straal, Color kleur) {
		super(x, y, massa, straal, kleur);
		this.snelheid = new Vector(2,5);
	}

}
