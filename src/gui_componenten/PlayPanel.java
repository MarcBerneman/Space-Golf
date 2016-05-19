package gui_componenten;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import spelelementen.Level;
import tools.LevelQueue;

public class PlayPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 2372177018884851446L;
	private LevelPanel levelpanel;
	private final JButton Reset = new JButton("Reset");
	private final JButton Quit = new JButton("Quit");
	private GameMain window;
	private final LevelQueue queue;

	public PlayPanel(GameMain window, LevelQueue queue) {
		this.queue = queue;
		levelpanel = new LevelPanel(queue.NextLevel());
		this.window = window;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel buttons = new JPanel();

		Reset.addActionListener(this);
		buttons.add(Reset);

		levelpanel.Next.setBackground(Color.GREEN);
		levelpanel.Next.setVisible(false);
		levelpanel.Next.addActionListener(this);
		buttons.add(levelpanel.Next);

		Quit.addActionListener(this);
		buttons.add(Quit);

		levelpanel.updateInformation();
		buttons.add(levelpanel.information);

		add(buttons);
		add(levelpanel);

		setFocusable(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Reset) {
			Level level = levelpanel.getLevel();
			if (!level.getHole().getScored() && !level.getGolfbal().getPlaats().equals(level.getStartPos())) {
				level.ResetBall();
				if (GameMain.totalstrokes != -1)
					GameMain.totalstrokes++;
			}
		} else if (e.getSource() == Quit) {
			window.switchPanel();
			GameMain.totalstrokes = -1;
		} else if (e.getSource() == levelpanel.Next) {
			if (queue.NextIsWinPanel()) {
				window.switchPanel(new WinPanel(window));
				GameMain.totalstrokes = -1;
			} else {
				window.switchPanel(new PlayPanel(window, queue));
			}
		}
	}

	public LevelQueue getQueue() {
		return queue;
	}
}
