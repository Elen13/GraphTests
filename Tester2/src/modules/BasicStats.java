package modules;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.xml.stream.XMLStreamException;

import report.HTMLReportArchive;
import sequence.Sequence;

public class BasicStats extends AbstractQCModule {

	private String name = null;
	private long actualCount = 0;
	private int minLength = 0;
	private int maxLength = 0;
	private double fileLength = 0;
	@SuppressWarnings("unused")
	private long nCount = 0;
	private char lowestChar = 126;
	private String fileType = null;
	
	public String description() {
		return "Сбор общей статистики о последовательности";
	}
	
	public boolean ignoreFilteredSequences() {
		return false;
	}

	public JPanel getResultsPanel() {
		JPanel returnPanel = new JPanel();
		returnPanel.setLayout(new BorderLayout());
		returnPanel.add(new JLabel("Основная статистика последовательности",JLabel.CENTER),BorderLayout.NORTH);
		
		TableModel model = new ResultsTable();
		returnPanel.add(new JScrollPane(new JTable(model)),BorderLayout.CENTER);
		
		return returnPanel;
	
	}
	
	public void reset () {
		minLength = 0;
		maxLength = 0;
		nCount = 0;
		fileLength = 0;
	}

	public String name() {
		return "Общая статистика";
	}

	public void processSequence(Sequence sequence) {

		if (name == null) name = sequence.file().name();
		
		// If this is a filtered sequence we simply count it and move on.
		/*if (sequence.isFiltered()) {
			filteredCount++;
			return;
		}*/
		
		actualCount++;
		
		if (fileType == null) {
			if (sequence.getColorspace() != null) {
				fileType = "Colorspace converted to bases";
			}
			else {
				fileType = "Conventional base calls";
			}
		}
		
		fileLength = sequence.getFileLength();
		if (actualCount == 1) {
			minLength = sequence.getSequence().length();
			maxLength = sequence.getSequence().length();
		}
		else {
			if (sequence.getSequence().length() < minLength) minLength = sequence.getSequence().length();
			if (sequence.getSequence().length() > maxLength) maxLength = sequence.getSequence().length();
		}

		char [] chars = sequence.getSequence().toCharArray();
		for (int c=0;c<chars.length;c++) {			
			switch (chars[c]) {
				case 'N': ++nCount;break;			
			}
		}
		
		/*chars = sequence.getQualityString().toCharArray();
		for (int c=0;c<chars.length;c++) {
			if (chars[c] < lowestChar) {
				lowestChar = chars[c];
			}
		}*/
	}
	
	public boolean raisesError() {
		return false;
	}

	public boolean raisesWarning() {
		return false;
	}
	
	public boolean ignoreInReport () {
		return false;
	}

	public void makeReport(HTMLReportArchive report) throws XMLStreamException,IOException {
		super.writeTable(report, new ResultsTable());
	}

	@SuppressWarnings("serial")
	private class ResultsTable extends AbstractTableModel {
				
		private String [] rowNames = new String [] {
				"Имя файла",
				/*"File type",
				"Encoding",
				"Total Sequences",
				"Sequences flagged as poor quality",*/
				"Длина последовательности",
				//"%GC",
		};		
		
		// Sequence - Count - Percentage
		public int getColumnCount() {
			return 2;
		}
	
		public int getRowCount() {
			return rowNames.length;
		}
	
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
				case 0: return rowNames[rowIndex];
				case 1:
					switch (rowIndex) {
					case 0 : return name;
					/*case 1 : return fileType;
					case 2 : return PhredEncoding.getFastQEncodingOffset(lowestChar);
					case 3 : return ""+actualCount;
					case 4 : return ""+filteredCount;*/
					case 1 :
						return fileLength + "Б";
						/*if (minLength == maxLength) {
							return ""+minLength;
						}
						else {
							return minLength+"-"+maxLength;
						}*/
						
						
					/*case 6 : 
						if (aCount+tCount+gCount+cCount > 0) {
							return ""+(((gCount+cCount)*100)/(aCount+tCount+gCount+cCount));
						}
						else {
							return 0;
						}*/
					
					}
			}
			return null;
		}
		
		public String getColumnName (int columnIndex) {
			switch (columnIndex) {
				case 0: return "Измерение";
				case 1: return "Значение";
			}
			return null;
		}
		
		public Class<?> getColumnClass (int columnIndex) {
			switch (columnIndex) {
			case 0: return String.class;
			case 1: return String.class;
		}
		return null;
			
		}
	}
	

}
