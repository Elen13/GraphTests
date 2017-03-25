package graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Histogram extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String [] xCategories;
	private ArrayList<?> data;
	private String graphTitle;
	private double minY;
	private double maxY;
	private double yInterval;
	private int test = 0;
	
	private float goodN = 0;
	private int level = 0;

	public Histogram (ArrayList<Integer> data, double minY, double maxY, int [] xCategories, String graphTitle) {
		this(data,minY,maxY,new String[0],graphTitle);
		this.xCategories = new String [xCategories.length];
		for (int i=0;i<xCategories.length;i++) {
			this.xCategories[i] = ""+xCategories[i];
		}
		
	}
	
	public Histogram (ArrayList<Integer> data, double minY, double maxY, String [] xCategories, String graphTitle) {
		this.data = data;
		this.minY = minY;
		this.maxY = maxY;
		this.xCategories = xCategories;
		this.graphTitle = graphTitle;
		this.yInterval = findOptimalYInterval(maxY);
	}
	
	public Histogram (ArrayList<?> data, double minY, double maxY, float goodN, int level, String [] xCategories, String graphTitle, int test) {
		this.data = data;
		this.minY = minY;
		this.maxY = maxY;
		this.xCategories = xCategories;
		this.graphTitle = graphTitle;
		this.yInterval = findOptimalYInterval(maxY);
		this.goodN = goodN;
		this.level = level;
		this.test = test;
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
		g.drawLine(xOffset, getHeight()-40, getWidth()-10,getHeight()-40); //x
		g.drawLine(xOffset, getHeight()-40, xOffset, 40);					//y
		
		// Draw the xLabel under the xAxis
		//g.drawString(xLabel, (getWidth()/2) - (g.getFontMetrics().stringWidth(xLabel)/2), getHeight()-5);
		
	
		// Now draw the data points
		int baseWidth = (getWidth()-(xOffset+10))/data.size();
		if (baseWidth<1) baseWidth=1;
		
		// First draw faint boxes over alternating bases so you can see which is which
		
		// Let's find the longest label, and then work out how often we can draw labels
		
		int lastXLabelEnd = 0;
		
		if(test == 0){
			for (int i=0;i<data.size();i++) {
				if (i%2 != 0) {
					g.setColor(new Color(230, 230, 230));
					g.fillRect(xOffset+(baseWidth*i), 40, baseWidth, getHeight()-80);
				}
				g.setColor(Color.BLACK);
				/*String baseNumber = ""+xCategories[i];
				int baseNumberWidth = g.getFontMetrics().stringWidth(baseNumber);
				int baseNumberPosition =  (baseWidth/2)+xOffset+(baseWidth*i)-(baseNumberWidth/2);
				
				if (baseNumberPosition > lastXLabelEnd) {
					g.drawString(baseNumber,baseNumberPosition, getHeight()-25);
					lastXLabelEnd = baseNumberPosition+baseNumberWidth+5;
				}*/
			}
		}

		
		// Now draw horizontal lines across from the y axis

		g.setColor(new Color(180,180,180));
		for (double i=yStart+yInterval;i<=maxY;i+=yInterval) {
			g.drawLine(xOffset, getY(i), getWidth()-10, getY(i));
		}
		g.setColor(Color.BLACK);
		
		// Now draw the datasets
		
		/*if (g instanceof Graphics2D) {
			((Graphics2D)g).setStroke(new BasicStroke(2));
			((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}		//размывание линий*/
		
		if(test == 0){
			
			for(int d = 0; d < data.size(); d++){
				int thisY = getY((Integer) data.get(d));
	    		g.setColor(Color.BLUE);
	    		g.fillRect(xOffset+(baseWidth*d),thisY+1, baseWidth,getHeight()-40-thisY);
	    		g.setColor(Color.black);
	    		g.drawRect(xOffset+(baseWidth*d),thisY, baseWidth,getHeight()-40-thisY);
	    	}
			
			g.setColor(Color.RED);
			g.drawLine(xOffset, getY(goodN), getWidth()-10,getY(goodN));
			g.setColor(Color.GREEN);
			g.drawLine(xOffset, getY(level), getWidth()-10,getY(level));
		}
		else if(test == 1){
			for(int d = 0; d < data.size(); d++){
				int thisY = getY((Integer) data.get(d));
				if(d%2 == 0){
					g.setColor(Color.BLUE);
					if(baseWidth == 1)
						g.fillRect(xOffset+(baseWidth*d),thisY-1, baseWidth,getHeight()-40-thisY);
					else
						g.fillRect(xOffset+1+(baseWidth*d),thisY-1, baseWidth-1,getHeight()-40-thisY);
				}
				else{
					g.setColor(Color.GREEN);
					if(baseWidth == 1)
						g.fillRect(xOffset+(baseWidth*d),thisY-1, baseWidth,getHeight()-40-thisY);
					else
						g.fillRect(xOffset+1+(baseWidth*d),thisY-1, baseWidth-1,getHeight()-40-thisY);
				}
	    	}
		}
		else{
			for(int d = 0; d < data.size(); d++){
				int thisY = getY((Double) data.get(d));
	    		g.setColor(Color.BLUE);
	    		g.fillRect(xOffset+(baseWidth*d),thisY+1, baseWidth,getHeight()-40-thisY);
	    		g.setColor(Color.black);
	    		g.drawRect(xOffset+(baseWidth*d),thisY, baseWidth,getHeight()-40-thisY);
	    	}
		}
		
		// Now draw the data legend
		g.setColor(Color.black);
		if (g instanceof Graphics2D) {
			((Graphics2D)g).setStroke(new BasicStroke(1));
			((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		}
		
	}

	private int getY(int y) {
		return (getHeight()-40) - (int)(((getHeight()-80)/(maxY-minY))*y);
	}

	private int getY(double y) {
		return (getHeight()-40) - (int)(((getHeight()-80)/(maxY-minY))*y);
	}
	
}
