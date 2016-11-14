package draw;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class SavePicture {

	    JFrame frame;
	    DrawPicture panel;
	 
	    public SavePicture(){
	       frame = new JFrame("Frame");
	        panel = new DrawPicture();
	 
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(500, 500);
	        
	        panel.setSize(frame.getWidth(), frame.getHeight());
	        
	        frame.add(panel);
	 
	        frame.setVisible(true);
	 
	        BufferedImage image =(BufferedImage) 
	        panel.createImage(panel.getWidth(), panel.getHeight());
	        Graphics2D g2 = image.createGraphics();
	        panel.paint(g2);
	        g2.dispose();
	        try {
	            ImageIO.write(image, "png", new File("MyImg.png"));
	        }
	        catch(IOException io) { io.printStackTrace(); }
	    }
}
