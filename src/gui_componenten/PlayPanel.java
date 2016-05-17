package gui_componenten;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import tools.LevelQueue;

public class PlayPanel extends JPanel implements ActionListener, KeyListener {
	private LevelPanel levelpanel;
	private final JButton Reset = new JButton("Reset");
	private final JButton Quit = new JButton("Quit");
	private GameMain window;
	private final LevelQueue queue;

	static boolean pause = false;

	public PlayPanel(GameMain window, int level) {
		queue = new LevelQueue(level);
		levelpanel = new LevelPanel(queue.NextLevel());

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Container buttons = new Container();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		this.window = window;
		levelpanel.information.setFocusable(false);
		levelpanel.information.setMaximumSize(new Dimension(130, 18));
		buttons.add(Reset);
		levelpanel.Next.setVisible(false);
		levelpanel.Next.setBackground(Color.GREEN);
		buttons.add(levelpanel.Next);
		buttons.add(Quit);
		buttons.add(levelpanel.information);
		add(buttons);
		levelpanel.information.setOpaque(false);
		levelpanel.information.setBackground(new Color(0, 0, 0, 0));
		add(levelpanel);

		Reset.addActionListener(this);
		Quit.addActionListener(this);
		levelpanel.Next.addActionListener(this);
		setFocusable(true);
		addKeyListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Reset) {
			if(!levelpanel.getLevel().getHole().getScored()) {
				levelpanel.getLevel().ResetBall();
				levelpanel.getLevel().getHole().setScored(false);
			}
		} else if (e.getSource() == Quit) {
			window.switchPanel();
		} else if (e.getSource() == levelpanel.Next) {
			window.switchPanel(new PlayPanel(window,queue.getCurrentLevel()));
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("key pressed");
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_P) {
			levelpanel.setLevel(queue.NextLevel());
			pause = true;
			System.out.println("paused");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public LevelQueue getQueue() {
		return queue;
	}
}
