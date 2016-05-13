
package spelelementen;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public final static double G = 2500; // Gravitatieconstante
	public final static double WR = 0.45; // Wrijvingscoefficient
	public final static double COR = 0.55; // Coefficient of Restitution
	public final static double DeltaT = 0.01;
	public final static double MOUSE_SPEED_COEFFICIENT = 2.5; 
	public final static double MINIMAL_AVERAGE_MOVEMENT = 3.0;
	public final static double MAX_POWER = 400;
	public final static double AIM_TIME = 1;
	public final static int BREEDTE= 1300, HOOGTE= 730;

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new MainPanel(RandomLevel.GenerateRandomLevel()));
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainFrame();

	}

}
