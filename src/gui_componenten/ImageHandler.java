package gui_componenten;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageHandler {
	int planeetcounter = 0;
	int maancounter = 0;
	private final static String IMAGE_FOLDER = "images/";
	private final static int NR_PLANET_IMAGES = 10;
	private final static int NR_MOON_IMAGES = 4; 
	
	public Image BalImage() {
		return new ImageIcon(getClass().getResource(IMAGE_FOLDER + "golfbal.png")).getImage();
	}
	
	public Image PlaneetImage() {
		Image output =  new ImageIcon(getClass().getResource(IMAGE_FOLDER + "planeet" + planeetcounter + ".png")).getImage();
		planeetcounter = (planeetcounter + 1) % NR_PLANET_IMAGES;
		return output;
	}
	
	public Image MaanImage() {
		Image output =  new ImageIcon(getClass().getResource(IMAGE_FOLDER + "maan" + maancounter + ".png")).getImage();
		maancounter = (maancounter + 1) % NR_MOON_IMAGES;
		return output;
	}
	
	public Image HoleImage() {
		return new ImageIcon(getClass().getResource(IMAGE_FOLDER + "hole.png")).getImage();
	}
	
	public Image BackgroundImage() {
		return new ImageIcon(getClass().getResource(IMAGE_FOLDER + "space_11.png")).getImage();
	}
}
