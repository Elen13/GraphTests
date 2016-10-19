package graphTests;

import java.util.ArrayList;

public class Series {
	public static ArrayList<ArrayList<Integer>> sqToSeries(String sq, int cap){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> part = new ArrayList<Integer>(cap);
		int i = 0, ind = 0;
		
		while(i < sq.length()){
			if(i+cap < sq.length()){
				for(int j=0; j < cap; j++){
					part.add(Character.getNumericValue(sq.charAt(i)));
					i++;
					System.out.println("HELLO PART "+part);
				}
				res.add(ind,part);
				ind++;
				System.out.println("HELLO RES "+part);
				part.clear();
			}
			else {
				int l = sq.length() - i;
				for (int j=0; j < l; j++) {
					part.add(Character.getNumericValue(sq.charAt(i)));
					i++;
					System.out.println("HELLO PART ELSE "+part);
		        }
				for(int j=0; j < cap; j++){
					if(part.get(j) == null){
						part.add(j,0);
					}
				}
				res.add(ind,part);
				ind++;
			}
			System.out.println("HELLO I "+i);
		}
		System.out.println(res);
		return res;
	}
}
