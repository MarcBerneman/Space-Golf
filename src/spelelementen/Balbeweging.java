package spelelementen;

import java.util.TimerTask;

import javax.swing.JPanel;

public class Balbeweging extends TimerTask {
	private MainPanel panel;
	public Balbeweging(MainPanel panel) {
		this.panel = panel;
	}

	@Override
	public void run() {
		panel.level.turn();
		panel.repaint();
	}

}
