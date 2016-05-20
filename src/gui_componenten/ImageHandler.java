package gui_componenten;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageHandler {
	private int planeetcounter = 0;
	private int maancounter = 0;
	private final static String IMAGE_FOLDER = "images/";
	private final static int NR_PLANET_IMAGES = 14;
	private final static int NR_MOON_IMAGES = 9; 
	
	public Image BalImage() {
		return new ImageIcon(getClass().getResource(IMAGE_FOLDER + "golfbal.png")).getImage();
	}//geeft de golfbal een afbeelding, gebruik gemaakt van ProjectTemplate
	
	public Image PlaneetImage() {
		Image output =  new ImageIcon(getClass().getResource(IMAGE_FOLDER + "planeet" + planeetcounter + ".png")).getImage();
		planeetcounter = (planeetcounter + 1) % NR_PLANET_IMAGES;
		return output;
	}//geeft de planeet een afbeelding met naam planeet+getal(planeetcounter) de planeetcounter begint terug bij de eerste afbeelding als ze allemaal aan bod zijn gekomen
	
	public Image MaanImage() {
		Image output =  new ImageIcon(getClass().getResource(IMAGE_FOLDER + "maan" + maancounter + ".png")).getImage();
		maancounter = (maancounter + 1) % NR_MOON_IMAGES;
		return output;
	}// hetzelfde maar voor de satellieten
	
	public Image HoleImage() {
		return new ImageIcon(getClass().getResource(IMAGE_FOLDER + "hole.png")).getImage();
	}//geeft het zwart gat een afbeelding
	
	public Image BackgroundImage() {
		return new ImageIcon(getClass().getResource(IMAGE_FOLDER + "space_11.jpg")).getImage();
	}//geeft de achtergrond een afbeelding
}
