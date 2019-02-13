package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class EntropyCal {
	public static void main(String[] args) throws IOException, InsufficientBitsLeftException {
		// Answers to numbers 2 and 3:
		
		ArrayList<Integer> totalSymbols = new ArrayList<Integer>(256);
		ArrayList<Integer> totalSymbolsC = new ArrayList<Integer>(256);
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		// create Input Stream
		InputStream is = null;

		try {
			is = new FileInputStream("data/decoded-text.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// now wrap this input stream with the one given
		InputStreamBitSource inputStream = new InputStreamBitSource(is);
		int size = is.available();
		
		int currentSymb = 0;
		for(int i = 0; i < 256; i++) {
			totalSymbolsC.add(i, 0);
			totalSymbols.add(i,i);
		}
		for(int i = 0; i < size; i++){
			try {
				currentSymb = inputStream.next(8);
				int countT = totalSymbolsC.get(currentSymb);
				totalSymbolsC.remove(currentSymb);
				totalSymbolsC.add(currentSymb, countT+1);
				
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("-----------------------Question 2-------------------");
		float theoreticalEntropy = 0;
		for(int i = 0; i < 256; i++) {
			 double prob = (totalSymbolsC.get(i) * 1.0f) / (size);
			System.out.println("The probability of " + (char)(i) + " or ASCII" + i + " is "+ prob + "%");
			double initEntropy =  (prob * (-1 * Math.log(prob)) / Math.log(2));
			if(initEntropy > 0) {
			theoreticalEntropy += initEntropy;
			}
			
		}
		System.out.println("-----------------------The end of question 2-------------------");
		
		
		
	
		System.out.println("-----------------------Question 3-------------------");
		System.out.println("The theoretical entropy is " + theoreticalEntropy);

		
		
		System.out.println("-----------------------The end of question 3-------------------");
		
		System.out.println("-----------------------Question 4-------------------");
		
		
		InputStream is2 = null;

		try {
			is2 = new FileInputStream("data/compressed.dat");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int size2 = is2.available();
		// now wrap this input stream with the one given
		InputStreamBitSource inputStream2 = new InputStreamBitSource(is2);
		double compressedEntropy = 0;
		for(int i = 0; i < 256; i++) {
			int lengTemp = 0;
			double prob = (totalSymbolsC.get(i) * 1.0f) / (size);
			try {
				 lengTemp = inputStream2.next(8);
			} catch (InsufficientBitsLeftException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double initCompressed = (prob * lengTemp);
			compressedEntropy += initCompressed;
		}
		
		System.out.println("The compressed entropy is " + compressedEntropy);
		System.out.println("-----------------------The end of question 4-------------------");
		
		System.out.println("-----------------------Question 6-------------------");
		InputStream is3 = null;

		try {
			is3 = new FileInputStream("data/encoded-text1.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// now wrap this input stream with the one given
		InputStreamBitSource inputStream3 = new InputStreamBitSource(is3);
		double compressedEntropy1 = 0;
		for(int i = 0; i < 256; i++) {
			int lengTemp = 0;
			double prob = (totalSymbolsC.get(i) * 1.0f) / (size);
			try {
				 lengTemp = inputStream3.next(8);
			} catch (InsufficientBitsLeftException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double initCompressed1 = (prob * lengTemp);
			compressedEntropy1 += initCompressed1;
		}
		System.out.println("The compressed entropy is " + compressedEntropy1);
		System.out.println("Look at word doc for answers");
	}
	}