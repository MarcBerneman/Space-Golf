package spelelementen;

import java.awt.Color;

import tools.Vector;

public class Bal extends Cirkel {
	private Vector snelheid;
	final static double COR = 0.85; // Coëfficient of Restitution

	public Bal(Vector plaats, int massa, int straal, Color kleur) {
		super(plaats, massa, straal, kleur);
		this.setSnelheid(new Vector(0, 0));
	}

	public Bal(int x, int y, int massa, int straal, Color kleur) {
		super(x, y, massa, straal, kleur);
		this.setSnelheid(new Vector(100, 0));
	}

	public Vector getSnelheid() {
		return snelheid;
	}

	public void setSnelheid(Vector snelheid) {
		this.snelheid = snelheid;
	}

}
