package modules;

public class ModuleFactory {

	public static QCModule [] getStandardModuleList () {
		
		QCModule [] module_list = new QCModule [] {
				new BasicStats(),
				//new PerBaseSequenceContent(),
				//new NContent(),
				//new PerSequenceQualityScores(),
				new BarGraph(),
				new Series(),
				new IncDec(),
			};
	
		return (module_list);
	}
	
}