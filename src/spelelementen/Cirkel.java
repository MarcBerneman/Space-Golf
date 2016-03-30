package spelelementen;

import java.awt.Color;
import java.awt.Graphics;

import tools.Vector;

public class Cirkel {
	Vector plaats;
	int massa, straal;
	Color kleur;

	public Cirkel(Vector plaats, int massa, int straal, Color kleur) {
		this.plaats = plaats;
		this.massa = massa;
		this.straal = straal;
		this.kleur = kleur;
	}

	public Cirkel(int x, int y, int massa, int straal, Color kleur) {
		this.plaats = new Vector((double) x, (double) y);
		this.massa = massa;
		this.straal = straal;
		this.kleur = kleur;
	}

	private boolean isColiding(Cirkel other) {
		Vector this_to_other = Vector.aftrekking(this.plaats, other.plaats);
		return this_to_other.modulus() <= this.straal + other.straal;
	}

	public boolean isColiding(Cirkel[] Cirkels) {
		for (Cirkel cirkel : Cirkels)
			if (this.isColiding(cirkel))
				return true;
		return false;
	}
	
	public void drawCirkel(Graphics g) {
		g.setColor(kleur);
		g.fillOval((int) plaats.x- straal, (int) plaats.y - straal, straal*2, straal*2);
	}
}
