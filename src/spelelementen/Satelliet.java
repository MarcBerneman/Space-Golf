package spelelementen;

import java.awt.Color;

import tools.Vector;

public class Satelliet extends Cirkel {
	private double theta,hoeksnelheid, omwentelingstraal;
	private Planeet planeet;
		
	public Satelliet(double hoeksnelheid, Planeet planeet, double afstand_tot_planeet, Color kleur, double beginhoek) {
		super(new Vector((afstand_tot_planeet + planeet.getStraal())*Math.cos(beginhoek),(afstand_tot_planeet + planeet.getStraal())*Math.sin(beginhoek)), 0, 20, kleur);
		this.planeet = planeet;
		this.omwentelingstraal = afstand_tot_planeet + planeet.getStraal();
		this.hoeksnelheid = hoeksnelheid;
		this.theta = beginhoek;
	}
	
	public void move(){
		theta += hoeksnelheid*MainFrame.DeltaT;
		Vector nieuwe_plaats = planeet.getPlaats().clone();
		nieuwe_plaats.optelling(new Vector(omwentelingstraal*Math.cos(theta),omwentelingstraal*Math.sin(theta)));
		this.setPlaats(nieuwe_plaats);
	}
	
	public double getHoeksnelheid(){
		return hoeksnelheid;
	}
	public double getAfstand_tot_planeet(){
		return omwentelingstraal;
	}
	public Planeet getPlaneet(){
		return planeet;
	}
}
