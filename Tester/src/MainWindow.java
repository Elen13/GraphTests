import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import graphTests.*;


public class MainWindow {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//WorkWithFile.write("a","Where an I?");
		Seqence sq = new Seqence();
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		System.out.println("Write a file name");
		Scanner in = new Scanner(System.in);
		String c = in.nextLine();
		String source = WorkWithFile.read(c);
		System.out.println(source);
		
		System.out.println("Write a part of seqence");
		double partSeq = in.nextDouble();
		
		System.out.println("Write a begin symbol");
		int startChar = in.nextInt();
		System.out.println("Write a count symbols");
		int countChar = in.nextInt();
		
		System.out.println("Write a capacity");
		int capacity = in.nextInt();
		
		System.out.println(sq.partSq(partSeq, startChar-1, countChar, source));
		list = sq.binToDec(sq.partSq(partSeq, startChar-1, countChar, source), capacity);
		System.out.println(list);
		
		BarGraph.show(list, capacity);
		IncDec.show(list, capacity);
		
		in.close();

	}

}