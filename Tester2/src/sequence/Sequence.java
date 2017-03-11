package sequence;

public class Sequence {

	private String sequence;
	private String quality;
	private String id;
	private SequenceFile file;
	private String colorspace;
	private boolean isFiltered;
	
	public Sequence (SequenceFile file,String sequence, String quality, String id) {
		this.id = id;
		this.file = file;
		this.sequence = sequence.toUpperCase();
		this.quality = quality;
		this.colorspace = null;
		this.isFiltered = false;
	}
	
	public Sequence (SequenceFile file,String sequence, String colorspace, String quality, String id) {
		this.id = id;
		this.file = file;
		this.sequence = sequence;
		this.quality = quality;
		this.colorspace = colorspace;
	}
	
	public void setIsFiltered (boolean isFiltered) {
		this.isFiltered = isFiltered;
	}
	
	public boolean isFiltered () {
		return isFiltered;
	}
	
	public SequenceFile file () {
		return file;
	}
	
	public String getSequence () {
		return sequence;
	}
	
	public String getColorspace () {
		return colorspace;
	}
	
	public String getQualityString () {
		return quality;
	}
	
	public String getID () {
		return id;
	}
	
}