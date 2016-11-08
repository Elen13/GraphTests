package draw;

import javax.swing.JFrame;

public class MainMenu {

	JFrame frame = new JFrame("Tester");

	public MainMenu() {
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		MenuItems menu = new MenuItems();
		menu.setMenuItems(frame);
		
		frame.setVisible(true);
	}
}
