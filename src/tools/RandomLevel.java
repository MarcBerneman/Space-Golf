package tools;

import java.util.ArrayList;
import java.util.Random;

import gui_componenten.GameMain;
import gui_componenten.ImageHandler;
import spelelementen.*;

public class RandomLevel {
	private final static double RESERVED = 1.5;
	private final static double DICHTHEID = 1;

	public static Level GenerateRandomLevel() {
		Random rand = new Random();
		ImageHandler imghandler = new ImageHandler();
		
		int aantalplaneten = rand.nextInt(3) + 5; //bepaalt hoeveel planeten er moeten zijn van 5 tot 7
		int aantalsatellieten = rand.nextInt(aantalplaneten-2) + 2; //bepaalt het aantal manen, van 2 tot het aantal planeten-1(er kan maar 1 maan op 1 planeet
		int aantal_loops = 0; //aantal loops zodat het niet oneindig lang een planeet probeert te tekenen als het onmogelijk is(als er geen plaats zou zijn voor die planeet)
		Planeet[] planeten = new Planeet[aantalplaneten];
		Satelliet[] satellieten = new Satelliet[aantalsatellieten];
		ArrayList<Cirkel> reserveerde_ruimtes = new ArrayList<Cirkel>(aantalplaneten);//Dit is een ArrayList met cirkels, elke cirkel omvat een planeet en de maan ervan(als het er een heeft) 

		int[] indexen_van_planeten_met_satelliet = randomunique(0, aantalplaneten, aantalsatellieten);//bepaalt de planeten die een maan gaan hebben

		int nr_initialized_planets = 0;//staat voor het aantal planeten die al bepaald zijn(in de lijst)
		int nr_initialized_satelites = 0;//staat voor het aantal manen die al bepaald zijn(in de lijst)
		while (nr_initialized_planets < aantalplaneten) {
			int straal = rand.nextInt(71) + 50;
			int massa = (int) (DICHTHEID * straal * straal);
			int x = rand.nextInt(GameMain.BREEDTE - straal * 2) + straal; // kan een positie nemen in de breedte van het scherm zonder de straal 
			int y = rand.nextInt(GameMain.HOOGTE - straal * 2) + straal; // hetzelfde maar voor de breedte
			Planeet planeet = new Planeet(x, y, massa, straal,null);
			//zolang het aantal planeten in de lijst niet het aantal is dat werd gevraagd, maakt het willekeurige planeten aan
			
			int reserveerde_straal;// is de straal van de cirkel waarin geen andere planeet in mag komen
			if (contains(indexen_van_planeten_met_satelliet, nr_initialized_planets)) {//checkt of de planeet een planeet is die een maan moet hebben,
				double hoeksnelheid = ((rand.nextDouble() * 2) + 3) * RandomSign();
				double hoek = rand.nextDouble() * 2 * Math.PI;
				double afstand_tot_planeet = (rand.nextInt(5) + 4) * 10;
				int straalmaan = rand.nextInt(6) + 15;
				satellieten[nr_initialized_satelites] = new Satelliet(hoeksnelheid, planeet, afstand_tot_planeet, hoek,
						straalmaan,null);
				reserveerde_straal = (int) (RESERVED * (afstand_tot_planeet + planeet.getStraal() + 2 * straalmaan));//vermenigvuldigt met resreved zodat de reserveerde straal iets groter zou zijn=> eenbeetje afstand tussen de gebieden
				//indien ja geeft het het een maan en wordt de reserveerde straal de afstand van het middelpunt van de planeet tot het buitenst van de maan
			} else {
				reserveerde_straal = (int) (RESERVED * planeet.getStraal());
				//indien nee wordt de reserveerde straal de straal van planeet
			}
			Cirkel bezetgebied = new Cirkel(x, y, 0, reserveerde_straal,null);
			//het gebied bezet door die planeet is dus een cirkel die de planeet en de maan omvat(als er een is)

			boolean checkcoliding = false;
			//we geaan er van uit dat het gebied geen ander gebied kruist
			for (Cirkel ruimte : reserveerde_ruimtes) {
				if (bezetgebied.isColiding(ruimte)) {
					checkcoliding = true;
				}
			}//voor alle gebieden die we al hebben gedefinieerd, of het nieuwe gebied met een van die gebieden kruist

			if (!checkcoliding) {
				planeten[nr_initialized_planets] = planeet;
				planeet.setImage(imghandler.PlaneetImage());
				if (contains(indexen_van_planeten_met_satelliet, nr_initialized_planets)) {
					satellieten[nr_initialized_satelites].setImage(imghandler.MaanImage());
					nr_initialized_satelites++;
				}//als ze niet kruisen, voegen we de planeet en de maan (als er een is) aan hun lijst en geven we ze een afbeelding
				nr_initialized_planets++;
				reserveerde_ruimtes.add(bezetgebied);//en we voegen het bezet gebied aan de lijst met bezette gebieden
			}
			if (aantal_loops > 2000) {
				aantal_loops = 0;
				planeten = new Planeet[aantalplaneten];
				satellieten = new Satelliet[aantalsatellieten];
				reserveerde_ruimtes = new ArrayList<Cirkel>(aantalplaneten);
				nr_initialized_planets = 0;
				nr_initialized_satelites = 0;
			}//zorgt ervoor dat er geen planeten blijven worden aangemaakt als het onmogelijk is om er een toe te voegende bezette gebieden zijn bv te groot en er is geen plaats meer) als begint dan terug van nul
			aantal_loops++;
		}
		
		Hole hole = new Hole(planeten[rand.nextInt(aantalplaneten)], rand.nextDouble() * 2 * Math.PI, 20,imghandler.HoleImage());
		// maakt een hole op een wilekeurige planeet
		int kwadrant = geefkwadrant(hole.getPlaats());//checkt in welk kwadrant het zwart gat ligt
		Vector startPos = geefplaats(kwadrant);//de beginplaats van de golfbal is altijd de verste hoek van het kwadrant
		Bal golfbal = new Bal(startPos, 1, 10,imghandler.BalImage());
		return new Level(planeten, satellieten, golfbal, hole, startPos, 0);

	}
	
