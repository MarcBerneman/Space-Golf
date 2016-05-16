package gui_componenten;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import tools.Vector;
import spelelementen.Bal;
import spelelementen.Level;
import spelelementen.OutOfBoundsBox;
import spelelementen.Planeet;
import spelelementen.Satelliet;
import spelelementen.Traject;

@SuppressWarnings("serial")
public class LevelPanel extends JPanel implements MouseListener, ActionListener, MouseMotionListener {
	private final Level level;
	private Timer timer = new Timer((int) (GameMain.DeltaT * 1000), this);
	private Vector muis_positie = new Vector(0, 0);
	final static String IMAGE_FOLDER = "images/";
	private Image[] afbeeldingen;
	private Image Background;

	public final JTextArea information = new JTextArea(1, 1);

	public LevelPanel(Level level) {
		this.level = level;
		setPreferredSize(new Dimension(GameMain.BREEDTE, GameMain.HOOGTE));
		setFocusable(true);
		addMouseListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		afbeeldingen = new Image[level.getPlaneten().length];
		setBackground(Color.BLACK);
		for (int i = 0; i < level.getPlaneten().length; i++) {
			afbeeldingen[i] = new ImageIcon(getClass().getResource(IMAGE_FOLDER + i + ".png")).getImage();
		}
		Background = new ImageIcon(getClass().getResource(IMAGE_FOLDER + "space_11.jpg")).getImage();
		timer.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(Background, 0, 0, getWidth(), getHeight(), this);

		for (int i = 0; i < level.getPlaneten().length; i++) {
			int straal = level.getPlaneten()[i].getStraal();
			int x = (int) level.getPlaneten()[i].getPlaats().getX();
			int y = (int) level.getPlaneten()[i].getPlaats().getY();
			g.drawImage(afbeeldingen[i], x - straal, y - straal, 2 * straal, 2 * straal, this);
		}
		Image holeImg = new ImageIcon(getClass().getResource(IMAGE_FOLDER + "hole.png")).getImage();
		int holeX = (int) level.getHole().getPlaats().getX() - level.getHole().getStraal();
		int holeY = (int) level.getHole().getPlaats().getY() - level.getHole().getStraal();
		g.drawImage(holeImg, holeX, holeY, 2 * level.getHole().getStraal(), 2 * level.getHole().getStraal(), this);
		if (!level.getHole().getScored()) {
			Image golfbalImg = new ImageIcon(getClass().getResource(IMAGE_FOLDER + "golfbal.png")).getImage();
			int golfbalX = (int) level.getGolfbal().getPlaats().getX() - level.getGolfbal().getStraal();
			int golfbalY = (int) level.getGolfbal().getPlaats().getY() - level.getGolfbal().getStraal();
			g.drawImage(golfbalImg, golfbalX, golfbalY, 2 * level.getGolfbal().getStraal(),
					2 * level.getGolfbal().getStraal(), this);
		}
		for (Satelliet satelliet : level.getSatellieten()) {
			Image satellietImg = new ImageIcon(getClass().getResource(IMAGE_FOLDER + "maan0.png")).getImage();
			int satellietX = (int) satelliet.getPlaats().getX() - satelliet.getStraal();
			int satellietY = (int) satelliet.getPlaats().getY() - satelliet.getStraal();
			g.drawImage(satellietImg, satellietX, satellietY, 2 * satelliet.getStraal(), 2 * satelliet.getStraal(),
					this);
		}
		if (level.getGolfbal().isStationary()) {
			if (!level.getHole().getScored()) {
				Traject.Aim(g, level.getGolfbal(), level.getPlaneten(), muis_positie, level.getHemellichamen());
			}
		}
		if (level.getGolfbal().outOfBounds(GameMain.BREEDTE, GameMain.HOOGTE)) {
			OutOfBoundsBox.drawBox(g, level.getGolfbal(), GameMain.BREEDTE, GameMain.HOOGTE);
		}

	}

	public Level getLevel() {
		return level;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		level.turn();
		repaint();
		information.setText(
				"Strokes: " + level.getNr_strokes() + ", Par: " + (level.getPar() > 0 ? level.getPar() : "N/A"));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Bal b = level.getGolfbal();
		if (!level.getHole().getScored()) {
			if (b.isStationary()) {
				b.setSnelheid(b.InitialSpeed(muis_positie));
				b.setStationary(false);
				level.incrementStrokes();
			}
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
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
