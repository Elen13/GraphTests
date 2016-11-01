package graphTests;

import java.util.ArrayList;

public class SpectralTest {

	private static double pi = Math.PI;
	
	public static double[] test6(ArrayList<Integer> sq, int cap){
		
		ArrayList<Integer> normSq = ACF.normViewBit(sq, cap);
		
		double Re = 0, Im = 0;
		double[] res = new double[normSq.size()];
		
		for(int i = 0; i < normSq.size(); i++){
			for(int k = 1; k <= normSq.size(); k++){
				Re += normSq.get(k-1) * Math.cos(2*pi*(i+1)*(k-1)/10);
				Im += normSq.get(k-1) * Math.sin(2*pi*(i+1)*(k-1)/10);
			}
			res[i] = Math.sqrt(Re*Re + Im*Im);
			Re = 0;
			Im = 0;
		}
		
		return res;
	}
}
