package graphTests;

import java.util.ArrayList;

public class LinearComplexity {
	
	private static byte[] b, c, t, s;
	private static int N;
	private static int L;
	private static int m;
	 
	public static void BerlekampRegistryTester(ArrayList<Integer> binSq)
	{
	    b = new byte[binSq.size()];
	    c = new byte[binSq.size()];
	    t = new byte[binSq.size()];
	    s = new byte[binSq.size()];
	    for(int i = 0; i < s.length;i++){
	    	s[i] = binSq.get(i).byteValue();
	    }
	    for (int i = 0; i < binSq.size(); i++) 
	        b[i] = c[i] = t[i] = 0;
	    b[0] = c[0] = 1;
	    N = L = 0;
	    m = -1;
	}
	
	public static int[] test7(ArrayList<Integer> sq, int cap){
		ArrayList<Integer> binSq = Series.sqToBin(sq, cap);
		
		System.out.println("Bin sq: "+ binSq+ "Size = "+binSq.size());
		
		LinearComplexity.BerlekampRegistryTester(binSq);
		
		int[] arrL = new int[binSq.size()];
		int d;
	    while (N < s.length)
	    {
	    	d = 0;
	        for (int i = 0; i <= L; i++) 
	            d += s[N-i] * c[i];
	        d = d % 2;
	 
	        if (d != 0)
	        {
	            t = (byte[])c.clone();
	            for (int i = 0; i <= s.length + m - 1 - N; i++)
	                c[N - m + i] = (byte)(c[N - m + i] ^ b[i]);
	            if (L <= (N / 2))
	            {
	                L = N + 1 - L;
	                m = N;
	                b = (byte[])t.clone();
	            }
	        }
	        arrL[N] = L;
	        N++;
	    }
	     
		return arrL;
	}
	
}
