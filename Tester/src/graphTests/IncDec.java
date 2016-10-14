package graphTests;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class IncDec {
	public static Map<Integer, Integer> test4(ArrayList<Integer> sq, int cap){
		Map<Integer, Integer> res = new HashMap<Integer, Integer>();
		int i = 0, inc = 1, dec = -1;
		
		while(i < sq.size()){
			res.put(inc, 0);
			while(i < sq.size() && sq.get(i) <= sq.get(i+1)){		// inc +
				res.put(inc, res.get(inc)+1);
				i++;
			}
			inc++;
			res.put(dec, 0);
			while(i < sq.size() && sq.get(i) >= sq.get(i+1)){		// dec -
				res.put(inc, res.get(dec)+1);
				i++;
			}
			dec--;
		}
		
		return res;
	}
	
	public static void show(ArrayList<Integer> sq, int cap){
		
		Map<Integer, Integer> res = IncDec.test4(sq, cap);
		
		System.out.println("----------Test4----------");
		for (Map.Entry<Integer, Integer> entry: res.entrySet())
		    System.out.println(entry.getKey() + " = " + entry.getValue());
		
	}

}