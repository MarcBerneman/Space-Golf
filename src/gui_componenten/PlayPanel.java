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

import spelelementen.Level;
import tools.LevelQueue;

public class PlayPanel extends JPanel implements ActionListener {
	private LevelPanel levelpanel;
	private final JButton Reset = new JButton("Reset");
	private final JButton Quit = new JButton("Quit");
	private GameMain window;
	private final LevelQueue queue;
	
	public PlayPanel(GameMain window, int level) {
		queue = new LevelQueue(level);
		levelpanel = new LevelPanel(queue.NextLevel());

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Container buttons = new Container();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		this.window = window;
		levelpanel.information.setEditable(false);
		buttons.add(Reset);
		levelpanel.Next.setVisible(false);
		levelpanel.Next.setBackground(Color.GREEN);
		buttons.add(levelpanel.Next);
		buttons.add(Quit);
		buttons.add(levelpanel.information);
		add(buttons);
		//levelpanel.information.setOpaque(false);
		//levelpanel.information.setBackground(new Color(0, 0, 0, 0));
		add(levelpanel);

		Reset.addActionListener(this);
		Quit.addActionListener(this);
		levelpanel.Next.addActionListener(this);
		setFocusable(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Reset) {
			Level level = levelpanel.getLevel();
			if(!level.getHole().getScored() && !level.getGolfbal().getPlaats().equals(level.getStartPos())) {
				level.ResetBall();
				if(GameMain.totalstrokes != -1) GameMain.totalstrokes++;
			}
		} else if (e.getSource() == Quit) {
			window.switchPanel();
		} else if (e.getSource() == levelpanel.Next) {
			if(queue.getCurrentLevel() < 7)
				window.switchPanel(new PlayPanel(window,queue.getCurrentLevel()));
			else {
				window.switchPanel(new WinPanel(window));
				GameMain.totalstrokes = -1;
			}
		}
	}


	public LevelQueue getQueue() {
		return queue;
	}
}
