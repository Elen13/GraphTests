package modules;


import java.io.IOException;

import javax.swing.JPanel;
import javax.xml.stream.XMLStreamException;

import report.HTMLReportArchive;
import sequence.Sequence;

public interface QCModule {

	public void processSequence(Sequence sequence);

	public JPanel getResultsPanel();
	
	public String name ();
	
	public String description ();
	
	public void reset ();
	
	public boolean raisesError();
	
	public boolean raisesWarning();
	
	public boolean ignoreFilteredSequences();
	
	/**
	 * Allows you to say that this module shouldn't be included in the final report.
	 * Useful for modules which have a use under some circumstances but not others.
	 * @return
	 */
	public boolean ignoreInReport();

	public void makeReport(HTMLReportArchive report) throws XMLStreamException,IOException;
	
	
}