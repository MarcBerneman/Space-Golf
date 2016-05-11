package spelelementen;

import java.awt.Color;
import java.awt.Graphics;

import tools.Vector;

public class Cirkel {
	private Vector plaats;
	private final int massa, straal;
	private final Color kleur;
	private Vector snelheid;

	public Cirkel(Vector plaats, int massa, int straal, Color kleur) {
		this.plaats = plaats;
		this.massa = massa;
		this.straal = straal;
		this.kleur = kleur;
		this.snelheid = new Vector(0,0);
	}

	public Cirkel(int x, int y, int massa, int straal, Color kleur) {
		this.plaats = new Vector((double) x, (double) y);
		this.massa = massa;
		this.straal = straal;
		this.kleur = kleur;
		this.snelheid = new Vector(0,0);
	}
	
	public Cirkel(Vector plaats, int massa, int straal, Color kleur, Vector snelheid){
		this.plaats = plaats;
		this.massa = massa;
		this.straal = straal;
		this.kleur = kleur;
		this.snelheid = snelheid;
	}
	public Cirkel(int x, int y, int massa, int straal, Color kleur, Vector snelheid) {
		this.plaats = new Vector((double) x, (double) y);
		this.massa = massa;
		this.straal = straal;
		this.kleur = kleur;
		this.snelheid = snelheid;
	}
	
	public boolean isColiding(Cirkel other) {
		Vector this_to_other = Vector.aftrekking(this.plaats, other.plaats);
		return this_to_other.modulus() < this.straal + other.straal;
	}

	public void paintme(Graphics g) {
		g.setColor(kleur);
		g.fillOval((int) plaats.getX() - straal, (int) plaats.getY() - straal, straal * 2, straal * 2);
	}

	public Vector getPlaats() {
		return plaats;
	}

	public void setPlaats(Vector plaats) {
		this.plaats = plaats;
	}

	public int getMassa() {
		return massa;
	}

	public int getStraal() {
		return straal;
	}
	
	public Color getKleur(){
		return kleur;
	}
	public Vector getSnelheid(){
		return snelheid;
	}
	public void setSnelheid(Vector snelheid) {
		this.snelheid = snelheid;
	}
}
