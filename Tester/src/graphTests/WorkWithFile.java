package graphTests;

import java.io.*;

public class WorkWithFile {
	
	public static void  write(String fileName, String text){
		File file = new File(fileName);
		
		try{
			if(!file.exists()){
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(file.getAbsoluteFile());
			try{
				out.print(text);
			} finally { out.close();}
		} catch (IOException e){
			throw new RuntimeException(e);
		}
	}
	
	public static String read(String fileName) throws FileNotFoundException {
		String s = "";
		exists(fileName);
	
		try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")){
			int b;
			while((b = raf.read()) != -1){
				s = s + (char)b;
			}
		}
		catch(IOException е) {
			System.out.println("Error input-output: " + е);
		}
		
		return s;
	}
	
	public static void exists(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		if (!file.exists()){
			throw new  FileNotFoundException (file.getName());
		}
	}
}