package analysis;

import modules.QCModule;
import sequence.SequenceFile;

public interface AnalysisListener {

	public void analysisStarted(SequenceFile file);
	public void analysisUpdated(SequenceFile file, int sequencesProcessed, int percentComplete);
	public void analysisComplete(SequenceFile file, QCModule [] results);
	public void analysisExceptionReceived(SequenceFile file, Exception e);
}
