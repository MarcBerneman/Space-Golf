package spelelementen;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	private Level level;
	private Timer timer = new Timer();

	public MainPanel(Level level) {
		this.level = level;
		setBackground(Color.BLACK);
		timer.schedule(new BalBewegingTask(this), 0, (long) (MainFrame.DeltaT * 1000));
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (Planeet planeet : level.planeten) {
			planeet.paintme(g);
		}
		level.golfbal.paintme(g);
	}

	public Level getLevel() {
		return level;
	}

}
