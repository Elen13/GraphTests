package modules;

import graphs.BaseGroup;
import graphs.Histogram;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.xml.stream.XMLStreamException;


import report.HTMLReportArchive;
import sequence.Sequence;

public class Spectral extends AbstractQCModule{

	public ArrayList<Double> data;
	public String [] xCategories = new String[0];
	private double pi = Math.PI;
	
	private float goodN = 0;
	private int level = 0;
	
	
	public int getMax(){
		int max = 0;
		
		for(int i = 0; i < data.size(); i++){
			if(data.get(i) > max)
				max = data.get(i).intValue();
		}
		
		return max;
	}

	public static ArrayList<Integer> normViewBit(String bsq){
		ArrayList<Integer> list = new ArrayList<Integer>(); 
		for(int j = 0; j < bsq.length(); j++){
			list.add(Character.getNumericValue(bsq.charAt(j)));
		}
		
		for(int i=0; i < list.size(); i++){
			if(list.get(i) == 0){ list.set(i, -1); }
		}
		return list;
	}
	
	public void processSequence(Sequence sequence) {
		ArrayList<Integer> normSq = normViewBit(sequence.getSequence());
		
		double Re = 0, Im = 0;
		ArrayList<Double> res = new ArrayList<Double>(normSq.size() / 2);
		
		for(int i = 0; i < normSq.size() / 2; i++){
			for(int k = 1; k <= normSq.size() / 2; k++){
				Re += normSq.get(k-1) * Math.cos(2*pi*(i+1)*(k-1)/10);
				Im += normSq.get(k-1) * Math.sin(2*pi*(i+1)*(k-1)/10);
			}
			res.add(Math.sqrt(Re*Re + Im*Im));
			Re = 0;
			Im = 0;
		}
		data = res;
		
		BaseGroup [] groups = BaseGroup.makeBaseGroups(data.size());
		xCategories = new String[groups.length];
		for (int i=0;i<groups.length;i++) {
			xCategories[i] = groups[i].toString();
		}
	}

	public JPanel getResultsPanel() {
		return new Histogram(data, 0d, getMax() + 1, goodN, level, xCategories, "Спектральный тест", 2); //test = 2
	}

	public String name() {
		return "Спектральный тест";
	}

	public String description() {
		return "Проверка равномерности распределения 0 и 1 в исследуемой последовательности на основе анализа высоты выбросов преобразования Фурье.";
	}

	public void reset() {
		goodN = 0;
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
