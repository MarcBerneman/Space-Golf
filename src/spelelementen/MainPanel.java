package spelelementen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;

import javax.swing.JPanel;

import tools.Vector;

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements MouseListener{
	private Level level;
	Timer timer = new Timer();

	public MainPanel(Level level) {
		this.level = level;
		setBackground(Color.BLACK);
		addMouseListener(this);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse Clicked");
		if(level.golfbal.isStationary) {
			level.golfbal.setSnelheid(Vector.aftrekking(new Vector(e.getX(),e.getY()), level.golfbal.getPlaats()));
			level.golfbal.isStationary = false;
			timer.schedule(new BalBewegingTask(this), 0, (long) (MainFrame.DeltaT * 1000));
		}
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

}
