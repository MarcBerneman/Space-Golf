package gui_componenten;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import spelelementen.RandomLevel;

public class MenuPanel extends JPanel implements ActionListener{
	private final JButton Play = new JButton("Play");
	private final JButton PlayLevel = new JButton("Level");
	private final JButton PlayRandom = new JButton("Random Level");
	private GameMain window;
	
	public MenuPanel(GameMain window) {
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
		if(e.getSource() == Play) {
			
		} else if(e.getSource() == PlayLevel) {
			
		} else if(e.getSource() == PlayRandom) {
			//LevelPanel p = new LevelPanel(RandomLevel.GenerateRandomLevel());
			PlayPanel p = new PlayPanel(window,new LevelPanel(RandomLevel.GenerateRandomLevel()));
			window.switchPanel(p);
		}
	}
	
	
	
	
}
