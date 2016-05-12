package spelelementen;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import tools.Vector;

public class RandomLevel {
	
	public static Level GenerateRandomLevel() {
		Random rand = new Random();
		int aantalplaneten= rand.nextInt(2)+3;
		Planeet[] planeten= new Planeet[aantalplaneten];
		ArrayList<Planeet> planetenarray = new ArrayList<Planeet>();
		while (planetenarray.size()<aantalplaneten){
			int straal = rand.nextInt(71)+30;
			int massa = (int) (straal*straal*1.5);
			int x = rand.nextInt(MainFrame.breedte-straal*2)+straal; //kan een positie nemen in de breedte van het scherm zonder de straal
			int y = rand.nextInt(MainFrame.hoogte-straal*2)+straal; //hetzelfde maar voor de breedte
			Planeet planeet=new Planeet(x, y, massa, straal, Color.BLACK);
			boolean checkcoliding=true;
			if (planetenarray.size()>0){
				for (Planeet planeetjes:planetenarray){
					if (planeetjes.isColiding(planeet)){
						checkcoliding =false;
					}
				}
			}
			if (checkcoliding ==true){
				planetenarray.add(planeet);
				planeten[planetenarray.size()-1] = planeet;
			}
		}//heeft een lijst aangemaakt van 4 of 5 planeten die elkaar niet raken en ergens op het scherm staan met
		 //een random straal tussen 30 en 100 en een massa die afhankelijk is van die straal
		int aantalsatellieten =rand.nextInt(2)+1;
		Satelliet[] satellieten = new Satelliet[aantalsatellieten];
		for (int i= 0; i< aantalsatellieten; i++){
			double hoeksnelheid = rand.nextInt(2)+1;
			Planeet planeet = planeten[rand.nextInt(aantalplaneten)];
			double afstand = (rand.nextInt(5)+4)*10;
			double hoek = (rand.nextInt(5)+1)*Math.PI/5;
			satellieten[i] = new Satelliet(hoeksnelheid, planeet, afstand, Color.WHITE, hoek);
		}//heeft een lijst aangemaakt van 1 of 2 planeten die rond een willekeurige planeet staan(kan dezelfde zijn)
		Hole hole = new Hole(planeten[rand.nextInt(aantalplaneten)],(rand.nextInt(5)+1)*Math.PI/5, 20);
		//maakt een hole op een wilekeurige planeet
		Vector startPos = new Vector(5,5);
		Bal golfbal = new Bal(startPos, 1, 10, Color.WHITE);
		return new Level(planeten, satellieten, golfbal, hole, startPos);
		
	}
}
