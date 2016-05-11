package spelelementen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import tools.Vector;

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements MouseListener, KeyListener, ActionListener, MouseMotionListener {
	private final Level level;
	private Timer timer;
	private Vector muis_positie = new Vector(0,0);

	public MainPanel(Level level) {
		this.level = level;
		setBackground(Color.BLACK);
		setFocusable(true);
		addMouseListener(this);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (Planeet planeet : level.getPlaneten()) {
			planeet.paintme(g);
		}
		level.getGolfbal().paintme(g);
		level.getHole().paintme(g);
		for(Satelliet satelliet : level.getSatellieten()){
			satelliet.paintme(g);
		}
		if (level.getGolfbal().isStationary()){
			Traject.Aim(g, level.getGolfbal(), level.getPlaneten(), muis_positie, level.getHemellichamen());
		}
		
	}

	public Level getLevel() {
		return level;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!level.getGolfbal().isStationary()) {
			level.turn();
			repaint();
		} else {
			timer.stop();
			System.out.println("Next Turn");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Bal b = level.getGolfbal();
		if (b.isStationary()) {
			b.setSnelheid(b.InitialSpeed(muis_positie));
			b.setStationary(false);
			timer = new Timer((int) (MainFrame.DeltaT * 1000), this);
			timer.start();
			System.out.println("GO");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_R:
			level.ResetBall();
//satellieten ook terug op startpositie?
			repaint();
			break;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		muis_positie = new Vector(e.getX(), e.getY());
		repaint();

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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
