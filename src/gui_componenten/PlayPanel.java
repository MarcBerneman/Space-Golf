package gui_componenten;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PlayPanel extends JPanel implements ActionListener{
	private LevelPanel levelpanel;
	private final JButton Reset = new JButton("Reset");
	private final JButton Quit = new JButton("Quit");
	private GameMain window;
	
	public PlayPanel(GameMain window, LevelPanel levelpanel) {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		Container buttons = new Container();
		buttons.setLayout(new BoxLayout(buttons,BoxLayout.X_AXIS));
		this.levelpanel = levelpanel;
		this.window = window;
		levelpanel.information.setFocusable(false);
		levelpanel.information.setMaximumSize(new Dimension(130,18));
		buttons.add(Reset);
		buttons.add(Quit);
		buttons.add(levelpanel.information);
		add(buttons);
		add(levelpanel);
		Reset.addActionListener(this);
		Quit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Reset)
			levelpanel.getLevel().ResetBall();
		else if(e.getSource() == Quit)
			window.switchPanel();
	}
	
	
}
