package spelelementen;

import java.awt.Color;

public class Level {
	Bal golfbal;
	Planeet[] planeten;
	
	public Level() {
		golfbal = new Bal(100,100, 1, 5,Color.WHITE);
		planeten = new Planeet[2];
		planeten[0] = new Planeet(300, 300, 100000, 35,Color.BLUE);
		planeten[1] = new Planeet(100, 200, 100000, 50,Color.MAGENTA);
	}

}
