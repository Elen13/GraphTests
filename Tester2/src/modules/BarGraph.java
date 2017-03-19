package modules;

import graphs.BaseGroup;
import graphs.Histogram;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.xml.stream.XMLStreamException;

import report.HTMLReportArchive;
import sequence.Sequence;

public class BarGraph extends AbstractQCModule {

	public int max = 0;
	public ArrayList<Integer> data;
	public String [] xCategories = new String[0];
	
	private float goodN = 0;
	private int D = 0;
	private int level = 0;
	
	public int getMaxY(){
		int max = 0;
		
		for(int i = 0; i < data.size(); i++){
			if(data.get(i) > max)
				max = data.get(i);
		}
		
		return max;
	}
	
	public int getMinY(){
		int min = getMaxY();
		
		for(int i = 0; i < data.size(); i++){
			if(data.get(i) < min)
				min = data.get(i);
		}
		
		return min;
	}
	
	public int findMax(ArrayList<Integer> sq){
		int max = 0;
		for(int i = 0; i < sq.size(); i++){
			if(sq.get(i) > max)
				max = sq.get(i);
		}
		return max;
	}
	
	public void processSequence(Sequence sequence) {
		ArrayList<Integer> sq = binToDec(sequence.getSequence(), 3);
		//System.out.println("BarGraph Hello!");
		max = findMax(sq) + 1;
		ArrayList<Integer> res = new ArrayList<Integer>(max);
		
		for(int i = 0; i < max; i++){
			res.add(0);
		}
		for(int i = 0; i < sq.size(); i++){
			res.set(sq.get(i), res.get(sq.get(i))+1);
		}
		data = res;	
		
		BaseGroup [] groups = BaseGroup.makeBaseGroups(data.size());
		xCategories = new String[groups.length];
		for (int i=0;i<groups.length;i++) {
			xCategories[i] = groups[i].toString();
		}
		
		int sizeSq = sq.size();
		int maxD = 0;
		int minD = 0;
		goodN = sizeSq / (float)Math.pow(2, 3);
		maxD = Math.round(sizeSq - goodN);
		
		if(sizeSq % 3 == 0){
			minD = 0;
		}
		else{
			minD = 1;
		}
		D = maxD - minD;
		level = (int)goodN + 57;
		//System.out.println("Size: "+sizeSq+" D: "+ D+" goodN: " + goodN);
		
	}

	public static ArrayList<Integer> binToDec(String sq, int cap){
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
	
	@Override
	public JPanel getResultsPanel() {
		//if (!calculated) getPercentages();
		return new Histogram(data, 0d, getMaxY() + 1, goodN, level, xCategories, "Гистограмма распределения элементов последовательности", false);
	}

	public String name() {
		return "Гистограмма распределения";
	}

	public String description() {
		return "Показывает распределение элементов последовательности";
	}

	public void reset() {
		max = 0;
		data = new ArrayList<Integer>();
		goodN = 0;
		D = 0;
		level = 0;
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
