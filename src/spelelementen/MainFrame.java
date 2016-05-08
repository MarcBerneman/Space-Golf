package spelelementen;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public final static double G = 5000; // Gravitatieconstante
	public final static double µ = 0.45; // Wrijvingscoëfficient
	public final static double COR = 0.55; // Coëfficient of Restitution
	public final static double DeltaT = 0.01;
	public final static double MOUSE_SPEED_COEFFICIENT = 2.50; 
	public final static double MINIMAL_AVERAGE_MOVEMENT = 1.5;

	public MainFrame() {
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new MainPanel(new Level()));
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainFrame();

	}

}
