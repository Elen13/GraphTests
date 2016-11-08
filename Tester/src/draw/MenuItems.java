package draw;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MenuItems {
	
	private JPanel File = new JPanel();
	private JPanel Parameters = new JPanel();
	private JPanel Tests = new JPanel();
	
	//private JLabel lFile = new JLabel("File");
	//private JLabel lParam = new JLabel("Parameters");
	private JLabel lTests = new JLabel("Tests");
	
	private MenuItemFile openFile = new MenuItemFile();
	private MainMenuItemParameters paramItem = new MainMenuItemParameters();
	
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
	
	public void setMenuItems(JFrame frame){
		File.add(openFile);
		//Parameters.add(lParam);
		Tests.add(lTests);
		
		Parameters.add(paramItem);
		
		tabbedPane.add("File", File);
		tabbedPane.add("Param", Parameters);
		tabbedPane.add("Tests", Tests);
		
		frame.add(tabbedPane);
	}

}
