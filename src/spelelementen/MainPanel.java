package spelelementen;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	Level level;

	public MainPanel(Level level) {
		this.level = level;
		setBackground(Color.BLACK);
		System.out.println(level.golfbal.isColiding(level.planeten));
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (Planeet planeet : level.planeten) {
			planeet.paintme(g);
		}
		level.golfbal.paintme(g);
	}
}
