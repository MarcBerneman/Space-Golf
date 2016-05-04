package spelelementen;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public final static double G = 4500; // Gravitatieconstante
	public final static double µ = 0.45; // Wrijvingscoëfficient
	public final static double COR = 0.65; // Coëfficient of Restitution
	public final static double DeltaT = 0.01;
	public final static double MOUSE_SPEED_COEFFICIENT = 1.25;
	public final static double SPEED_THRESHHOLD = 1.5;

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
