package spelelementen;

import java.awt.Color;

import tools.Vector;

public class Bal extends Cirkel {
	private Vector snelheid;
	public boolean isStationary = true;

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

	public void Correctie(Planeet ColidingPlanet) {
		Vector n = Vector.aftrekking(getPlaats(), ColidingPlanet.getPlaats());
		n.scalair_vermenigvuldiging((this.getStraal() + ColidingPlanet.getStraal()) / n.modulus());
		setPlaats(Vector.optelling(ColidingPlanet.getPlaats(), n));
		// Beweegt de bal zodat het niet in de planeet is
	}
	
	public Vector InitialSpeed(Vector MousePosition) {
		Vector snelheid = Vector.aftrekking(MousePosition, getPlaats());
		return Vector.scalair_vermenigvuldiging(MainFrame.MOUSE_SPEED_COEFFICIENT, snelheid);
	}
	
	public Bal clone() {
		Bal dolly = new Bal(getPlaats().clone(),getMassa(),getStraal(),getKleur());
		dolly.setSnelheid(this.getSnelheid());
		dolly.isStationary = this.isStationary;
		return dolly;
	}

}
