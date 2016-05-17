package gui_componenten;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import tools.LevelBuilder;
import tools.LevelQueue;
import tools.RandomLevel;

public class MainMenuPanel extends JPanel implements ActionListener {
	private final JButton Play = new JButton("Play");
	private final JButton PlayLevel = new JButton("Level");
	private final JButton PlayRandom = new JButton("Random Level");
	private GameMain window;

	public MainMenuPanel(GameMain window) {
		this.window = window;
		Play.addActionListener(this);
		PlayLevel.addActionListener(this);
		PlayRandom.addActionListener(this);
		add(Play);
		add(PlayLevel);
		add(PlayRandom);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Play) {
			PlayPanel p = new PlayPanel(window,LevelQueue.PLAY);
			window.switchPanel(p);
		} else if (e.getSource() == PlayLevel) {
			PlayPanel p = new PlayPanel(window,3);
			window.switchPanel(p);
		} else if (e.getSource() == PlayRandom) {
			PlayPanel p = new PlayPanel(window,LevelQueue.RANDOMLEVEL);
			window.switchPanel(p);
		}
	}

}
