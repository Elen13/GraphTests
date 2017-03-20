package modules;

import graphs.BaseGroup;
import graphs.LinearGraph;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.xml.stream.XMLStreamException;

import report.HTMLReportArchive;
import sequence.Sequence;

public class LinearComplexity extends AbstractQCModule{

	private byte[] b, c, t, s;
	private int N;
	private int L;
	private int m;
	
	int[] arrL = new int[0];

	
	public int getMax(int [] arr){
		int max = 0;
		for(int i = 0; i < arr.length; i++){
			if(arr[i] > max)
				max = arr[i];
		}
		return max;
	}
	
	public void BerlekampRegistryTester(ArrayList<Integer> binSq)
	{
	    b = new byte[binSq.size()];
	    c = new byte[binSq.size()];
	    t = new byte[binSq.size()];
	    s = new byte[binSq.size()];
	    for(int i = 0; i < s.length;i++){
	    	s[i] = binSq.get(i).byteValue();
	    	b[i] = c[i] = t[i] = 0;
	    }
	    /*for (int i = 0; i < binSq.size(); i++) 
	    	b[i] = c[i] = t[i] = 0;*/
	    b[0] = c[0] = 1;
	    N = L = 0;
	    m = -1;
	}

	public void processSequence(Sequence sequence) {
		String bsq = sequence.getSequence();
		ArrayList<Integer> binSq = new ArrayList<Integer>();//Series.sqToBin(sq, cap);
		for(int i = 0; i < bsq.length(); i++){
			 int i1 = Character.getNumericValue(bsq.charAt(i));
			binSq.add(i1);
		}
		
		BerlekampRegistryTester(binSq);
		
		arrL = new int[binSq.size()];
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
	     
	}

	public JPanel getResultsPanel() {
		return new LinearGraph(arrL, 0d, getMax(arrL) + 1, "Профиль линейной сложности");
	}

	public String name() {
		return "Профиль линейной сложности";
	}

	public String description() {
		return "Исследование последовательности на случайность, анализируя зависимость линейной сложности последовательности от ее длины.";
	}

	public void reset() {
		arrL = new int[0];
		b = new byte[0];
	    c = new byte[0];
	    t = new byte[0];
	    s = new byte[0];
	}

	public boolean raisesError() {
		return false;
	}

	public boolean raisesWarning() {
		return false;
	}

	public boolean ignoreFilteredSequences() {
		return true;
	}

	public boolean ignoreInReport() {
		return false;
	}

	@Override
	public void makeReport(HTMLReportArchive report) throws XMLStreamException,
			IOException {
		// TODO Auto-generated method stub
		
	}

}
