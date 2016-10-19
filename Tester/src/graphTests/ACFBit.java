package graphTests;

import java.util.ArrayList;

public class ACFBit {
	public static void test5(ArrayList<Integer> sq, int cap){
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
	        System.out.println(sb);
		}
		System.out.println("Bin " +bin);
		
		ArrayList<Integer> list = new ArrayList<>(); 
		for(int j = 0; j<bin.length(); j++){
			list.add(Character.getNumericValue(bin.charAt(j)));
		}
		
		for(int i=0; i < list.size(); i++){
			if(list.get(i) == 0){ list.set(i, -1); }
		}
		System.out.println("Bin " + list);
	
		
	}

}
