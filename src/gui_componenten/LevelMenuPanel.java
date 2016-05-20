package gui_componenten;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import tools.LevelQueue;

public class LevelMenuPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 2551818852864376058L;
	private final JButton[] levels = new JButton[6];
	private final JButton menu;
	private GameMain window;
	
	public LevelMenuPanel(GameMain window) {
		this.window = window;
		for(int i = 0 ;  i < 6 ; i++) {
			levels[i] = new JButton("Level " + (i + 1));
			add(levels[i]);
			levels[i].addActionListener(this);
		}
		menu = new JButton("Main Menu");
		add(menu);
		menu.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//gaat terug naar het menu of kiest een level
		if(e.getSource() == menu)
			window.switchPanel();
		for(int i = 0 ; i < 6 ; i++)
			if(e.getSource() == levels[i]) {
				PlayPanel p = new PlayPanel(window, new LevelQueue(i + 1));
				window.switchPanel(p);
				break;
			}
	}
}
