package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;



public class HuffmanEncoding {

	public static void main(String[] args) throws InsufficientBitsLeftException, IOException {
		// TODO Auto-generated method stub
		encode();

	}
	public static void encode() throws InsufficientBitsLeftException, IOException {

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
		
			
			PriorityQueue<encodeNode> pq = new PriorityQueue<encodeNode>(256, new encodeNodeComparator());
	        //traverse elements of ArrayList object  
			//System.out.println(totalSymbols.size());
			int k = 0;
	        while(k < 256){  
	           
	            //pq.add(new(en))
	            int sym = totalSymbols.get(k);
	        	int count = totalSymbolsC.get(k);
	        	 //System.out.println("Symbol and Count is: " + sym + ", " + count);
	        	encodeNode encodeN = new encodeNode(k,count);
	        	
	        	pq.add(encodeN);
	        	//System.out.println(pq.size());
	            k++;
	            
	        } 
	        int j = 0;
	        while(pq.size() > 1) {
	        	encodeNode min1 = pq.remove();
	        	encodeNode min2 = pq.remove();
	        	encodeNode parentN = new encodeNode(min1, min2);
	        	pq.add(parentN);
	        }
	        encodeNode rootE = pq.peek();

	        
	      
			
	 
	        //for(int i = 0; i < 256; i++) {
	        //	System.out.println(codeLengthArray[i]);
	       // }
	        ArrayList<Integer> sortedKeys = new ArrayList<Integer>();
			HashMap<Integer,ArrayList<Integer>> mapI = new HashMap<Integer,ArrayList<Integer>>();
		     codeLength(sortedKeys, mapI, rootE, 0);
			CTree canoTree = new CTree();
			
			
			
			Collections.sort(sortedKeys);
			
			for(int keys : sortedKeys) {
				Collections.sort(mapI.get(keys));
			}
			canoTree.createTree(sortedKeys,mapI);
			
			
			
			
			String arrayS[] = new String[256];
			encodedBits(canoTree.root, arrayS, "");
			OutputStream output = new FileOutputStream("encoded-text1.txt");
			OutputStreamBitSink outStream = new OutputStreamBitSink(output);
			for(int i = 0; i < 256; i++) {
				outStream.write( arrayS[i].length(),8);
			}
			outStream.write(size, 32);
			InputStream is2 = null;

			try {
				is2 = new FileInputStream("data/decoded-text.txt");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// now wrap this input stream with the one given
			
			for(int i = 0; i < size; i++) {
				
				outStream.write(arrayS[is2.read()]);
				
			}
			outStream.padToWord();
			
	        //sort in order from bits, then ascii value
	        //create cannoincal tree
	      
	        
		//create minHeap
	        
	        //order in terms of probablity
	        //new node that is connected to the sum of the two probablity
	        //insert all the nodes into prioirty queue and mininum is
	        //deque two at a time
		
	}//end of encode method
	 public static void codeLength(ArrayList<Integer> sortedKeys, HashMap<Integer,ArrayList<Integer>> mapI , encodeNode root, int length) {
     	if(root.isLeaf()) {
     		int i = root.value;
     		if(mapI.containsKey(length) == false) {
				ArrayList<Integer> lengthTable = new ArrayList<Integer>();
				lengthTable.add(i);
			    mapI.put(length, lengthTable);
			    sortedKeys.add(length);
			    return;
			}
			//otherwise it already contains this amount of bits so you must insert into the existing key value, add into the arraylist
			else if(mapI.containsKey(length) == true) {
				ArrayList<Integer> tempTable = new ArrayList<Integer>();
				//replacing old arrayList with the key, with a new arrayList here
				tempTable = mapI.get(length);
				tempTable.add(i);
				mapI.remove(length);
				mapI.put(length, tempTable);
				return;
			}
     	}
     	else {
     		codeLength(sortedKeys, mapI,root.left, length +1);
     	
     		codeLength(sortedKeys, mapI,root.right, length +1);
     	}
     }
	/*public static void findCodewordLengths(encodeNode node, HashMap<Integer, ArrayList<Integer>> codewordLengths, ArrayList<Integer> lengths, int depth) {
		if(node.value != -1) {
			if(codewordLengths.containsKey(depth)) {
				codewordLengths.get(depth).add(node.value);
			} else {
				codewordLengths.put(depth, new ArrayList<Integer>());
				codewordLengths.get(depth).add(node.value);
				lengths.add(depth);
			}
		} else {
			findCodewordLengths(node.left, codewordLengths, lengths, depth + 1);
			findCodewordLengths(node.right, codewordLengths, lengths, depth + 1);
		}
	}*/
	public static void encodedBits(Node root, String arrayS[], String codeWord) {
		if(root.value != -1) {
			arrayS[root.value] = codeWord;
			return;
		}
		encodedBits(root.left, arrayS, codeWord + "0");
		encodedBits(root.right, arrayS, codeWord + "1");
	}

}
