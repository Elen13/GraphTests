package draw;

import graphTests.BarGraph;
import graphTests.Distribution;
import graphTests.Seqence;
import graphTests.LinearComplexity;

import java.awt.BasicStroke;
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
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString(s, x, y);		
	}
	
	public void axes (Graphics g){
		Graphics2D g2 = (Graphics2D) g;   
		g2.setStroke(new BasicStroke(2.0f));
		g2.setColor(Color.black);
		
		int x1 = getSize().width * 1/8;
		int y1 = getSize().height * 7/8;
		int x2 = getSize().width - x1;
		int y2 = getSize().height - y1;
		
		g2.drawLine(x1, y1, x2, y1);
		g2.drawLine(x1, y1, x1, y2);
		g2.setStroke(new BasicStroke());
	}
	
	public void draw(Graphics g){
		switch (num) {
	    case 1:  {
	    	ArrayList<Integer> data = BarGraph.test1(sq, cap);
	    	int x = getSize().width * 1/8;
			int y = getSize().height * 7/8;
	    	int wAxes = getSize().width*6/8;
	    	int hAxis = getSize().height*6/8;
	    	int strip_w = wAxes / data.size();
	    	int maxNum = BarGraph.getMax();
	    	
	    	g.setColor(Color.black);
	    	axes(g);
	    	
	    	for(int i = 0; i < data.size(); i++){
	    		int strip_h = data.get(i) * hAxis / maxNum;
	    		g.setColor(Color.BLUE);
	    		g.fillRect(x + i * strip_w, y - strip_h, strip_w, strip_h);
	    		g.setColor(Color.black);
	    		g.drawRect(x + i * strip_w, y - strip_h, strip_w, strip_h);
	    	}
	    	
	    	break;
	    }
	             
	    case 2:  {
			int [][] pointArr = Distribution.test2(sq);
			int widthRec = getSize().width - getSize().height * 1/2;
			double elp = (double)Seqence.findMax(sq) / (double)(widthRec - 7);
			int x = ( getSize().width - widthRec ) / 2;
			int y = ( getSize().height - widthRec) / 2;
			int width = 4;
			int step = 1;
			double xO = 0, yO = 0;

			g.setColor(Color.black);
			g.drawRect(x, y, widthRec, widthRec);
			
			for(int i = 0; i < sq.size()-1; i++){ 
				xO = x + step * pointArr[i][0] / elp - width/2;  
				yO = y + widthRec - step * pointArr[i][1] / elp - width/2; 
				g.fillOval((int)xO, (int)yO, width, width);
			}
	    	break;
	    }
	             
	    /*case 3:  g.setColor(Color.green);
			g.drawRect(50, 50, 13, 13);
	             break;
	    case 4:  monthString = "Апрель";
	             break;
	    case 5:  monthString = "Май";
	             break;
	    case 6:  monthString = "Июнь";
	             break;
	    case 7:  monthString = "Июль";
	             break;*/
	    case 8:  {
	    	int [] line = LinearComplexity.test7(sq, cap);
	    	int [] xValues = new int [line.length];
	    	int [] yValues = new int [line.length];
	    	int wAxes = getSize().width*6/8;
	    	int hAxis = getSize().height*6/8;
	    	double xElp = (double)line.length / (double)wAxes;
	    	double yElp = (double)line[line.length - 1] / (double)hAxis;
	    	
	    	System.out.println("xELP:"+xElp+ ", " + " yELP:"+yElp+ ", " + (double)line[line.length - 1] + " length:" + line.length);
	    	for(int i = 0; i < line.length; i++){
	    		xValues[i] = (int)((double)i / xElp) + getSize().width * 1/8;
	    		yValues[i] = getSize().height * 7/8 - (int)((double)line[i] / yElp);
	    		
	    		System.out.println("NEW x:"+ xValues[i] + " y:" + yValues[i] +" xreal:"+i+" yreal:"+line[i]);
	    	}
	    	
	    	axes(g);
	    	g.drawPolyline( xValues, yValues, line.length);
	    	
	    	break;
	    }
	            
	   /* default: monthString = "Не знаем такого";
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