package spelelementen;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TestPanel extends JPanel {
	Level level = new Level();
	public TestPanel() {
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 500, 500);
		level.golfbal.drawCirkel(g);
		for(Planeet planeet : level.planeten)
			planeet.drawCirkel(g);
	}

}