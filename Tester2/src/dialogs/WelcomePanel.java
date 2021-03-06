package dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public WelcomePanel () {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.weightx=0.5;
		gbc.weighty=0.99;
		
		add(new JPanel(),gbc);
		gbc.gridy++;
		gbc.weighty=0.01;
		
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.NONE;
		
		add(new TesterTitlePanel(),gbc);
		
		gbc.gridy++;
		gbc.weighty=0.5;
		
		add(new JLabel("<html>Файл > Открыть <br> выбор файла с последовательностью для тестирования</html>"),gbc);

		gbc.gridy++;
		gbc.weighty=0.99;
		add(new JPanel(),gbc);
		
	}
}