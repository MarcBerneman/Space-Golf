package spelelementen;

import java.awt.Color;

import tools.Vector;

public class Bal extends Cirkel {
	private boolean isStationary = true;
	private static double CorrectionFactor = 1;

	public Bal(Vector plaats, int massa, int straal, Color kleur) {
		super(plaats, massa, straal, kleur);
	}
	public Bal(Vector plaats, int massa, int straal, Color kleur, Vector snelheid) {
		super(plaats, massa, straal, kleur);
	}
	
	public void Correctie(Cirkel ColidingPlanet) {
		Vector n = Vector.aftrekking(getPlaats(), ColidingPlanet.getPlaats());
		n.scalair_vermenigvuldiging(CorrectionFactor*(this.getStraal() + ColidingPlanet.getStraal()) / n.modulus());
		setPlaats(Vector.optelling(ColidingPlanet.getPlaats(), n));
		// Beweegt de bal zodat het niet in de planeet is
	}
	
	public Vector InitialSpeed(Vector MousePosition) {
		Vector snelheid = Vector.aftrekking(MousePosition, getPlaats());
		if(snelheid.modulus() > MainFrame.MAX_POWER)
			snelheid.scalair_vermenigvuldiging(MainFrame.MAX_POWER/snelheid.modulus());
		return Vector.scalair_vermenigvuldiging(MainFrame.MOUSE_SPEED_COEFFICIENT, snelheid);
	}
	
	public Bal clone() {
		Bal dolly = new Bal(getPlaats().clone(),getMassa(),getStraal(),getKleur());
		dolly.setSnelheid(this.getSnelheid());
		dolly.setStationary(this.isStationary());
		return dolly;
	}

	public boolean isStationary() {
		return isStationary;
	}

	public void setStationary(boolean bool) {
		isStationary = bool;
	}

}
