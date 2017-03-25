package help;


import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


/**
 * The Class HelpDialog is the root window of the help system.
 */
public class HelpDialog extends JDialog implements TreeSelectionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The tree. */
	private JTree tree;
	
	/** The current page. */
	private HelpPageDisplay currentPage = null;
	
	/** The main split. */
	private JSplitPane mainSplit;
	
	
	/**
	 * Instantiates a new help dialog.
	 * 
	 * @param parent the parent
	 * @param startingLocation the starting location
	 */
	public HelpDialog (JFrame parent, File startingLocation) {
		super(parent,"Справка");
		
		HelpIndexRoot root = new HelpIndexRoot(startingLocation);
		
		mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		setContentPane(mainSplit);
		
		tree = new JTree(new DefaultTreeModel(root));
		
		JSplitPane leftSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		leftSplit.setTopComponent(new JScrollPane(tree));
		leftSplit.setBottomComponent(new HelpSearchPanel(root,this));
		
		mainSplit.setLeftComponent(leftSplit);
		currentPage = new HelpPageDisplay(null);
		mainSplit.setRightComponent(currentPage);

		tree.addTreeSelectionListener(this);
		
		
		setSize(800,500);
		setLocationRelativeTo(parent);
		setVisible(true);
		
		leftSplit.setDividerLocation(0.7);
		mainSplit.setDividerLocation(0.3);
		findStartingPage();
	}

	/**
	 * Find starting page.
	 */
	private void findStartingPage () {
		DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)tree.getModel().getRoot();

		DisplayPage((HelpPage) currentNode.getFirstLeaf());
	}
	
	/**
	 * Display page.
	 * 
	 * @param page the page
	 */
	public void DisplayPage(HelpPage page) {
		if (currentPage != null) {
			int d = mainSplit.getDividerLocation();
			mainSplit.remove(currentPage);
			currentPage = new HelpPageDisplay(page);
			mainSplit.setRightComponent(currentPage);
			mainSplit.setDividerLocation(d);
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
	 */
	public void valueChanged(TreeSelectionEvent tse) {
		
		if (tse.getNewLeadSelectionPath() == null) return;
		
		Object o = tse.getNewLeadSelectionPath().getLastPathComponent();
		if (o instanceof HelpPage && ((HelpPage)o).isLeaf()) {
			DisplayPage((HelpPage)o);
		}
	}
	
}
