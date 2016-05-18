package spelelementen;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import gui_componenten.LevelPanel;
import tools.Vector;

public class Cirkel {
	private Vector plaats;
	private final int massa, straal;
	private Vector snelheid;
	private Image image;

	public Cirkel(Vector plaats, int massa, int straal, Image image) {
		this.plaats = plaats;
		this.massa = massa;
		this.straal = straal;
		this.snelheid = new Vector(0, 0);
		this.image = image;
	}

	public Cirkel(int x, int y, int massa, int straal, Image image) {
		this.plaats = new Vector((double) x, (double) y);
		this.massa = massa;
		this.straal = straal;
		this.snelheid = new Vector(0, 0);
		this.image = image;
	}

	public Cirkel(Vector plaats, int massa, int straal, Vector snelheid, Image image) {
		this.plaats = plaats;
		this.massa = massa;
		this.straal = straal;
		this.snelheid = snelheid;
		this.image = image;
	}

	public Cirkel(int x, int y, int massa, int straal, Vector snelheid, Image image) {
		this.plaats = new Vector((double) x, (double) y);
		this.massa = massa;
		this.straal = straal;
		this.snelheid = snelheid;
		this.image = image;
	}

	public boolean isColiding(Cirkel other) {
		Vector this_to_other = Vector.aftrekking(this.plaats, other.plaats);
		return this_to_other.modulus() < this.straal + other.straal;
	}
	
	public void drawme(Graphics g, LevelPanel panel) {
		g.drawImage(image, (int) (plaats.getX() - straal), (int) (plaats.getY() - straal), 2 * straal, 2 * straal, panel);
	}

	public Vector getPlaats() {
		return plaats;
	}

	public void setPlaats(Vector plaats) {
		this.plaats = plaats;
	}

	public int getMassa() {
		return massa;
	}

	public int getStraal() {
		return straal;
	}

	public Vector getSnelheid() {
		return snelheid;
	}

	public void setSnelheid(Vector snelheid) {
		this.snelheid = snelheid;
	}

	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}

}
