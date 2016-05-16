package spelelementen;

import tools.Vector;

public class Cirkel {
	private Vector plaats;
	private final int massa, straal;
	private Vector snelheid;

	public Cirkel(Vector plaats, int massa, int straal) {
		this.plaats = plaats;
		this.massa = massa;
		this.straal = straal;
		this.snelheid = new Vector(0, 0);
	}

	public Cirkel(int x, int y, int massa, int straal) {
		this.plaats = new Vector((double) x, (double) y);
		this.massa = massa;
		this.straal = straal;
		this.snelheid = new Vector(0, 0);
	}

	public Cirkel(Vector plaats, int massa, int straal, Vector snelheid) {
		this.plaats = plaats;
		this.massa = massa;
		this.straal = straal;
		this.snelheid = snelheid;
	}

	public Cirkel(int x, int y, int massa, int straal, Vector snelheid) {
		this.plaats = new Vector((double) x, (double) y);
		this.massa = massa;
		this.straal = straal;
		this.snelheid = snelheid;
	}

	public boolean isColiding(Cirkel other) {
		Vector this_to_other = Vector.aftrekking(this.plaats, other.plaats);
		return this_to_other.modulus() < this.straal + other.straal;
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

	public Vector getSnelheid() {
		return snelheid;
	}

	public void setSnelheid(Vector snelheid) {
		this.snelheid = snelheid;
	}
}
