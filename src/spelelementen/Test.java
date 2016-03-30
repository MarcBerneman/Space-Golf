package spelelementen;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Test extends JFrame{
	private JFrame frame;
	public Test() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.add(new TestPanel());
	}

	public static void main(String[] args) {
		new Test();
	}

}
