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

public class PlayPanel extends JPanel implements ActionListener, KeyListener {
	private LevelPanel levelpanel;
	private final JButton Reset = new JButton("Reset");
	private final JButton Quit = new JButton("Quit");
	private GameMain window;
	
	static boolean pause = false;

	public PlayPanel(GameMain window, LevelPanel levelpanel) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Container buttons = new Container();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		this.levelpanel = levelpanel;
		this.window = window;
		levelpanel.information.setFocusable(false);
		levelpanel.information.setMaximumSize(new Dimension(130, 18));
		buttons.add(Reset);
		buttons.add(Quit);
		buttons.add(levelpanel.information);
		add(buttons);
		levelpanel.information.setOpaque(false);
		levelpanel.information.setBackground(new Color(0, 0, 0, 0));
		add(levelpanel);
		
		Reset.addActionListener(this);
		Quit.addActionListener(this);
		setFocusable(true);
		addKeyListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Reset) {
			levelpanel.getLevel().ResetBall();
			levelpanel.getLevel().getHole().setScored(false);
		} else if (e.getSource() == Quit) {
			window.switchPanel();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("key pressed");
		int key = e.getKeyCode();
		if(key==KeyEvent.VK_P){
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
}
