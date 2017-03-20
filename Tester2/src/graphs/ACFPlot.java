package graphs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ACFPlot extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Double> data;
	private String graphTitle;
	private double minY = -1;
	private double maxY = 1;
	private double yInterval = 0.2;
	
	
	public ACFPlot (ArrayList<Double> data, double minY, double maxY, String graphTitle) {
		this.data = data;
		this.graphTitle = graphTitle;
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
		
		double yStart = -1;
		
		/*if (minY % yInterval == 0) {
			yStart = minY;
		}
		else {
			yStart = yInterval * (((int)minY/yInterval)+1);
		}
		*/
		int xOffset = 0;
		
		for (double i=0;i<=maxY*2;i+=yInterval) {
			String label = "" + String.format("%.1f", yStart);
			yStart+=yInterval;
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
				int baseWidth = (getWidth()-(xOffset+10))/data.size();
				if (baseWidth<1) baseWidth=1;
		
		// Now draw horizontal lines across from the y axis

		g.setColor(new Color(180,180,180));
		for (double i=0+yInterval;i<=maxY*2;i+=yInterval) {
			g.drawLine(xOffset, getY(i), getWidth()-10, getY(i));
		}
		g.setColor(Color.BLACK);
		
		// Now draw the datasets
		
		//int thisY = getY(1);
		//g.setColor(Color.BLUE);
		//g.fillRect(xOffset+(17),thisY, 17,getHeight()-40-getY(0.6));
		
		for (int d=0;d<data.size();d++) {
			if(data.get(d) >= 0){
				int thisY = getY(1+data.get(d));
	    		g.setColor(Color.BLUE);
	    		g.fillRect(xOffset+(baseWidth*d),thisY, baseWidth,getHeight()-40-getY(data.get(d)));
	    		g.setColor(Color.black);
	    		g.drawRect(xOffset+(baseWidth*d),thisY, baseWidth,getHeight()-40-getY(data.get(d)));		
			}
			else{
				int thisY = getY(1);
				g.setColor(Color.BLUE);
	    		g.fillRect(xOffset+(baseWidth*d),thisY, baseWidth,getHeight()-40-getY(0-data.get(d)));
	    		g.setColor(Color.black);
	    		g.drawRect(xOffset+(baseWidth*d),thisY, baseWidth,getHeight()-40-getY(0-data.get(d)));	
			}		
		}
	}
	
	private int getY(double y) {
		return (getHeight()-40) - (int)(((getHeight()-80)/(maxY-minY))*y);
	}

}
