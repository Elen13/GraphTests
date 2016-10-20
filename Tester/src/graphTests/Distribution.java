package graphTests;

import java.util.ArrayList;

public class Distribution {
	public static void test2(ArrayList<Integer> sq){
		int [][] coordinates = new int [sq.size()-1][2];
		int n = 1;
		
			for(int i = 0; i < sq.size()-1; i++){
				n--;
				for(int j = 0; j < 2; j++){
					coordinates[i][j] = sq.get(n);
					System.out.println("elem = "+ sq.get(n));
					n++;
				}
			}
		
		for(int i = 0; i < sq.size()-1; i++){
			for(int j = 0; j < 2; j++){
				System.out.print(coordinates[i][j] + ",");
			}
			System.out.println();
		}
	}

}
