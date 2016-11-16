package graphTests;

import java.util.ArrayList;

public class Distribution {

	public static int findMax(ArrayList<Integer> sq){
		int max = 0;
		for(int i = 0; i < sq.size(); i++){
			if(sq.get(i) > max)
				max = sq.get(i);
		}
		return max;
	}
	
	public static int[][] test2(ArrayList<Integer> sq){
		int [][] coordinates = new int [sq.size()-1][2];
		int n = 1;
		
		for(int i = 0; i < sq.size()-1; i++){
			n--;
			for(int j = 0; j < 2; j++){
				coordinates[i][j] = sq.get(n);
				n++;
			}
		}
		
		return coordinates;
	}
	
}
