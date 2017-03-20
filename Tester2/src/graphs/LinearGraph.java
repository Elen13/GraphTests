package graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class LinearGraph extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int [] data;
	private String graphTitle;
	private double minY;
	private double maxY;
	private double yInterval;
	
	private static final Color [] COLOURS = new Color[] {new Color(220,0,0), new Color(0,0,220), new Color(0,220,0), Color.DARK_GRAY, Color.MAGENTA, Color.ORANGE,Color.YELLOW,Color.CYAN,Color.PINK,Color.LIGHT_GRAY};
	
	public LinearGraph (int [] data, double minY, double maxY, String graphTitle) {
		this.data = data;
		this.minY = minY;
		this.maxY = maxY;
		this.graphTitle = graphTitle;
		this.yInterval = findOptimalYInterval(maxY);
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
	
	public Dimension getPreferredSize () {
		return new Dimension(800,600);
	}

	public Dimension getMinimumSize () {
		return new Dimension(100,200);
	}
	
	public void paint (Graphics g) {
		super.paint(g);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		
		double yStart;
		
		if (minY % yInterval == 0) {
			yStart = minY;
		}
		else {
			yStart = yInterval * (((int)minY/yInterval)+1);
		}
		
		int xOffset = 0;
		
		for (double i=yStart;i<=maxY;i+=yInterval) {
			String label = ""+i;
			label = label.replaceAll(".0$", ""); // Don't leave trailing .0s where we don't need them.
			int width = g.getFontMetrics().stringWidth(label);
			if (width > xOffset) {
				xOffset = width;
			}
			
			g.drawString(label, 2, getY(i)+(g.getFontMetrics().getAscent()/2));			
		}
	
		// Give the x axis a bit of breathing space
		xOffset += 5;
		
		// Draw the graph title
		int titleWidth = g.getFontMetrics().stringWidth(graphTitle);
		g.drawString(graphTitle, (xOffset + ((getWidth()-(xOffset+10))/2)) - (titleWidth/2), 30);
		
		
		// Now draw the axes
		g.drawLine(xOffset, getHeight()-40, getWidth()-10,getHeight()-40);
		g.drawLine(xOffset, getHeight()-40, xOffset, 40);
		
		
		// Now draw the data points
		int baseWidth = (getWidth()-(xOffset+10))/data.length;
		if (baseWidth<1) baseWidth=1;
		
//		System.out.println("Base Width is "+baseWidth);
		
		// First draw faint boxes over alternating bases so you can see which is which
		
		// Let's find the longest label, and then work out how often we can draw labels
		
		for (int i=0;i<data.length;i++) {
			if (i%2 != 0) {
				g.setColor(new Color(230, 230, 230));
				g.fillRect(xOffset+(baseWidth*i), 40, baseWidth, getHeight()-80);
			}
			g.setColor(Color.BLACK);
		}
		
		// Now draw horizontal lines across from the y axis

		g.setColor(new Color(180,180,180));
		for (double i=yStart+yInterval;i<=maxY;i+=yInterval) {
			g.drawLine(xOffset, getY(i), getWidth()-10, getY(i));
		}
		g.setColor(Color.BLACK);
		
		// Now draw the datasets
		
		if (g instanceof Graphics2D) {
			((Graphics2D)g).setStroke(new BasicStroke(2));
			((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		
		int [] xValues = new int [data.length];
    	int [] yValues = new int [data.length];
    	int thisY = 0;
    	
		for(int i = 0; i < data.length; i++){
			thisY = getY(data[i]);
    		xValues[i] = xOffset+(baseWidth*i);
    		yValues[i] = thisY;    		
    	}
		
    	g.drawPolyline( xValues, yValues, data.length);
		
		// Now draw the data legend

		if (g instanceof Graphics2D) {
			((Graphics2D)g).setStroke(new BasicStroke(1));
			((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		}

		for(int i = 0; i < data.length; i++){
			thisY = getY(data[i]);
    		xValues[i] = xOffset+(baseWidth*i);
    		yValues[i] = thisY;    		
    	}
		
		g.setColor(Color.RED);
		g.drawLine(xOffset, getHeight()-40, xOffset+(baseWidth*data.length), getY(data.length/2));
		g.setColor(Color.BLACK);
		
		// First we need to find the widest label
		/*int widestLabel = 0;
		for (int t=0;t<xTitles.length;t++) {
			int width = g.getFontMetrics().stringWidth(xTitles[t]);
			if (width > widestLabel) widestLabel = width;
		}
		
		// Add 3px either side for a bit of space;
		widestLabel += 6;
		
		// First draw a box to put the legend in
		g.setColor(Color.WHITE);
		g.fillRect((getWidth()-10)-widestLabel, 40, widestLabel, 3+(20*xTitles.length));
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect((getWidth()-10)-widestLabel, 40, widestLabel, 3+(20*xTitles.length));

		// Now draw the actual labels
		for (int t=0;t<xTitles.length;t++) {
			g.setColor(COLOURS[t]);
			g.drawString(xTitles[t], ((getWidth()-10)-widestLabel)+3, 40+(20*(t+1)));
		}*/
		

		
		
	}

	private int getY(double y) {
		return (getHeight()-40) - (int)(((getHeight()-80)/(maxY-minY))*y);
	}
	
}
