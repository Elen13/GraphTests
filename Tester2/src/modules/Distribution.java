package modules;

import graphs.DistributionPlot;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.xml.stream.XMLStreamException;

import report.HTMLReportArchive;
import sequence.Sequence;

public class Distribution extends AbstractQCModule{

	public int [][] data;
	public int fieldSize = 0;

	public void processSequence(Sequence sequence) {
		
		int cap = 3;
		ArrayList<Integer> sq = BarGraph.binToDec(sequence.getSequence(), cap);
		int [][] coordinates = new int [sq.size()-1][2];
		int n = 1;
		
		for(int i = 0; i < sq.size()-1; i++){
			n--;
			for(int j = 0; j < 2; j++){
				coordinates[i][j] = sq.get(n);
				n++;
			}
		}
		data = coordinates.clone();
		fieldSize = (int)Math.pow(2, cap);
	}

	public JPanel getResultsPanel() {
		return new DistributionPlot(data, fieldSize,  "Распределение на плоскости");
	}

	public String name() {
		return "Распределение на плоскости";
	}

	public String description() {
		return "Данный тест предназначен для определения зависимостей между элементами исследуемой последовательности.";
	}

	public void reset() {
		data = new int [0][0];
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
