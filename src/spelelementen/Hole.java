package spelelementen;

import java.awt.Color;

import tools.Vector;



public class Hole extends Cirkel{
	
	public Hole(Planeet planeet, double theta, int straal){
		super(new Vector(planeet.getStraal()*Math.cos(theta)+planeet.getPlaats().getX(),planeet.getStraal()*Math.sin(theta)+planeet.getPlaats().getY()), 0, straal, Color.RED);		
	}
	
	public boolean Score(Bal golfbal){
		return golfbal.isColiding(this);
	}
	
}
