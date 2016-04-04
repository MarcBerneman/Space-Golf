package spelelementen;

import java.awt.Color;

import tools.Vector;

public class Level {
	Bal golfbal;
	Planeet[] planeten;
	
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
		//Nog te doen: snelheid van bal aanpassen en dan plaats van bal aanpassen .
	}

}
