package graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;
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
	private int bufOffset;
	
	private TranslateHandler translater;
	private static double translateX;
    private static double translateY;
    private static double scale;
	
	public LinearGraph (int [] data, double minY, double maxY, String graphTitle) {
		this.data = data;
		this.minY = minY;
		this.maxY = maxY;
		this.graphTitle = graphTitle;
		this.yInterval = findOptimalYInterval(maxY);
		
		this.translater = new TranslateHandler(this);
		this.addMouseListener(translater);
		this.addMouseMotionListener(translater);
		this.addMouseWheelListener(new ScaleHandler(this));
		
		translateX = 0;
        translateY = 0;
        scale = 0.5;
        setOpaque(true);
        setDoubleBuffered(true);
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
		//super.paint(g);
		
		/*AffineTransform tx = new AffineTransform();
            tx.translate(translateX, translateY);
            tx.scale(scale, scale);
            Graphics2D ourGraphics = (Graphics2D) g;
            ourGraphics.setColor(Color.WHITE);
            ourGraphics.fillRect(0, 0, getWidth(), getHeight());
            
            
            ourGraphics.setColor(Color.RED);
            ourGraphics.drawString("Game Test", 10, 20);
            
            
            ourGraphics.setTransform(tx);
            ourGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            ourGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);            
            ourGraphics.setColor(Color.BLACK);
            ourGraphics.drawRect(50, 50, 50, 50);
            ourGraphics.fillOval(100, 100, 100, 100);
            ourGraphics.fillOval(300, 300, 100, 100);
            ourGraphics.drawString("Test Affine Transform", 50, 30);
            // super.paint(g);*/
		AffineTransform tx = new AffineTransform();
        tx.translate(translateX, translateY);
        tx.scale(scale, scale);
        Graphics2D ourGraphics = (Graphics2D) g;
        ourGraphics.setColor(Color.WHITE);
        ourGraphics.fillRect(0, 0, getWidth(), getHeight());
       
        
        ourGraphics.setTransform(tx);
        ourGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ourGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);            
        ourGraphics.setColor(Color.BLACK);
        
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
			int width = ourGraphics.getFontMetrics().stringWidth(label);
			if (width > xOffset) {
				xOffset = width;
			}
			
			ourGraphics.drawString(label, 2, getY(i)+(g.getFontMetrics().getAscent()/2));			
		}
	
		// Give the x axis a bit of breathing space
		xOffset += 5;
		bufOffset = xOffset;            
   
       
		// Draw the graph title
		int titleWidth = ourGraphics.getFontMetrics().stringWidth(graphTitle);
		ourGraphics.drawString(graphTitle, (xOffset + ((getWidth()-(xOffset+10))/2)) - (titleWidth/2), 30);
				
		//int baseWidth = 7;
		
		// Now draw the axes
		ourGraphics.drawLine(xOffset, getHeight()-40, getWidth()-10, getHeight()-40);
		ourGraphics.drawLine(xOffset, getHeight()-40, xOffset, 40);
				
				
		// Now draw the data points
		int baseWidth = (getWidth()-(xOffset+10))/data.length;
		if (baseWidth<1) baseWidth=1;
		
		for (int i=0;i<data.length;i++) {
			if (i%2 != 0) {
				ourGraphics.setColor(new Color(230, 230, 230));
				ourGraphics.fillRect(xOffset+(baseWidth*i), 40, baseWidth, getHeight()-80-1);
			}
			ourGraphics.setColor(Color.BLACK);
		}
		
		// Now draw horizontal lines across from the y axis

		ourGraphics.setColor(new Color(180,180,180));
		for (double i=yStart+yInterval;i<=maxY;i+=yInterval) {
			ourGraphics.drawLine(xOffset, getY(i), getWidth()-10, getY(i));
		}
		ourGraphics.setColor(Color.BLACK);
		
		
		//draw data set
		ourGraphics.setStroke(new BasicStroke(2));
		
		ourGraphics.setColor(Color.BLACK);
		int [] xValues = new int [data.length];
    	int [] yValues = new int [data.length];
    	int thisY = 0;
    	
		for(int i = 0; i < data.length; i++){
			thisY = getY(data[i]);
    		xValues[i] = xOffset+(getX(i));
    		yValues[i] = thisY;    		
    	}
		
		ourGraphics.drawPolyline( xValues, yValues, data.length);
		
		// Now draw the data legend

		ourGraphics.setStroke(new BasicStroke(1));
			
		ourGraphics.setColor(Color.RED);
		ourGraphics.drawLine(xOffset, getHeight()-40, xOffset+getX(data.length), getY(maxY));
		ourGraphics.setColor(Color.BLACK);
		
		/*g.setColor(Color.WHITE);
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
		bufOffset = xOffset;
		
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
		
		/*System.out.println("Data size: "+ data.length);
		int x = (int)(((getWidth()-xOffset-10)/((double)data.length))*data.length);
		g.setColor(Color.YELLOW);
		g.drawLine(xOffset, getHeight()-40, xOffset+x, getY(maxY));
		System.out.println("X: "+ getWidth());*/
		
		/*sg.setColor(Color.BLACK);
		int [] xValues = new int [data.length];
    	int [] yValues = new int [data.length];
    	int thisY = 0;
    	
		for(int i = 0; i < data.length; i++){
			thisY = getY(data[i]);
    		xValues[i] = xOffset+(getX(i));
    		yValues[i] = thisY;    		
    	}
		
    	g.drawPolyline( xValues, yValues, data.length);
		
		// Now draw the data legend

		if (g instanceof Graphics2D) {
			((Graphics2D)g).setStroke(new BasicStroke(1));
			((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		}
		
		g.setColor(Color.RED);
		g.drawLine(xOffset, getHeight()-40, xOffset+getX(data.length), getY(maxY));
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
	
	private int getX(int x) {
		int a = (int) (((getWidth()-bufOffset-10)/((double)data.length))*x);
		return a;
	}
	
	/*private int getX(int x) {
		int a = (int) (((data.length/2*7-bufOffset)/((double)data.length))*x);
		return a;
	}*/
	
	private int getY(double y) {
		return (getHeight()-40) - (int)(((getHeight()-80)/(maxY-minY))*y);
	}
	
	private static class TranslateHandler implements MouseListener, MouseMotionListener {
		private int lastOffsetX;
		private int lastOffsetY;
		private LinearGraph canvas;
		 
	    public TranslateHandler(LinearGraph canvas){
	    	this.canvas = canvas;
	    }
		
		public void mousePressed(MouseEvent e) {
		    // capture starting point
		    lastOffsetX = e.getX();
		    lastOffsetY = e.getY();
		}
		
		public void mouseDragged(MouseEvent e) {
		    
		    // new x and y are defined by current mouse location subtracted
		    // by previously processed mouse location
		    int newX = e.getX() - lastOffsetX;
		    int newY = e.getY() - lastOffsetY;
		
		    // increment last offset to last processed by drag event.
		    lastOffsetX += newX;
		    lastOffsetY += newY;
		
		    // update the canvas locations
		    canvas.translateX += newX;
		    canvas.translateY += newY;
		    
		    // schedule a repaint.
		    canvas.repaint();
		    
		}
		
		public void mouseClicked(MouseEvent e) { System.out.println("Mouse clicked!"); }
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
	
	private static class ScaleHandler implements MouseWheelListener {
    	
    	private LinearGraph canvas;
    	
    	public ScaleHandler(LinearGraph canvas){
    		this.canvas = canvas;
    	}
    	
        public void mouseWheelMoved(MouseWheelEvent e) {
            if(e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                
                // make it a reasonable amount of zoom
                // .1 gives a nice slow transition
                canvas.scale += (.1 * e.getWheelRotation());
                // don't cross negative threshold.
                // also, setting scale to 0 has bad effects
                canvas.scale = Math.max(0.00001, canvas.scale); 
                canvas.repaint();
            }
        }
    }
}
