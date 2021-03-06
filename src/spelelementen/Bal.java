package spelelementen;

import java.awt.Image;

import gui_componenten.GameMain;
import tools.Vector;

public class Bal extends Cirkel {
	private boolean stationary = true;
	private boolean currently_coliding = false;

	public Bal(Vector plaats, int massa, int straal, Image image) {
		super(plaats.clone(), massa, straal, image);
	}

	public void Correctie(Cirkel ColidingPlanet) {
		Vector n = Vector.aftrekking(getPlaats(), ColidingPlanet.getPlaats());
		n.scalair_vermenigvuldiging((this.getStraal() + ColidingPlanet.getStraal()) / n.modulus());
		setPlaats(Vector.optelling(ColidingPlanet.getPlaats(), n));
		// Beweegt de bal zodat het niet in de planeet is
	}

	public Vector InitialSpeed(Vector MousePosition) {
		Vector snelheid = Vector.aftrekking(MousePosition, getPlaats());
		if (snelheid.modulus() > GameMain.MAX_POWER)
			snelheid.scalair_vermenigvuldiging(GameMain.MAX_POWER / snelheid.modulus());
		return Vector.scalair_vermenigvuldiging(GameMain.MOUSE_SPEED_COEFFICIENT, snelheid);
	}//de snelheid is de vector van de golfbal tot de muis, deze vector kan een bepaalde,gegeven waarde niet overschrijden

	public Bal clone() {
		Bal dolly = new Bal(getPlaats().clone(), getMassa(), getStraal(),getImage());
		dolly.setSnelheid(this.getSnelheid());
		dolly.setStationary(this.isStationary());
		return dolly;
	}

	public boolean outOfBounds(int breedte, int hoogte) {
		double x = getPlaats().getX();
		double y = getPlaats().getY();
		int r = getStraal();
		return (((x + r) < 0) || ((y + r) < 0) || ((x - r) > breedte) || ((y - r) > hoogte));
	}//checkt of de bal buiten het scherm is, niet het middelpunt van de bal maar wel de voledige bal

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
