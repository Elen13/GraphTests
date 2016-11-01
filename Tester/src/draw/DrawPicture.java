package draw;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.SystemColor;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class DrawPicture extends JFrame{

	public DrawPicture(){
		setSize(300,300);
		setTitle("Tester");
		/*MyPanel panel = new MyPanel();
		Container pane = getContentPane();
		pane.add(panel);*/
	}
}

class MyPanel extends JPanel{
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;
		/*g.drawString("Text", 55, 55);
		Font f=new Font ("SansSerif", Font.ITALIC,20);
		g2.setFont(f);
		g2.drawString("new Text", 55, 77);*/
		
		double x1 = 11, y1 = 11, dx = 44, dy = 66;
		
		Rectangle2D rect1 = new Rectangle2D.Double(x1, y1, dx, dy);
		g2.draw(rect1);
		
		x1+=dx+15;
		Rectangle2D rect2 = new Rectangle2D.Double(x1, y1, dx, dy);
		g2.setPaint(Color.red);
		g2.fill(rect2);
		/*int x1 = 22, y1 = 22, dx = 44, dy = 66;
		setForeground(Color.red);
		g.drawLine(x1, y1, x1+dx, y1+dy);
		g.setColor(Color.green);
		g.drawRect(x1, y1, dx, dy);
		int rx = 15, ry = 35;
		x1+= dx+15;
		g.setColor(SystemColor.activeCaption);
		g.drawRoundRect(x1, y1, dx, dy, rx, ry);
		x1+= dx+15;
		g.clearRect(x1, y1, dx, dy);
		x1+= dx+15;
		g.fillRect(x1, y1, dx, dy);
		x1+= dx+15;
		g.fillRoundRect(x1, y1, dx, dy, rx, ry);
		x1+= dx+15;
		g.draw3DRect(x1, y1, dx, dy, true);
		x1+= dx+15;
		g.fill3DRect(x1, y1, dx, dy, true);
		x1 = 22;
		y1 = 100;
		g.drawOval(x1, y1, dx, dy);
		x1+= dx+15;
		g.fillOval(x1, y1, dx, dy);
		x1+= dx+15;
		g.drawArc(x1, y1, dx, dy, 30, 300);
		x1+= dx+15;
		g.fillArc(x1, y1, dx, dy, 30, 250);*/
	}
}