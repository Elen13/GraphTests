package modules;

import graphs.BaseGroup;
import graphs.Histogram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.xml.stream.XMLStreamException;

import report.HTMLReportArchive;
import sequence.Sequence;

public class IncDec extends AbstractQCModule{
	
	public ArrayList<Integer> data;
	Map<Integer, Integer> resMap; 
	public String [] xCategories = new String[0];
	
	private float goodN = 0;
	private int D = 0;
	private int level = 0;

	public int getMax(){
		int max = 0;
		
		for (Map.Entry<Integer, Integer> entry: resMap.entrySet()){
			if(entry.getValue() > max)
				max = entry.getValue();
		}
		
		return max;
	}
	
	public void processSequence(Sequence sequence) {
		Map<Integer, Integer> res = new HashMap<Integer, Integer>();
		ArrayList<Integer> sq = BarGraph.binToDec(sequence.getSequence(), 4);
		int i = 0, inc = 0, dec = 1, count = 1, c=0, f=0;

		while(sq.get(i) == sq.get(i+1)){
			c++; i++;
			///System.out.println("HELLO c == "  + c);
		
			if(sq.get(i+1) > sq.get(i)){
				res.put(inc, c);
				//System.out.println("HELLO c INC = "  + c);
			}
			else{
				res.put(dec, c);
				//System.out.println("HELLO c DEC = "  + c);
			}
			//System.out.println("Hello while start");
		}
		//System.out.println("sq size: " + sq.size());
		
		while(i < sq.size()-1){
			//System.out.println("Hello while sq: " + i);
			while(i < sq.size()-1 && sq.get(i) < sq.get(i+1)){
				/*while(i < sq.size()-1 && sq.get(i) >= sq.get(i-1)){
					System.out.println("HELLO INC "  + sq.get(i) + " i="+i);
					count++; i++;
				}*/
				//System.out.println("HELLO INC "  + sq.get(i) + " i="+i);
				count++; i++;
				while(i < sq.size()-1 && sq.get(i) == sq.get(i+1)){
					if(sq.get(i) == sq.get(i-1)){
						count++; i++;
					}
					//System.out.println("HELLO INC == "  + sq.get(i) + " i="+i);
					count++; i++;
				}
				f = 1;
			}
			if(f == 1 && res.get(inc) == null){
				res.put(inc, count);
				inc+=2;
				count = 0;
				//f = 0;
			}else{
				if(f == 1) {
					res.put(inc, res.get(inc)+count);
					inc+=2;
					count = 0;
					//f = 0;
				}
			}
			
			while(i < sq.size()-1 && sq.get(i) > sq.get(i+1)){
				/*while(i < sq.size()-1 && sq.get(i) <= sq.get(i-1)){
					System.out.println("HELLO DEC "  + sq.get(i) + " i="+i);
					count++; i++;
				}*/
				//System.out.println("HELLO DEC "  + sq.get(i) + " i="+i);
				count++; i++;
				while(i < sq.size()-1 && sq.get(i) == sq.get(i+1)){
					//System.out.println("HELLO DEC == "  + sq.get(i) + " i="+i);
					if(sq.get(i) == sq.get(i-1)){
						count++; i++;
					}
					count++; i++;
				}
				f = 1;
			}
			if(f == 1 && res.get(dec) == null){
				res.put(dec, count);
				dec+=2;
				count = 0;
				//f = 0;
			}else{
				if(f == 1) {
					res.put(dec, res.get(dec)+count);
					dec+=2;
					count = 0;
					//f = 0;
				}
			}
			if(f==0){
				c = 0;
					c++; i++;
					///System.out.println("HELLO c == "  + c);
				
					if(i < sq.size()-1 && sq.get(i+1) > sq.get(i)){
						res.put(inc, c);
						//System.out.println("HELLO c INC = "  + c);
					}
					else{
						res.put(dec, c);
						//System.out.println("HELLO c DEC = "  + c);
					}
					//System.out.println("Hello while equals");
			}
			f = 0;
			
			
		}
		resMap = res;
		
		ArrayList<Integer> map = new ArrayList<>(res.values());
		data = map;
		
		BaseGroup [] groups = BaseGroup.makeBaseGroups(data.size());
		xCategories = new String[groups.length];
		for (int j=0;j<groups.length;j++) {
			xCategories[j] = groups[j].toString();
		}
		/*System.out.println("sizeMap: " + res.size());
		System.out.println("sizeList: " + map.size());
		
		Map<Integer, Integer> sourceMap = new HashMap<Integer, Integer>();
		sourceMap.put(1, 11);
		sourceMap.put(2, 22);
		sourceMap.put(7, 77);
		ArrayList<Integer> m = new ArrayList<>(sourceMap.values());
		for (Map.Entry<Integer, Integer> entry: sourceMap.entrySet())
			System.out.println(entry.getKey() + " = " + entry.getValue());
		for (int j = 0; j < m.size(); j++){
			System.out.println(j + " = " + m.get(j));
		}*/
		
		/*//for (Map.Entry<Integer, Integer> entry: res.entrySet()){
			for (int j = 0; j < map.size(); j++){
				if(map.get(j) == res.get(i)) System.out.println("==");
				else System.out.println("!=");
			}*/

		//System.out.println(entry.getKey() + " = " + entry.getValue());
		
	}

	public JPanel getResultsPanel() {
		return new Histogram(data, 0d, getMax() + 1, goodN, level, xCategories, "Проверка на монотонность", 1); //test = 1
	}

	public String name() {
		return "Проверка на монотонность";
	}

	public String description() {
		return "Данный тест позволяет оценить равномерность распределения символов в исследуемой последовательности на основе анализа длин участков невозрастания и неубывания элементов последовательности.";
	}

	public void reset() {
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
