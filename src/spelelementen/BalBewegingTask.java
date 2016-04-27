package spelelementen;

import java.util.TimerTask;

public class BalBewegingTask extends TimerTask {
	private MainPanel panel;

	public BalBewegingTask(MainPanel panel) {
		this.panel = panel;
	}

	@Override
	public void run() {
		panel.getLevel().turn();
		panel.repaint();
	}

}
