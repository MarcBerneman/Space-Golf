package spelelementen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import tools.Vector;

public class OutOfBoundsBox {
	private final static int BOXHOOGTE = 30, BOXBREEDTE = 46;

	public static Vector box_plaats(Bal bal, int breedte, int hoogte) {
		Vector midden = new Vector(breedte / 2, hoogte / 2);
		Vector midden_to_bal = Vector.aftrekking(bal.getPlaats(), midden);
		double theta;
		if (midden_to_bal.getY() <= 0)
			theta = Math.acos(midden_to_bal.getX() / midden_to_bal.modulus());
		else
			theta = 2 * Math.PI - Math.acos(midden_to_bal.getX() / midden_to_bal.modulus());
		double phi = Math.atan(((double) hoogte) / (double) breedte);
		double x;
		double y;
		double rico = (bal.getPlaats().getY() - midden.getY()) / (bal.getPlaats().getX() - midden.getX());
		if (theta >= 2 * Math.PI - phi) {
			x = breedte;
			y = rico * (x - midden.getX()) + midden.getY();
		} else if (theta >= Math.PI + phi) {
			y = hoogte;
			x = (1 / rico) * (y - midden.getY()) + midden.getX();
		} else if (theta >= Math.PI - phi) {
			x = 0;
			y = rico * (x - midden.getX()) + midden.getY();
		} else if (theta >= phi) {
			y = 0;
			x = (1 / rico) * (y - midden.getY()) + midden.getX();
		} else {
			x = breedte;
			y = rico * (x - midden.getX()) + midden.getY();
		}
		return new Vector(x, y);
	}

	private static double distance(Bal bal, int breedte, int hoogte) {
		double afstand = Vector.aftrekking(bal.getPlaats(), box_plaats(bal, breedte, hoogte)).modulus();
		return afstand;
	}

	public static void drawBox(Graphics g, Bal bal, int breedte, int hoogte) {
		g.setColor(Color.WHITE);
		Vector box = box_plaats(bal, breedte, hoogte);
		int x = (int) box.getX();
		int y = (int) box.getY();
		int x_draw, y_draw;
		if (x == 0) {
			x_draw = 0;
			y_draw = y - BOXHOOGTE / 2;
		} else if (x == breedte) {
			x_draw = breedte - BOXBREEDTE;
			y_draw = y - BOXHOOGTE / 2;
		} else if (y == 0) {
			x_draw = x - BOXBREEDTE / 2;
			y_draw = 0;
		} else { // y == hoogte
			x_draw = x - BOXBREEDTE / 2;
			y_draw = hoogte - BOXHOOGTE;
		}
		g.drawRect(x_draw, y_draw, BOXBREEDTE, BOXHOOGTE);
		int distance = (int) distance(bal, breedte, hoogte);
		String str;
		if (distance > 9000)
			str = ">9000";
		else
			str = Integer.toString(distance);
		centerString(g, str, x_draw + BOXBREEDTE / 2, y_draw + BOXHOOGTE / 2, new Font("Cambria", Font.PLAIN, 16));
	}

	private static void centerString(Graphics g, String s, int x, int y, Font font) {
		// overgenomen van
		// http://parallel.vub.ac.be/education/java/practica/Reeks5/Puzzelpaneel.java
		Rectangle2D r2D = font.getStringBounds(s, new FontRenderContext(null, true, true));
		int rWidth = (int) Math.round(r2D.getWidth());
		int rHeight = (int) Math.round(r2D.getHeight());

		g.setFont(font);
		g.drawString(s, x - rWidth / 2, y - rHeight / 2 + font.getSize());
	}

}
