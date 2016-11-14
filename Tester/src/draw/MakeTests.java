package draw;

import javax.swing.JFrame;

public class MakeTests {

	private JFrame newFrame;
	private DrawPicture picture;
	
	public void show(int number){
		newFrame = new JFrame("Test"+number);
		newFrame.setSize(800, 600);
		
		picture = new DrawPicture();
		picture.setParam(number, MainMenuItemParameters.getSeqence(), MainMenuItemParameters.getCap(), MainMenuItemParameters.getSeries());
		
		newFrame.add(picture);
		
		newFrame.setVisible(true);
	}
}
