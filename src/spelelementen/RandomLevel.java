package spelelementen;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import tools.Vector;

public class RandomLevel {
	private static double RESERVED = 1;
	
	public static Level GenerateRandomLevel() {	
		Random rand = new Random();
		int aantalplaneten= rand.nextInt(2)+7;
		int aantalsatellieten =rand.nextInt(2)+5;
		System.out.println("pl: " + aantalplaneten);
		System.out.println("sat: " + aantalsatellieten);
		Planeet[] planeten= new Planeet[aantalplaneten];
		Satelliet[] satellieten = new Satelliet[aantalsatellieten];
		ArrayList<Cirkel> reserveerde_ruimtes = new ArrayList<Cirkel>(aantalplaneten);
		
		int[] indexen_van_planeten_met_satelliet = randomunique(0, aantalplaneten, aantalsatellieten);
		
		int nr_initialized_planets = 0;
		int nr_initialized_satelites = 0;
		while (nr_initialized_planets<aantalplaneten){
			int straal = rand.nextInt(71)+30;
			int massa = (int) (straal*straal*1.5);
			int x = rand.nextInt(MainFrame.breedte-straal*2)+straal; //kan een positie nemen in de breedte van het scherm zonder de straal
			int y = rand.nextInt(MainFrame.hoogte-straal*2)+straal; //hetzelfde maar voor de breedte
			Planeet planeet=new Planeet(x, y, massa, straal, Color.BLACK);
			int reserveerde_straal;
			if (contains(indexen_van_planeten_met_satelliet,nr_initialized_planets)) {
				double hoeksnelheid = rand.nextInt(2)+1;
				double afstand_tot_planeet = (rand.nextInt(5)+4)*10;
				double hoek = (rand.nextInt(5)+1)*Math.PI/5;
				int straalmaan = rand.nextInt(6)+15;
				satellieten[nr_initialized_satelites] = new Satelliet(hoeksnelheid, planeet, afstand_tot_planeet, Color.WHITE, hoek,straalmaan);
				reserveerde_straal = (int) (RESERVED*(afstand_tot_planeet + planeet.getStraal()+2*straalmaan));
			}else{
				reserveerde_straal = (int) (RESERVED*planeet.getStraal());
			}
			
			boolean checkcoliding = true;

			for (Cirkel ruimte : reserveerde_ruimtes) {
				if (planeet.isColiding(ruimte)) {
					checkcoliding = false;
				}
			}

			if (checkcoliding){
				planeten[nr_initialized_planets] = planeet;
				if (contains(indexen_van_planeten_met_satelliet,nr_initialized_planets))
					nr_initialized_satelites++;
				nr_initialized_planets++;
				reserveerde_ruimtes.add(new Cirkel(x,y,0,reserveerde_straal,Color.WHITE));
			}
		}//heeft een lijst aangemaakt van 4 of 5 planeten die elkaar niet raken en ergens op het scherm staan met
		 //een random straal tussen 30 en 100 en een massa die afhankelijk is van die straal
		
		Hole hole = new Hole(planeten[rand.nextInt(aantalplaneten)],(rand.nextInt(5)+1)*Math.PI/5, 20);
		//maakt een hole op een wilekeurige planeet
		Vector startPos = new Vector(5,5);
		Bal golfbal = new Bal(startPos, 1, 10, Color.WHITE);
		return new Level(planeten, satellieten, golfbal, hole, startPos);
		
	}
	
	private static int[] randomunique(int start, int end, int aantal) {
		Random rand = new Random();
		int[] output = new int[aantal];
		int counter = 0;
		while (counter < aantal) {
			int nummer = rand.nextInt(end-start) + (start);
			boolean uniek = true;
			for (int getal : output)
				if (nummer == getal)
					uniek = false;
			if (uniek){
				output[counter] = nummer;
				counter++;
			}
			//System.out.println(start + ", " + end + ", " + aantal);
			//System.out.println(counter);
			//System.out.println(Arrays.toString(output));
		}
		//System.out.println(start + ", " + end + ", " + aantal);
		System.out.println(Arrays.toString(output));
		return output;
	}
	
	private static boolean contains(int[] array,int nummer) {
		for (int element:array)
			if (element == nummer)
				return true;
		return false;
	}
	
}
