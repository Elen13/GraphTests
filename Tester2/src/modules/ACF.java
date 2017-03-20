package modules;

import graphs.ACFPlot;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.xml.stream.XMLStreamException;


import report.HTMLReportArchive;
import sequence.Sequence;

public class ACF extends AbstractQCModule{

	public ArrayList<Double> data;
	
	public double getMax(){
		double max = 0;
		
		for(int i = 0; i < data.size(); i++){
			if(data.get(i) > max)
				max = data.get(i).intValue();
		}
		
		return max;// * 100;
	}
	
	public double getMin(){
		double min = 0;
		
		for(int i = 0; i < data.size(); i++){
			if(data.get(i) < min)
				min = data.get(i);
		}
		
		return min;//(int)(min * 100);
	}
	
	public ArrayList<Integer> normViewBit(String bsq){

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
		ArrayList<Integer> sq = normViewBit(sequence.getSequence());
		ArrayList<Double> res = new ArrayList<Double>();
		double ch = 0, zn = 0;

		for(int j = 0; j <= sq.size(); j++){
			ch = 0; zn = 0;
			for(int i = 0; i < sq.size(); i++){
				ch = ch + sq.get(i) * sq.get((i+j)%sq.size());
				zn = zn + Math.pow(sq.get(i), 2);
			}
			res.add(ch/zn);
		}
		data = res;
		
	}

	public JPanel getResultsPanel() {
		return new ACFPlot(data, getMin(), getMax(), "Автокорреляционная функция");
	}

	public String name() {
		return "Автокорреляционная функция";
	}

	public String description() {
		return "Данный тест предназначен для оценки корреляции между сдвинутыми копиями исследуемой последовательности.";
	}

	public void reset() {

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
