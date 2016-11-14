package draw;

import graphTests.Distribution;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;


public class DrawPicture extends JPanel{
	private static final long serialVersionUID = 5061568512500793878L;
	private ArrayList<Integer> sq;
	private int cap;
	private int ser;
	private int num;
	
	public void setParam(int number, ArrayList<Integer> seq, int bitDepth, int series){
		sq = seq;
		cap = bitDepth;
		ser = series;
		num = number;
	}

	public void paintComponent(Graphics g){
		super.paintComponents(g);
		
		g.setColor(Color.white);
		g.fillRect(0, 0, getSize().width, getSize().height);
		title(g);
		this.draw(g); 
	}
	
	public void title(Graphics g){
		String s = "Test"+num;
		Font font = new Font("TimesRoman", Font.BOLD, 16);
		FontMetrics metrics = g.getFontMetrics(font);
		int x = (getSize().width - metrics.stringWidth(s)) / 2;
		int y = metrics.getHeight();//smetrics.getAscent();//((getSize().height - metrics.getHeight()) / 2) + metrics.getAscent();
		System.out.println("x: "+x+" Y: "+y);
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString(s, x, y);		
	}
	
	
	public void draw(Graphics g){
		switch (num) {
	    case 1:  {
	    	g.setColor(Color.green);
			g.drawRect(50, 50, 13, 13);
	    	break;
	    }
	             
	    case 2:  {
			int [][] pointArr = Distribution.test2(sq);
			int widthRec = 512;
			int x = ( getSize().width - widthRec ) / 2;
			int y = ( getSize().height - widthRec) / 2;
			int width = 4;
			int step = 1;
			int xO = 0, yO = 0;

			g.setColor(Color.black);
			g.drawRect(x, y, widthRec, widthRec);
			
			for(int i = 0; i < sq.size()-1; i++){ 
				xO = x + step * pointArr[i][0] - width/2;  
				yO = y + widthRec - step * pointArr[i][1] - width/2; 
				g.fillOval(xO, yO, width, width);
			}
	    	break;
	    }
	             
	   /* case 3:  monthString = "Март";
	             break;
	    case 4:  monthString = "Апрель";
	             break;
	    case 5:  monthString = "Май";
	             break;
	    case 6:  monthString = "Июнь";
	             break;
	    case 7:  monthString = "Июль";
	             break;
	    case 8:  monthString = "Август";
	             break;
	    default: monthString = "Не знаем такого";
	             break;*/
		}
		
	}
	/*public DrawPicture(){
		setSize(300,300);
		setTitle("Tester");
		/*MyPanel panel = new MyPanel();
		Container pane = getContentPane();
		pane.add(panel);
	}*/
}


/*class MyPanel extends JPanel{
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawString("Text", 55, 55);
		Font f=new Font ("SansSerif", Font.ITALIC,20);
		g2.setFont(f);
		g2.drawString("new Text", 55, 77);
		
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
		g.fillArc(x1, y1, dx, dy, 30, 250);
	}
}*/