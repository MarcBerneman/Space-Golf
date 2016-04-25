package spelelementen;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public MainFrame() {
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new MainPanel(new Level()));
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainFrame();

	}

}
