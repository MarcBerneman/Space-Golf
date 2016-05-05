package spelelementen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;

import javax.swing.JPanel;

import tools.Vector;

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements MouseListener, KeyListener {
	private final Level level;
	private Timer timer = new Timer();

	public MainPanel(Level level) {
		this.level = level;
		setBackground(Color.BLACK);
		setFocusable(true);
		addMouseListener(this);
		addKeyListener(this);
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (Planeet planeet : level.getPlaneten()) {
			planeet.paintme(g);
		}
		level.getGolfbal().paintme(g);
	}

	public Level getLevel() {
		return level;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (level.getGolfbal().isStationary) {
			Vector snelheid = Vector.aftrekking(new Vector(e.getX(), e.getY()), level.getGolfbal().getPlaats());
			level.getGolfbal().setSnelheid(Vector.scalair_vermenigvuldiging(MainFrame.MOUSE_SPEED_COEFFICIENT, snelheid));
			level.getGolfbal().isStationary = false;
			timer.schedule(new BalBewegingTask(this), 0, (long) (MainFrame.DeltaT * 1000));
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_R:
			level.ResetBall();
			repaint();
			break;
		}
	}

	public Timer getTimer() {
		return timer;
	}

	public void ReinitializeTimer() {
		timer = new Timer();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
