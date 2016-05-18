package gui_componenten;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WinPanel extends JPanel implements ActionListener{
	private JTextField wininformation;
	private JButton menu = new JButton("Menu");
	private GameMain window;
	
	public WinPanel(GameMain window) {
		this.window = window;
		wininformation = new JTextField("Total number of strokes: " + GameMain.totalstrokes);
		wininformation.setEditable(false);
		menu.addActionListener(this);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(wininformation);
		add(menu);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == menu)
			window.switchPanel();
	}

}
