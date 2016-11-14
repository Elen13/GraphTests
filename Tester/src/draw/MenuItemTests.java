package draw;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuItemTests extends JPanel implements ActionListener{
	private static final long serialVersionUID = 3553374497693587087L;
	private JButton runButton;
	private JLabel helloLabel;
	private MakeTests test = new MakeTests(); 
	private JCheckBox test1checkBox;
	private JCheckBox test2checkBox;
	private JCheckBox test3checkBox;
	private JCheckBox test4checkBox;
	private JCheckBox test5checkBox;
	private JCheckBox test6checkBox;
	private JCheckBox test7checkBox;
	private JCheckBox test8checkBox;
	
	
	public MenuItemTests(){
		setLayout(new GridBagLayout());
		setBackground(Color.GREEN);
		
		
		runButton = new JButton("Run");
		helloLabel = new JLabel("Select the tets:");
		test1checkBox = new JCheckBox("<html>Test1.<br> Histogram distribution of elements.</html>");
		test2checkBox = new JCheckBox("<html>Test2.<br> Distribution of the plane.</html>");
		test3checkBox = new JCheckBox("<html>Test3.<br>Checking series.</html>");
		test4checkBox = new JCheckBox("<html>Test4.<br>Check for monotony.</html>");
		test5checkBox = new JCheckBox("<html>Test5.<br>Bit autocorrelation function.</html>");
		test6checkBox = new JCheckBox("<html>Test6.<br>Symbol autocorrelation function.</html>");
		test7checkBox = new JCheckBox("<html>Test7.<br>Spectral test.</html>");
		test8checkBox = new JCheckBox("<html>Test8.<br>Profile linear complexity.</html>");
		
		
		add(helloLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		add(test1checkBox, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		add(test2checkBox, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		add(test3checkBox, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		add(test4checkBox, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		add(test5checkBox, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		add(test6checkBox, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		add(test7checkBox, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		add(test8checkBox, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		
		add(runButton, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		runButton.addActionListener(this);
	}
	
	
	

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == runButton){
			if(test1checkBox.isSelected()){
				test.show(1);
			}
			
			if(test2checkBox.isSelected()){
				test.show(2);
			}
			
			if(test3checkBox.isSelected()){
				test.show(3);
			}
			
			if(test4checkBox.isSelected()){
				test.show(4);
			}
			
			if(test5checkBox.isSelected()){
				test.show(5);
			}
			
			if(test6checkBox.isSelected()){
				test.show(6);
			}
			
			if(test7checkBox.isSelected()){
				test.show(7);
			}
			
			if(test8checkBox.isSelected()){
				test.show(8);
			}
		}
	}
}
