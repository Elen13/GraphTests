import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import graphTests.*;
import javax.swing.*;

import draw.*;

public class MainWindow {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws FileNotFoundException 
	 */
	
	public static void main(String[] args) {//throws FileNotFoundException {//throws InterruptedException {//
		// TODO Auto-generated method stub
		//WorkWithFile.write("a","Where an I?");
		SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new MainMenu();			
            }
        });
		
		
		/*System.out.println("Hello World!");
		DrawPicture fr = new DrawPicture();
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setLocationRelativeTo(null);
		//fr.setLayout(new GridBagLayout());
		
		JButton b = new JButton("Run");	
		JTextField text = new JTextField(10);
		JLabel l = new JLabel("Test1"); 
		JProgressBar pr = new JProgressBar();
		JPanel pl1 = new JPanel();
	
		fr.setLayout(new FlowLayout());
		
		fr.add(b);
		fr.add(text);
	
		fr.setVisible(true);*/
		
		
		
		
		/*Seqence sq = new Seqence();
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		System.out.println("Write a file name");
		Scanner in = new Scanner(System.in);
		String c = in.nextLine();
		String source = WorkWithFile.read(c);
		System.out.println(source);
		
		System.out.println("Write a part of seqence");
		double partSeq = 1;//in.nextDouble();
		
		System.out.println("Write a begin symbol");
		int startChar = 1;//in.nextInt();
		System.out.println("Write a count symbols");
		int countChar = source.length();//in.nextInt();
		
		System.out.println("Write a capacity");
		int capacity = in.nextInt();
		
		System.out.println("Write a series");
		int series = capacity;//in.nextInt();
		
		System.out.println(sq.partSq(partSeq, startChar-1, countChar, source));
		list = sq.binToDec(sq.partSq(partSeq, startChar-1, countChar, source), capacity);
		System.out.println(list);
		
		//BarGraph.show(list, capacity);
		//nuOKIncDec.show(list, capacity);
		//Series.sqToSeries(sq.partSq(partSeq, startChar-1, countChar, source), capacity);
		//OK ACF.bit(ACF.normViewBit(list, capacity));
		//OK ACF.bit(ACF.normViewChar(list, capacity));
		//Distribution.test2(list);
		//OK Series.test3(list, capacity, series);
		//OK SpectralTest.test6(list, capacity);
		//OK LinearComplexity.test7(list, capacity);
		
		in.close();

		
		SwingUtilities.invokeLater(new Runnable(){	//сохранение картинки в png
            public void run(){
                new SavePicture();				
            }
        });*/
	}

}


