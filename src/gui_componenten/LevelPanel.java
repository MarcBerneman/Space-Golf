package gui_componenten;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import spelelementen.*;
import tools.Vector;

public class LevelPanel extends JPanel implements MouseListener, ActionListener, MouseMotionListener {
	private static final long serialVersionUID = 335863881169194831L;
	private Level level;
	private Timer timer = new Timer((int) (GameMain.DeltaT * 1000), this);
	private Vector muis_positie = new Vector(0, 0);
	final static String IMAGE_FOLDER = "images/";
	private PlayPanel playpanel;
	
	public LevelPanel(Level level, PlayPanel playpanel) {
		this.level = level;
		this.playpanel = playpanel;
		setPreferredSize(new Dimension(GameMain.BREEDTE, GameMain.HOOGTE));
		setFocusable(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.BLACK);
		timer.start();
	}
	
	public void paint(Graphics g) {
		ImageHandler imghandler = new ImageHandler();
		super.paint(g);
		g.drawImage(imghandler.BackgroundImage(), 0, 0, getWidth(), getHeight(), this);
		for(Planeet planeet : level.getPlaneten())
			planeet.drawme(g, this);
		level.getHole().drawme(g, this);
		for(Satelliet satelliet : level.getSatellieten())
			satelliet.drawme(g, this);
		//tekent de achtergrond, de planeten, het zwart gat en de manen
		
		if (!level.getHole().getScored()) {
			if (level.getGolfbal().outOfBounds(GameMain.BREEDTE, GameMain.HOOGTE)) {
				OutOfBoundsBox.drawBox(g, level.getGolfbal(), GameMain.BREEDTE, GameMain.HOOGTE);
			}
			level.getGolfbal().drawme(g, this);
			if (level.getGolfbal().isStationary()) {
				Traject.Aim(g, level.getGolfbal(), level.getPlaneten(), muis_positie, level.getHemellichamen());
			}
		}//zolang er niet is gescoord, als de golfbal buiten het scherm komt, komt de box tevoorschijn, wordt de golfbal getekend en berekent men het traject
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		level.turn();
		repaint();
		if (level.getHole().getScored())
			playpanel.getNext().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!level.getHole().getScored()) {
			Bal b = level.getGolfbal();
			if (b.isStationary()) {
				b.setSnelheid(b.InitialSpeed(muis_positie));
				b.setStationary(false);
				level.incrementStrokes();
				if(GameMain.totalstrokes != -1) GameMain.totalstrokes++;
				updateInformation();
			}
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		muis_positie = new Vector(e.getX(), e.getY());
		repaint();

	}
	
	public void updateInformation() {
		String text = "Strokes: " + level.getNr_strokes() + ", Par: " + (level.getPar() > 0 ? level.getPar() : "N/A")
				+ (GameMain.totalstrokes != -1 ? ", Total strokes: " + GameMain.totalstrokes : "");
		playpanel.getInformation().setText(text);
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
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
