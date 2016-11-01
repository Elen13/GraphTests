package graphTests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Series {
	public static ArrayList<Integer> sqToBin(ArrayList<Integer> sq, int cap){
		String bin = new String();
		
		for (int i = 0; i < sq.size(); i++){
	        StringBuilder sb = new StringBuilder();
	        int a = sq.get(i), j = 0;
	        while ( j<cap){
	        	j++;
	            sb.append(a % 2);
	            a = a / 2;
	        }
	        sb.reverse();
	        bin = bin.concat(sb.toString());
		}
		
		ArrayList<Integer> list = new ArrayList<>(); 
		for(int j = 0; j<bin.length(); j++){
			list.add(Character.getNumericValue(bin.charAt(j)));
		}
		
		return list;
	}
	
	public static void test3(ArrayList<Integer> sq, int cap, int ser){
		ArrayList<Integer> bin = Series.sqToBin(sq, cap);
		Map<String, Integer> res = new HashMap<String, Integer>();
		int a = 0, b = 0;
		
		for(int i = 0; i < bin.size(); i += ser){
			a = bin.get(i);
			b = bin.get(i+1);
			String key = Integer.toString(a)+Integer.toString(b);
			if(!res.containsKey(key)){
				res.put(key, 0);
				for(int j = 0; j <  bin.size(); j+=ser){
					if(bin.get(j) == a && bin.get(j+1) == b){
						res.put(key, res.get(key)+1);
					}
				}
			}
		}
		
		System.out.println("----------Test3----------");
		for (Map.Entry<String, Integer> entry: res.entrySet())
		    System.out.println(entry.getKey() + " = " + entry.getValue());
	}
}