	private static Vector geefplaats(int kwadrant) {
		double x,y;
		if (kwadrant % 2 == 1) {
			x= GameMain.BREEDTE-10;
		}else{
			x= 10;
		}
		if (kwadrant < 3) {
			y= GameMain.HOOGTE-10;
		}else{
			y= 10;
		}
		return new Vector(x,y);
	}//je geeft een kwadrant in (linksboven=1, rechtssboven=2, linksonder=3, rechtssonder=4) en je krijgt de plaats van een hoek aan de tegeovergestelde kant terug
	
	private static int geefkwadrant(Vector plaats) {
		int kwadrant;
		if (plaats.getX() < GameMain.BREEDTE/2){
			if (plaats.getY() < GameMain.HOOGTE/2){
				kwadrant = 1;
			}else{
				kwadrant = 3;
			}
		}else{
			if (plaats.getY() < GameMain.HOOGTE/2){
				kwadrant = 2;
			}else{
				kwadrant = 4;
			}
		};
		return kwadrant;
	}//je geeft een plaats en je krijgt het kwadrant terug waarin die plaats ligt

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
			}// checkt of het getal dat je er aan wilt toevoegen er al in zit, indien nee voegt het het toe en gaat het naar het volgend getal, indien ja doet het het opnieuw
		}
		return output;
	}//maakt een lijst van willekeurige getallen van (start) tot (end) die elk maximaal een keer voorkomen, de lengte van die lijst wordt gegeven door aantal

	private static boolean contains(int[] array, int nummer) {
		for (int element : array)
			if (element == nummer)
				return true;
		return false;
	}//ziet of een lijst een getal bevat

	private static int RandomSign() {
		Random rand = new Random();
		return (rand.nextBoolean() ? 1 : -1);
	}

}
