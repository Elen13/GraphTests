package graphTests;

import java.util.ArrayList;

public class BarGraph {
	public static ArrayList<Integer> test1(ArrayList<Integer> sq, int cap){
		int max = (int) (Math.pow(2, cap));
		ArrayList<Integer> res = new ArrayList<Integer>(max);
		
		for(int i = 0; i < max; i++){
			res.add(0);
			for(int j = 0; j < sq.size(); j++){
				if(sq.get(j) == i){
					res.set(i, res.get(i)+1);
				}
			}
		}
		
		return res;
	}
	
	public static void show(ArrayList<Integer> sq, int cap){
		
		ArrayList<Integer> res = BarGraph.test1(sq, cap);
		
		System.out.println("----------Test1----------");
		for(int i = 0; i < res.size(); i++){
			System.out.print(i+" ");
			for(int j = 0; j < res.get(i); j++){
				System.out.print("-");
			}
			System.out.println();
		}
		
	}

}