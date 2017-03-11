package dialogs;

import javax.swing.*;

import mainWindow.TesterApplication;

import java.awt.*;
import java.awt.event.*;

/**
 * Shows the generic about dialog giving details of the current version
 * and copyright assignments.  This is just a thin shell around the 
 * SeqMonkTitlePanel which actually holds the relevant information and
 * which is also used on the welcome screen.
 */
public class AboutDialog extends JDialog {
	private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new about dialog.
     * 
     * @param a The SeqMonk application.
     */
    public AboutDialog(TesterApplication a) {
    	super(a);
        setTitle("О Графических тестах...");  
        Container cont = getContentPane();
        cont.setLayout(new BorderLayout());
        
        add(new TesterTitlePanel(),BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        
        JButton closeButton = new JButton("Закрыть");
        getRootPane().setDefaultButton(closeButton);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
                dispose();
            }
        });
        buttonPanel.add(closeButton);
        
        cont.add(buttonPanel,BorderLayout.SOUTH);
        
        setSize(650,230);
        setLocationRelativeTo(a);
        setResizable(false);
        setVisible(true);
    }
    
}