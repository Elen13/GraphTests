package graphTests;

import java.util.ArrayList;

public class ACF {
	public static ArrayList<Integer> normViewBit(ArrayList<Integer> sq, int cap){
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
		
		ArrayList<Integer> list = new ArrayList<Integer>(); 
		for(int j = 0; j<bin.length(); j++){
			list.add(Character.getNumericValue(bin.charAt(j)));
		}
		
		for(int i=0; i < list.size(); i++){
			if(list.get(i) == 0){ list.set(i, -1); }
		}
	
		return list;
	}
	
	public static void bit(ArrayList<Integer> sq){
		ArrayList<Double> res = new ArrayList<Double>();
		double ch = 0, zn = 0;
		
		for(int j = 0; j <= sq.size(); j++){
			ch = 0; zn = 0;
			for(int i = 0; i < sq.size(); i++){
				ch = ch + sq.get(i) * sq.get((i+j)%sq.size());
				zn = zn + Math.pow(sq.get(i), 2);
			}
			res.add(ch/zn);
		}
		System.out.println("RESULT"+res);
	}
	
	public static ArrayList<Integer> normViewChar(ArrayList<Integer> sq, int cap){
		String bin = new String();
		
		for (int i = 0; i < sq.size(); i++){
	        StringBuilder sb = new StringBuilder();
	        int a = sq.get(i), j = 0;
	        while ( j<cap){
	        	j++;
	            sb.append(a % 2);
	            a = a / 2;
	        }
	        bin = bin.concat(sb.toString());
		}	
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> charSq = new ArrayList<Integer>(); 
		for(int j = 0; j<bin.length(); j++){
			list.add(Character.getNumericValue(bin.charAt(j)));
		}
		
		int n = 0;
		double ch = 0;
		while(n < list.size()){
			ch = 0;
			for(int j = 0; j < cap; j++){
				ch = ch + Math.pow(-1, list.get(n)) * Math.pow(2, j);
				n++;
			}
			charSq.add((int)ch);
		}
		
		return charSq;
	}

}
