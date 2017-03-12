package sequence;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BinFile implements SequenceFile {

	private File file;
	private String name;
	private String seqence;
	
	public BinFile(File file) {
		this.file = file;
		name = file.getName();
		
		try (DataInputStream datain = new DataInputStream(new FileInputStream(file))){

			byte[] buffer = new byte[datain.available()];
			datain.read(buffer, 0, datain.available());
			seqence = toBinary(buffer);
			System.out.println("arr: " + seqence);
		}
		catch(IOException е) {
			System.out.println("Error input-output: " + е);
		}
	}
	
	public File getFile() {
		return file;
	}

	public String name() {
		return name;
	}

	static String toBinary( byte[] bytes ){
        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        return sb.toString();
    }
	
	static byte[] fromBinary( String s )
	{
	    int sLen = s.length();
	    byte[] toReturn = new byte[(sLen + Byte.SIZE - 1) / Byte.SIZE];
	    char c;
	    for( int i = 0; i < sLen; i++ )
	        if( (c = s.charAt(i)) == '1' )
	            toReturn[i / Byte.SIZE] = (byte) (toReturn[i / Byte.SIZE] | (0x80 >>> (i % Byte.SIZE)));
	        else if ( c != '0' )
	            throw new IllegalArgumentException();
	    return toReturn;
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Sequence next() throws SequenceFormatException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isColorspace() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPercentComplete() {
		// TODO Auto-generated method stub
		return 0;
	}

}
