package draw;

import graphTests.WorkWithFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuItemFile extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 725306310132802354L;
	private JFileChooser fileChooser;
	private JButton openButton;
	private JLabel lblFileName;
	private String fileName;
	private static String source;
	int returnVal;
	
	public MenuItemFile(){
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new java.io.File("."));
		openButton = new JButton("Open file");
		lblFileName = new JLabel();
		
		add(openButton);
		add(lblFileName);
		
		openButton.addActionListener(this);
	}
	
	public static String getSeqence(){
		return source;
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == openButton){
			returnVal = fileChooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				lblFileName.setText(fileChooser.getSelectedFile().toString());
				fileName = fileChooser.getSelectedFile().toString();
				try {
					source = WorkWithFile.read(fileName);
					System.out.println(source);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			} else {
				lblFileName.setText("The file open operation was cancelled.");
				fileName = "The file open operation was cancelled.";
			}
		}
	}

}
