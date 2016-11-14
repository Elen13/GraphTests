package draw;

import graphTests.Seqence;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class MainMenuItemParameters extends JPanel implements ActionListener{

	private static final long serialVersionUID = 884904764504929063L;
	private static ArrayList<Integer> decSq = new ArrayList<Integer>();;
	private JLabel paramLabel;
	private JTextField tf;
	private JButton setButton;
	
	private JLabel partLabel;
    private JSpinner percentSpinner;
    
    private JLabel startSymbLabel;
    private JSpinner startSymbSpinner;
    
    private JLabel bitDepthLabel;
    private static JSpinner bitDepthSpinner;
    
    private JLabel seriesLabel;
    private static JSpinner seriesSpinner;
    
	
	public MainMenuItemParameters(){
		setLayout(new GridBagLayout());
		setBackground(Color.GREEN);
		
		setButton = new JButton("Set");
		paramLabel = new JLabel("Parameters:");
		add(paramLabel, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		
		
		partLabel = new JLabel("Chose a part(%) of seqence: ");
		add(partLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		percentSpinner = new JSpinner(new SpinnerNumberModel(100, 1, 100, 1));
		tf = ((JSpinner.DefaultEditor) percentSpinner.getEditor()).getTextField();
		tf.setEditable(false);
		add(percentSpinner, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		
		
		
		startSymbLabel = new JLabel("Chose a begin symbol: ");
		add(startSymbLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		startSymbSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		tf = ((JSpinner.DefaultEditor) startSymbSpinner.getEditor()).getTextField();
		tf.setEditable(false);
		add(startSymbSpinner, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		
		
		
		bitDepthLabel = new JLabel("Chose a bit depth: ");
		add(bitDepthLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		bitDepthSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 8, 1));
		tf = ((JSpinner.DefaultEditor) bitDepthSpinner.getEditor()).getTextField();
		tf.setEditable(false);
		add(bitDepthSpinner, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		
		
		seriesLabel = new JLabel("Chose series for Test3: ");
		add(seriesLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		seriesSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 4, 1));
		tf = ((JSpinner.DefaultEditor) seriesSpinner.getEditor()).getTextField();
		tf.setEditable(false);
		add(seriesSpinner, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		
		
		
		add(setButton, new GridBagConstraints(0, 8, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		setButton.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == setButton){
			decSq = Seqence.binToDec(Seqence.partSq((int)percentSpinner.getValue(), (int)startSymbSpinner.getValue(),
										MenuItemFile.getSeqence().length(), MenuItemFile.getSeqence()), (int)bitDepthSpinner.getValue());
			System.out.println("Введенное значение: " + decSq);
		}
	}
	
	public static ArrayList<Integer> getSeqence(){
		return decSq;
	}
	
	public static int getCap(){
		return (int)bitDepthSpinner.getValue();
	}
	
	public static int getSeries(){
		return (int)seriesSpinner.getValue();
	}

}
