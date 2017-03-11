package fileFilters;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class SequenceFileFilter extends FileFilter {

	public boolean accept(File f) {
		if (f.isDirectory() 
				|| f.getName().toLowerCase().endsWith(".txt.gz") 
				|| f.getName().toLowerCase().endsWith(".fastq.gz") 
				|| f.getName().toLowerCase().endsWith(".fq.gz") 
				|| f.getName().toLowerCase().endsWith(".fq") 
				|| f.getName().toLowerCase().endsWith(".txt.bz2") 
				|| f.getName().toLowerCase().endsWith(".fastq.bz2")
				|| f.getName().toLowerCase().endsWith(".txt") 
				|| f.getName().toLowerCase().endsWith(".fastq") 
				|| f.getName().toLowerCase().endsWith(".bam") 
				|| f.getName().toLowerCase().endsWith(".sam")
				|| f.getName().toLowerCase().endsWith(".compact-reads")
				|| f.getName().toLowerCase().endsWith(".goby")
				|| f.getName().toLowerCase().endsWith(".bin")
				
		) {
			return true;
		}
		else {
			return false;
		}
	}

	public String getDescription() {
		return "Sequence Files";
	}

}