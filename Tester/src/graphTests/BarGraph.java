package graphTests;

import java.util.ArrayList;

public class BarGraph {
	private static ArrayList<Integer> data;
	
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
		data = res;
		
		return res;
	}
	
	public static int getMax(){
		int max = 0;
		
		for(int i = 0; i < data.size(); i++){
			if(data.get(i) > max)
				max = data.get(i);
		}
		
		return max;
	}

}