package graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class DistributionPlot extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int [][] data;
	private String graphTitle;
	private double minY = 0;
	private double maxY;
	private double yInterval;
	private int xOffset = 50;

	
	public DistributionPlot (int [] [] data, int fieldSize, String graphTitle) {
		this.data = data;
		this.maxY = fieldSize;
		this.graphTitle = graphTitle;
		this.yInterval = findOptimalYInterval(fieldSize);
	}
	
	private double findOptimalYInterval(double max) {
		
		int base = 1;
		double [] divisions = new double [] {1,2,2.5,5};
		
		while (true) {
			
			for (int d=0;d<divisions.length;d++) {
				double tester = base * divisions[d];
				if (max / tester <= 10) {
					return tester;
				}
			}
		
			base *=10;
			
		}	
		
	}
	
	public void paint (Graphics g) {
		super.paint(g);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		
		/*System.out.println("Width: " + getWidth() + " Height: " + getHeight());
		System.out.println("Get y = 0: " + getY(0));
		System.out.println("Get x = 0: " + getX(0));*/
		
		double yStart;
		
		if (minY % yInterval == 0) {
			yStart = minY;
		}
		else {
			yStart = yInterval * (((int)minY/yInterval)+1);
		}
		
		for (double i=yStart;i<=maxY;i+=yInterval) {
			String label = ""+i;
			label = label.replaceAll(".0$", ""); // Don't leave trailing .0s where we don't need them.

			g.drawString(label, 2, getY((int)i)+(g.getFontMetrics().getAscent()/2));
			g.drawString(label, getY((int)i)+(g.getFontMetrics().getAscent()/2), getHeight()-5);
		}
		
		// Draw the graph title
		int titleWidth = g.getFontMetrics().stringWidth(graphTitle);
		g.drawString(graphTitle, (xOffset + ((getWidth()-(xOffset+10))/2)) - (titleWidth/2), 30);
		

		// Now draw the field
		g.drawRect(xOffset, 40, getHeight()-80, getHeight()-80);
		
		// Now draw the datasets
		
		if (g instanceof Graphics2D) {
			((Graphics2D)g).setStroke(new BasicStroke(2));
			((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		
		g.setColor(new Color(50, 51, 204, 50));
		int width = 7;//getHeight()-40-getY(1);  
		
		for (int d=0;d<data.length;d++) {
			int x = getX(data[d][0]) - width/2;
			int y = getY(data[d][1]) - width/2;
			g.fillOval(x, y, width, width);
		}
		
		// Now draw the data legend

		if (g instanceof Graphics2D) {
			((Graphics2D)g).setStroke(new BasicStroke(1));
			((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		}
		
		
		
	}
	
	private int getX(int x) {
		return (xOffset + (int)((getHeight()-80)/(maxY-minY))*x);
	}
	
	private int getY(int y) {
		return (getHeight()-40) - (int)(((getHeight()-80)/(maxY-minY))*y);
	}

}
