package graphTests;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class IncDec {
	public static Map<Integer, Integer> test4(ArrayList<Integer> sq, int cap){
		Map<Integer, Integer> res = new HashMap<Integer, Integer>();
		int i = 0, inc = 1, dec = -1, count = 1, c=0, f=0;

		while(sq.get(i) == sq.get(i+1)){
			c++; i++;
			System.out.println("HELLO c == "  + c);
		
			if(sq.get(i+1) > sq.get(i)){
				res.put(inc, c);
				System.out.println("HELLO c INC = "  + c);
			}
			else{
				res.put(dec, c);
				System.out.println("HELLO c DEC = "  + c);
			}
		}
		//if(i == 0){ i++; }
		while(i < sq.size()-1){
			while(i < sq.size()-1 && sq.get(i) < sq.get(i+1)){
				/*while(i < sq.size()-1 && sq.get(i) >= sq.get(i-1)){
					System.out.println("HELLO INC "  + sq.get(i) + " i="+i);
					count++; i++;
				}*/
				System.out.println("HELLO INC "  + sq.get(i) + " i="+i);
				count++; i++;
				while(i < sq.size()-1 && sq.get(i) == sq.get(i+1)){
					System.out.println("HELLO INC == "  + sq.get(i) + " i="+i);
					count++; i++;
				}
				f = 1;
			}
			if(f == 1 && res.get(inc) == null){
				res.put(inc, count);
				inc++;
				count = 0;
				f = 0;
			}else{
				if(f == 1) {
					res.put(inc, res.get(inc)+count);
					inc++;
					count = 0;
					f = 0;
				}
			}
			
			while(i < sq.size()-1 && sq.get(i) > sq.get(i+1)){
				/*while(i < sq.size()-1 && sq.get(i) <= sq.get(i-1)){
					System.out.println("HELLO DEC "  + sq.get(i) + " i="+i);
					count++; i++;
				}*/
				System.out.println("HELLO DEC "  + sq.get(i) + " i="+i);
				count++; i++;
				while(i < sq.size()-1 && sq.get(i) == sq.get(i+1)){
					System.out.println("HELLO DEC == "  + sq.get(i) + " i="+i);
					count++; i++;
				}
				f = 1;
			}
			if(f == 1 && res.get(dec) == null){
				res.put(dec, count);
				dec--;
				count = 0;
				f = 0;
			}else{
				if(f == 1) {
					res.put(dec, res.get(dec)+count);
					dec--;
					count = 0;
					f = 0;
				}
			}
			
			
		}
		/*while(i < sq.size()){
			if(i == 0){ 
				System.out.println("HELLO i==0");
				i = 1; count = 1; }
			System.out.println(" i="+i);
			while(i < sq.size() && sq.get(i) > sq.get(i-1)){
				System.out.println("HELLO INC "  + sq.get(i) + " i="+i);
				count++;
				i++;
				while(i != sq.size() && sq.get(i) == sq.get(i-1)){
					System.out.println("HELLO INC == "  + sq.get(i) + " i="+i);
					count++; 
					i++;
				}	
				f = 1;
			}
			if(f == 1 && res.get(inc) == null){
				res.put(inc, count);
				inc++; i++;
				count = 1;
				f = 0;
			}else{
				if(f == 1) {
					res.put(inc, res.get(inc)+count);
					inc++; i++;
					count = 1;
					f = 0;
				}
			}
		
				//i++;
			
			while(i < sq.size() && sq.get(i) < sq.get(i-1)){
				System.out.println("HELLO DEC "  + sq.get(i)+ " i="+i);
				count++;
				i++;
				while(i < sq.size() && sq.get(i) == sq.get(i-1)){
					count++; i++;
				}
				f = 1;
			}
			if(f == 1 && res.get(dec) == null){
				res.put(dec, count);
				dec--;	i++;
				count = 1;
				f = 0;
			}else{
				if(f == 1) {
					res.put(dec, res.get(dec)+count);
					dec--;	i++;
					count = 1;
					f = 0;
				}
			}
			
		}*/
		
		return res;
	}
	
	public static void show(ArrayList<Integer> sq, int cap){
		
		Map<Integer, Integer> res = IncDec.test4(sq, cap);
		
		System.out.println("----------Test4----------");
		for (Map.Entry<Integer, Integer> entry: res.entrySet())
		    System.out.println(entry.getKey() + " = " + entry.getValue());
		
	}

}