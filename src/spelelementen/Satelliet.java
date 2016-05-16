package spelelementen;

import gui_componenten.GameMain;
import tools.Vector;

public class Satelliet extends Cirkel {
	private double theta, hoeksnelheid, omwentelingstraal;
	private Planeet planeet;

	public Satelliet(double hoeksnelheid, Planeet planeet, double afstand_tot_planeet, double beginhoek, int straal) {
		super(new Vector((afstand_tot_planeet + planeet.getStraal()) * Math.cos(beginhoek),
				(afstand_tot_planeet + planeet.getStraal()) * Math.sin(beginhoek)), 0, straal);
		this.planeet = planeet;
		this.omwentelingstraal = afstand_tot_planeet + planeet.getStraal();
		this.hoeksnelheid = hoeksnelheid;
		this.theta = beginhoek;
		this.setSnelheid(snelheidberekening());
	}

	public void move() {
		theta += hoeksnelheid * GameMain.DeltaT;
		Vector nieuwe_plaats = planeet.getPlaats().clone();
		nieuwe_plaats.optelling(new Vector(omwentelingstraal * Math.cos(theta), omwentelingstraal * Math.sin(theta)));
		this.setPlaats(nieuwe_plaats);
		this.setSnelheid(snelheidberekening());
	}

	private Vector snelheidberekening() {
		return new Vector(-omwentelingstraal * hoeksnelheid * Math.sin(theta),
				omwentelingstraal * hoeksnelheid * Math.cos(theta));
	}

	public double getHoeksnelheid() {
		return hoeksnelheid;
	}

	public double getOmwentelingstraal() {
		return omwentelingstraal;
	}

	public Planeet getPlaneet() {
		return planeet;
	}
}
