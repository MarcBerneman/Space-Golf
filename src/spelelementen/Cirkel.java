package spelelementen;

import tools.Vector;

public class Cirkel {
	Vector plaats;
	int massa, straal;

	public Cirkel(Vector plaats, int massa, int straal) {
		this.plaats = plaats;
		this.massa = massa;
		this.straal = straal;
	}

	public Cirkel(int x, int y, int massa, int straal) {
		Vector plaats = new Vector((double) x, (double) y);
		this.plaats = plaats;
		this.massa = massa;
		this.straal = straal;
	}

	public boolean isColiding(Cirkel[] Cirkels) {
		for (Cirkel cirkel : Cirkels) {
			Vector this_to_cirkel = Vector.aftrekking(this.plaats, cirkel.plaats);
			if (this_to_cirkel.modulus() <= this.straal + cirkel.straal)
				return true;
		}
		return false;
	}
}
