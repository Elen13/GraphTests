package graphTests;

import java.util.*;


public class Seqence {
	String partSq(double part, int sChar, int cChar, String sq){
		
		if(part > 1.1){ throw new Error("Part is > then seqence!");}
		int endIndex = (int)(sq.length()*part);
		
		if(sChar > sq.length()){ throw new Error("Start char is too big");}
		String partSq = sq.substring(sChar, endIndex);
		
		char[] chArray = partSq.toCharArray();
		if(cChar > partSq.length()){ throw new Error("Count of chars is too big");}
		partSq = String.copyValueOf(chArray, 0, cChar);
		
		return partSq;
	}
	
	ArrayList<Integer> binToDec(String sq, int cap){
		ArrayList<Integer> arrDec = new ArrayList<Integer>();
		int i = 0, res = 0;
		
		while(i < sq.length()){
			if(i+cap < sq.length()){
				for(int j=cap-1; j >= 0; j--){
					res += Math.pow(2, j) * (sq.charAt(i) == '1' ? 1 : 0);
					i++;
				}
				arrDec.add(res);
				res = 0;
			}
			else {
				int l = sq.length() - i;
				int tmp[] = new int[cap];
				for (int j=0; j < l; j++) {
		            tmp[j] = Character.getNumericValue(sq.charAt(i));
		            i++;
		        }
				for(int j = 0, jj = tmp.length-1; j < tmp.length; j++,jj--){
					res += Math.pow(2, jj) * (tmp[j]);
				}
				arrDec.add(res);
				res = 0;
			}
		}
	    return arrDec;
	}

	
	
}