package spelelementen;

import tools.Vector;

public class Planeet {
	Vector plaats;
	int massa, straal;
	
	final static double G = 0.0000000000667;
	
	public Planeet(Vector plaats, int massa, int straal) {
		this.plaats = plaats;
		this.massa = massa;
		this.straal = straal;
	}
	
	public Planeet(int x, int y, int massa, int straal) {
		Vector plaats = new Vector((double) x, (double) y);
		this.plaats = plaats;
		this.massa = massa;
		this.straal = straal;
	}
	
	public Vector zwaartekrachtveld(Vector r) {
		Vector r_min_ri = Vector.aftrekking(r, plaats);
		return Vector.scalair_vermenigvuldiging(-G*massa/Math.pow(r_min_ri.modulus(),3),r_min_ri);
	}
}
