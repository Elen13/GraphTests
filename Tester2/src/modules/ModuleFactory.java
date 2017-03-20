package modules;

public class ModuleFactory {

	public static QCModule [] getStandardModuleList () {
		
		QCModule [] module_list = new QCModule [] {
				new BasicStats(),
				//new PerBaseSequenceContent(),
				//new NContent(),
				//new PerSequenceQualityScores(),
				new BarGraph(),
				new Distribution(),
				new Series(),
				new IncDec(),
				new ACF(),
				new Spectral(),
				new LinearComplexity(),
			};
	
		return (module_list);
	}
	
}