package tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import spelelementen.Bal;
import spelelementen.Hole;
import spelelementen.Level;
import spelelementen.Planeet;
import spelelementen.Satelliet;

public class LevelBuilder {
	private final int aantal_levels = 6;
	private String level;
	private static final String RESOURCE_FOLDER = "textfiles/";
	private static final String fileName = RESOURCE_FOLDER + "Levels.txt";
	private final static double DICHTHEID = 1;


	public LevelBuilder(int level) {
		this.level = "L" + level;
	}

	public Level Build() {
		Level lev = null;
		try {

			InputStream in = getClass().getResourceAsStream(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String line = reader.readLine().trim();
			boolean found = false;

			while (line != null && !found) {
				if (line.equals(level)) {
					found = true;
				} else {
					line = reader.readLine().trim();
				}
			}

			line = reader.readLine();
			String[] theLine = line.split(",");
			Planeet[] planeten = planetenBuilder(theLine);

			theLine = reader.readLine().split(",");
			Satelliet[] satellieten = satellietenBuilder(theLine, planeten);

			theLine = reader.readLine().split(",");
			Hole hole = holeBuilder(theLine, planeten);

			theLine = reader.readLine().split(",");
			Vector startpos = startposBuilder(theLine);

			Bal golfbal = golfbalBuilder(startpos);

			theLine = reader.readLine().split(",");
			reader.close();
			int par = Integer.parseInt(theLine[0]);

			lev = new Level(planeten, satellieten, golfbal, hole, startpos, par);

		} catch (FileNotFoundException e) {
			System.out.println("File not Found: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}

		return lev;
	}

	private Vector startposBuilder(String[] theLine) {
		Vector startpos = new Vector(Integer.parseInt(theLine[0]), Integer.parseInt(theLine[1]));
		return startpos;
	}

	private Bal golfbalBuilder(Vector startpos) {
		Bal golfbal = new Bal(startpos, 1, 10);
		return golfbal;
	}

	private Hole holeBuilder(String[] theLine, Planeet[] planeten) {
		Hole hole = new Hole(planeten[Integer.parseInt(theLine[0])], Double.parseDouble(theLine[1]), 20);
		return hole;
	}

	private Satelliet[] satellietenBuilder(String[] theLine, Planeet[] planeten) {
		int aantal_sat = Integer.parseInt(theLine[0]);
		Satelliet[] satellieten = null;
		satellieten = new Satelliet[aantal_sat];
		for (int i = 0; i < aantal_sat; i++) {
			double hoeksnelheid = Double.parseDouble(theLine[i + 1]);
			Planeet planeet = planeten[Integer.parseInt(theLine[i + 1 + aantal_sat])];
			double afstand_tot_planeet = Double.parseDouble(theLine[i + 1 + 2 * aantal_sat]);
			double beginhoek = Double.parseDouble(theLine[i + 1 + 3 * aantal_sat]);
			int straal = Integer.parseInt(theLine[i + 1 + 4 * aantal_sat]);
			satellieten[i] = new Satelliet(hoeksnelheid, planeet, afstand_tot_planeet, beginhoek, straal);
		}
		return satellieten;
	}

	private Planeet[] planetenBuilder(String[] theLine) {
		int aantal_planeten = Integer.parseInt(theLine[0]);
		Planeet[] planeten = new Planeet[aantal_planeten];
		for (int i = 0; i < aantal_planeten; i++) {
			Vector pos = new Vector(Integer.parseInt(theLine[i + 1]),
					Integer.parseInt(theLine[i + 1 + aantal_planeten]));
			int straal = Integer.parseInt(theLine[i + 1 + 2 * aantal_planeten]);
			int massa = (int) (DICHTHEID * straal * straal);
			planeten[i] = new Planeet(pos, massa, straal);
		}
		return planeten;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = "L" + level;
	}
}
