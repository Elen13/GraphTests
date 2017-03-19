package modules;

import graphs.BaseGroup;
import graphs.Histogram;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.xml.stream.XMLStreamException;

import report.HTMLReportArchive;
import sequence.Sequence;

public class Series extends AbstractQCModule{
	
	public ArrayList<Integer> data;
	public String [] xCategories = new String[0];
	
	private float goodN = 0;
	private int D = 0;
	private int level = 0;

	public int getMax(){
		int max = 0;
		
		for(int i = 0; i < data.size(); i++){
			if(data.get(i) > max)
				max = data.get(i);
		}
		
		return max;
	}
	
	public void processSequence(Sequence sequence) {
		ArrayList<Integer> map = new ArrayList<Integer>();
		ArrayList<String> res = new ArrayList<String>();
		String key = "";
		String sbin = sequence.getSequence();
		int ser = 3;
		int size = sbin.length() - sbin.length() % ser;
		
		for(int i = 0; i < size; i += ser){
			for (int j = 0; j < ser; j++){
				key = key + sbin.charAt(j+i);
			}
			res.add(key);
			key = "";
		}
		
		for(int i = 0; i < Math.pow(2, ser); i++){
			map.add(0);
			StringBuilder sb = new StringBuilder();
	        int a = i, j = 0;
	        while ( j < ser){
	        	j++;
	            sb.append(a % 2);
	            a = a / 2;
	        }
	        sb.reverse();
			key = sb.toString();
			
			for(int n = 0; n < res.size(); n++){
				if(res.get(n).equals(key)){
					map.set(i, map.get(i)+1);
				}
			}
		}
		
		data = map;
		
		BaseGroup [] groups = BaseGroup.makeBaseGroups(data.size());
		//System.out.println("xCategories[i]: " + groups[3]);
		xCategories = new String[groups.length];
		for (int i=0;i<groups.length;i++) {
			String buf = groups[i].toString();
			buf = Integer.toBinaryString(Integer.parseInt(buf));
			xCategories[i] = buf;
		}
		
		int sizeSq = 0;
		for(int i = 0; i < map.size(); i++){
			sizeSq = sizeSq + map.get(i);
		}
		int maxD = 0;
		int minD = 0;
		goodN = sizeSq / (float)Math.pow(2, 3);
		maxD = Math.round(sizeSq - goodN);
		
		if(sizeSq % ser == 0){
			minD = 0;
		}
		else{
			minD = 1;
		}
		D = maxD - minD;
		level = (int)goodN + 57;
		//System.out.println("Size: "+sizeSq+" D: "+ D+" goodN: " + goodN);
	}

	public JPanel getResultsPanel() {
		return new Histogram(data, 0d, getMax() + 1, goodN, level, xCategories, "Проверка серий", false);
	}

	public String name() {
		return "Проверка серий";
	}

	public String description() {
		return "Данный тест позволяет оценить равномерность распределения символов в исследуемой последовательности на основе анализа частоты появления нулей, единиц и серий, состоящих из k бит";
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
