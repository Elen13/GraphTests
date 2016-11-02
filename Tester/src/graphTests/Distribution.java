package graphTests;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Distribution {
	private static int [][] pointArr;
	private static int size;
	
	public static void setSize(ArrayList<Integer> sq){
		size = sq.size();
	}
	
	public static void test2(ArrayList<Integer> sq){
		int [][] coordinates = new int [sq.size()-1][2];
		int n = 1;
		
		Distribution.setSize(sq);
		
		for(int i = 0; i < sq.size()-1; i++){
			n--;
			for(int j = 0; j < 2; j++){
				coordinates[i][j] = sq.get(n);
				n++;
			}
		}
		
		pointArr = coordinates.clone();
	}

	public static void pictureForDistribution(Graphics g, int w, int h){
		int x = w / 4;
		int y = h / 4;
		int width = 4;
		int step = 1;
		int xO = 0, yO = 0;

		g.setColor(Color.black);
		g.drawRect(x, y, 250, 250);

		for(int i = 0; i < size-1; i++){ 
			xO = step * pointArr[i][0] + 125 - width/2;  
			yO = 375 - step * pointArr[i][1] - width/2; 
			g.fillOval(xO, yO, width, width);
		}
	}
	
}
