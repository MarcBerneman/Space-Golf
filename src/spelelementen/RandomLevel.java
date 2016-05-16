package spelelementen;

import java.util.ArrayList;
import java.util.Random;

import gui_componenten.GameMain;
import tools.Vector;

public class RandomLevel {
	private final static double RESERVED = 1.5;
	private final static double DICHTHEID = 0.75;

	public static Level GenerateRandomLevel() {
		Random rand = new Random();
		int aantalplaneten = rand.nextInt(3) + 3;
		int aantalsatellieten = rand.nextInt(2) + 1;
		if (aantalsatellieten > aantalplaneten)
			aantalsatellieten = rand.nextInt(2) + 1;
		int aantal_loops = 0;
		Planeet[] planeten = new Planeet[aantalplaneten];
		Satelliet[] satellieten = new Satelliet[aantalsatellieten];
		ArrayList<Cirkel> reserveerde_ruimtes = new ArrayList<Cirkel>(aantalplaneten);

		int[] indexen_van_planeten_met_satelliet = randomunique(0, aantalplaneten, aantalsatellieten);

		int nr_initialized_planets = 0;
		int nr_initialized_satelites = 0;
		while (nr_initialized_planets < aantalplaneten) {
			int straal = rand.nextInt(71) + 50;
			int massa = (int) (DICHTHEID * straal * straal);
			int x = rand.nextInt(GameMain.BREEDTE - straal * 2) + straal; // kan een positie nemen in de breedte van het scherm zonder de straal 
			int y = rand.nextInt(GameMain.HOOGTE - straal * 2) + straal; // hetzelfde maar voor de breedte
			Planeet planeet = new Planeet(x, y, massa, straal);
			int reserveerde_straal;
			if (contains(indexen_van_planeten_met_satelliet, nr_initialized_planets)) {
				// double hoeksnelheid = rand.nextInt(2)+1;
				// double hoek = (rand.nextInt(5)+1)*Math.PI/5;
				double hoeksnelheid = ((rand.nextDouble() * 2) + 3) * RandomSign();
				double hoek = rand.nextDouble() * 2 * Math.PI;
				double afstand_tot_planeet = (rand.nextInt(5) + 4) * 10;
				int straalmaan = rand.nextInt(6) + 15;
				satellieten[nr_initialized_satelites] = new Satelliet(hoeksnelheid, planeet, afstand_tot_planeet, hoek,
						straalmaan);
				reserveerde_straal = (int) (RESERVED * (afstand_tot_planeet + planeet.getStraal() + 2 * straalmaan));
			} else {
				reserveerde_straal = (int) (RESERVED * planeet.getStraal());
			}
			Cirkel bezetgebied = new Cirkel(x, y, 0, reserveerde_straal);

			boolean checkcoliding = true;

			for (Cirkel ruimte : reserveerde_ruimtes) {
				if (bezetgebied.isColiding(ruimte)) {
					checkcoliding = false;
				}
			}

			if (checkcoliding) {
				planeten[nr_initialized_planets] = planeet;
				if (contains(indexen_van_planeten_met_satelliet, nr_initialized_planets))
					nr_initialized_satelites++;
				nr_initialized_planets++;
				reserveerde_ruimtes.add(bezetgebied);
			}
			if (aantal_loops > 2000) {
				aantal_loops = 0;
				planeten = new Planeet[aantalplaneten];
				satellieten = new Satelliet[aantalsatellieten];
				reserveerde_ruimtes = new ArrayList<Cirkel>(aantalplaneten);
				nr_initialized_planets = 0;
				nr_initialized_satelites = 0;
			}
			aantal_loops++;
		}
		// heeft een lijst aangemaakt van 4 of 5 planeten die elkaar niet raken
		// en ergens op het scherm staan met
		// een random straal tussen 30 en 100 en een massa die afhankelijk is
		// van die straal

		Hole hole = new Hole(planeten[rand.nextInt(aantalplaneten)], rand.nextDouble() * 2 * Math.PI, 20);
		// maakt een hole op een wilekeurige planeet
		int kwadrant = geefkwadrant(hole.getPlaats());
		Vector startPos = geefplaats(kwadrant);
		Bal golfbal = new Bal(startPos, 1, 10);
		return new Level(planeten, satellieten, golfbal, hole, startPos, 0);

	}

	private static Vector geefplaats(int kwadrant) {
		double x, y;
		if (kwadrant % 2 == 1) {
			x = GameMain.BREEDTE - 10;
		} else {
			x = 10;
		}
		if (kwadrant < 3) {
			y = GameMain.HOOGTE - 10;
		} else {
			y = 10;
		}
		return new Vector(x, y);
	}

	private static int geefkwadrant(Vector plaats) {
		int kwadrant;
		if (plaats.getX() < GameMain.BREEDTE / 2) {
			if (plaats.getY() < GameMain.HOOGTE / 2) {
				kwadrant = 1;
			} else {
				kwadrant = 3;
			}
		} else {
			if (plaats.getY() < GameMain.HOOGTE / 2) {
				kwadrant = 2;
			} else {
				kwadrant = 4;
			}
		}
		;
		return kwadrant;
	}

	private static int[] randomunique(int start, int end, int aantal) {
		Random rand = new Random();
		int[] output = new int[aantal];
		int counter = 0;
		while (counter < aantal) {
			int nummer = rand.nextInt(end - start) + (start);
			boolean uniek = true;
			for (int getal : output)
				if (nummer == getal)
					uniek = false;
			if (uniek) {
				output[counter] = nummer;
				counter++;
			}
		}
		return output;
	}

	private static boolean contains(int[] array, int nummer) {
		for (int element : array)
			if (element == nummer)
				return true;
		return false;
	}

	private static int RandomSign() {
		Random rand = new Random();
		return (rand.nextBoolean() ? 1 : -1);
	}

}
