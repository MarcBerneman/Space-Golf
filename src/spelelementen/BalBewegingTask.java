package spelelementen;

import java.util.TimerTask;

public class BalBewegingTask extends TimerTask {
	private final MainPanel panel;

	public BalBewegingTask(MainPanel panel) {
		this.panel = panel;
	}

	@Override
	public void run() {
		if (!panel.getLevel().getGolfbal().isStationary) {
			panel.getLevel().turn();
			panel.repaint();
		} else {
			//panel.getTimer().cancel();
			//panel.ReinitializeTimer();
			System.out.println("Next Turn");
		}
	}

}
