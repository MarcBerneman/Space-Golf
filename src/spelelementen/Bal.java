package spelelementen;

import java.awt.Color;

import tools.Vector;

public class Bal extends Cirkel {
	private boolean stationary = true;
	private boolean currently_coliding = false;

	public Bal(Vector plaats, int massa, int straal, Color kleur) {
		super(plaats.clone(), massa, straal, kleur);
	}

	public void Correctie(Cirkel ColidingPlanet) {
		Vector n = Vector.aftrekking(getPlaats(), ColidingPlanet.getPlaats());
		n.scalair_vermenigvuldiging((this.getStraal() + ColidingPlanet.getStraal()) / n.modulus());
		setPlaats(Vector.optelling(ColidingPlanet.getPlaats(), n));
		// Beweegt de bal zodat het niet in de planeet is
	}

	public Vector InitialSpeed(Vector MousePosition) {
		Vector snelheid = Vector.aftrekking(MousePosition, getPlaats());
		if (snelheid.modulus() > MainFrame.MAX_POWER)
			snelheid.scalair_vermenigvuldiging(MainFrame.MAX_POWER / snelheid.modulus());
		return Vector.scalair_vermenigvuldiging(MainFrame.MOUSE_SPEED_COEFFICIENT, snelheid);
	}

	public Bal clone() {
		Bal dolly = new Bal(getPlaats().clone(), getMassa(), getStraal(), getKleur());
		dolly.setSnelheid(this.getSnelheid());
		dolly.setStationary(this.isStationary());
		return dolly;
	}
	
	public boolean outOfBounds(int breedte,int hoogte) {
		double x = getPlaats().getX();
		double y = getPlaats().getY();
		int r = getStraal();
		return (((x+r)<0) || ((y+r)<0) || ((x-r)>breedte) || ((y-r)>hoogte));
	}

	public boolean isStationary() {
		return stationary;
	}

	public void setStationary(boolean bool) {
		stationary = bool;
	}

	public boolean isCurrently_coliding() {
		return currently_coliding;
	}

	public void setCurrently_coliding(boolean currently_coliding) {
		this.currently_coliding = currently_coliding;
	}

}
