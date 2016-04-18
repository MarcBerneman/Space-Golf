package spelelementen;

import java.awt.Color;

import tools.Vector;

public class Level {
	Bal golfbal;
	Planeet[] planeten;
	final static double DeltaT = 0.01;
	
	public Level() {
		golfbal = new Bal(250,150, 1, 5,Color.WHITE);
		planeten = new Planeet[2];
		planeten[0] = new Planeet(300, 300, 100000, 35,Color.BLUE);
		planeten[1] = new Planeet(100, 200, 100000, 80,Color.MAGENTA);
	}
	
	public void turn() {
		Vector F_tot = new Vector(0,0);
		for(Planeet planeet : planeten)
			F_tot = Vector.optelling(F_tot, planeet.zwaartekrachtveld(golfbal.plaats));
		F_tot = Vector.scalair_vermenigvuldiging((double) golfbal.massa, F_tot);
		golfbal.snelheid.optelling(Vector.scalair_vermenigvuldiging(DeltaT/golfbal.massa, F_tot));
		golfbal.plaats.optelling(Vector.scalair_vermenigvuldiging(DeltaT, golfbal.snelheid));
		if(golfbal.isColiding(planeten)) {
			
		}
		//Nog te doen: snelheid van bal aanpassen en dan plaats van bal aanpassen .
	}

}
