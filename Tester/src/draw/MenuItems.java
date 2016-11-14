package draw;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MenuItems {
	
	private JPanel File = new JPanel();
	private JPanel Parameters = new JPanel();
	private JPanel Tests = new JPanel();
	
	private MenuItemFile openFile = new MenuItemFile();
	private MainMenuItemParameters paramItem = new MainMenuItemParameters();
	private MenuItemTests tests = new MenuItemTests();
	
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
	
	public void setMenuItems(JFrame frame){
		File.add(openFile);
		Parameters.add(paramItem);
		Tests.add(tests);
		
		tabbedPane.add("File", File);
		tabbedPane.add("Parameters", Parameters);
		tabbedPane.add("Tests", Tests);
		
		frame.add(tabbedPane);
	}

}
