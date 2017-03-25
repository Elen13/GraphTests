package sequence;

import java.awt.Color;
import java.awt.Dimension;
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


public class Settings extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static ArrayList<Integer> sq;
	private int cap;
	private int ser;
	private int num;
	private static boolean SET = false;
	
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
	
    public Settings(){
		setLayout(new GridBagLayout());
		
		setButton = new JButton("Ок");
		setButton.setPreferredSize(new Dimension(70,40));
		
		paramLabel = new JLabel("Параметры:");
		add(paramLabel, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		
		
		partLabel = new JLabel("Размер последовательности (%): ");
		add(partLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		percentSpinner = new JSpinner(new SpinnerNumberModel(100, 1, 100, 1));
		tf = ((JSpinner.DefaultEditor) percentSpinner.getEditor()).getTextField();
		tf.setEditable(false);
		add(percentSpinner, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		
		
		
		startSymbLabel = new JLabel("Начать с символа: ");
		add(startSymbLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		startSymbSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		tf = ((JSpinner.DefaultEditor) startSymbSpinner.getEditor()).getTextField();
		tf.setEditable(false);
		add(startSymbSpinner, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		
		
		
		bitDepthLabel = new JLabel("Разрядность: ");
		add(bitDepthLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		bitDepthSpinner = new JSpinner(new SpinnerNumberModel(8, 1, 8, 1));
		tf = ((JSpinner.DefaultEditor) bitDepthSpinner.getEditor()).getTextField();
		tf.setEditable(false);
		add(bitDepthSpinner, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		
		
		seriesLabel = new JLabel("Серии: ");
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
			//decSq = Seqence.binToDec(Seqence.partSq((int)percentSpinner.getValue(), (int)startSymbSpinner.getValue(),
				//						MenuItemFile.getSeqence().length(), MenuItemFile.getSeqence()), (int)bitDepthSpinner.getValue());
			SET = true;
			System.out.println("Set");
		}
	}
	
	public int getCap(){
		return (int)bitDepthSpinner.getValue();
	}
	
	public int getSeries(){
		return (int)seriesSpinner.getValue();
	}
	
	public static boolean getSetOption(){
		return SET;
	}
}
