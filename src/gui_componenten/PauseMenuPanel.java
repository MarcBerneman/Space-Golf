package gui_componenten;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import tools.RandomLevel;

public class PauseMenuPanel extends JPanel implements ActionListener {

	private final JButton Resume = new JButton("Resume");
	private final JButton Reset = new JButton("Reset");
	private final JButton Quit = new JButton("Quit");
	private GameMain window;
	
	public PauseMenuPanel(GameMain window) {
		this.window = window;
		Resume.addActionListener(this);
		Reset.addActionListener(this);
		Quit.addActionListener(this);
		add(Resume);
		add(Reset);
		add(Quit);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Resume) {
			//PlayPanel p;
			//p = new PlayPanel(window, new LevelPanel(levelbuilder.Build()));
			window.switchPanel(p);//switchPanel moet zonder remove int begin, bijzetten bij elke call
		} else if (e.getSource() == Reset) {

		} else if (e.getSource() == Quit) {
			//PlayPanel p = new PlayPanel(window, new LevelPanel(RandomLevel.GenerateRandomLevel()));
			//window.switchPanel(p);
		}
		
	}

}
