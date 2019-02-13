package io;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.io.*;


public class HuffmanDecoding {
	public static void main(String[] args) {
		try {
			decode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// end of static void main
	public static void decode() throws IOException {
		CTree canoTree = new CTree();
		ArrayList<Integer> sortedKeys = new ArrayList<Integer>();
		HashMap<Integer,ArrayList<Integer>> map = new HashMap<Integer,ArrayList<Integer>>();
		// create Input Stream
		InputStream is = null;

		try {
			is = new FileInputStream("data/encoded-text1.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// now wrap this input stream with the one given
		InputStreamBitSource inputStream = new InputStreamBitSource(is);
		InputStreamBitSource inputStream2 = new InputStreamBitSource(is);
		
		
		
		for(int i = 0; i < 256; i++) {
			int lengTemp = 0;
			try {
				 lengTemp = inputStream.next(8);
			} catch (InsufficientBitsLeftException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//if the hashmap does not contain the length of the bits read, you will insert
			if(map.containsKey(lengTemp) == false) {
				ArrayList<Integer> lengthTable = new ArrayList<Integer>();
				int ascii = i;
				char cTemp=(char)ascii;
				lengthTable.add(i);
			    map.put(lengTemp, lengthTable);
			    sortedKeys.add(lengTemp);
			}
			//otherwise it already contains this amount of bits so you must insert into the existing key value, add into the arraylist
			else if(map.containsKey(lengTemp) == true) {
				ArrayList<Integer> tempTable = new ArrayList<Integer>();
				//replacing old arrayList with the key, with a new arrayList here
				tempTable = map.get(lengTemp);
				int ascii = i;
				char cTemp=(char)ascii;
				tempTable.add(i);
				map.remove(lengTemp);
				map.put(lengTemp, tempTable);
			}
			
		}//end of for loop for going through 255 bytes
		//sort Key lengths
		
		/*for (Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
	        System.out.print("Length of Bit " + entry.getKey()+" | ");
	        for(int charLength : entry.getValue()){
	            System.out.print(charLength+" ");
	        }
	        System.out.println();
	    }*/
		
		//now insert into canonical tree
		

			
		canoTree.createTree(sortedKeys,map);
			
			
		int numSymb = 0;
		try {
			numSymb = inputStream.next(32);
		} catch (InsufficientBitsLeftException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//now decode the tree
		Node rootT; 
		int i = 0;
		OutputStream output = new FileOutputStream("decoded-text.txt");
		OutputStreamBitSink outStream = new OutputStreamBitSink(output);
		while(i < numSymb) {
			rootT = canoTree.root;
			
			while(rootT != null) {
				int lengTemp = -1;
				try {
					 lengTemp = inputStream.next(1);
					 
					 			} catch (InsufficientBitsLeftException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(lengTemp == 0) {
					rootT = rootT.left;
					
				}
				else if(lengTemp == 1){
					rootT = rootT.right;

				}
			
				if(rootT.isLeaf()) {
					char cTemp=(char)rootT.value;
					int value2 = rootT.value;
					//System.out.print(cTemp);
					outStream.write(value2, 8);
					
					break;
				}
			
					
			}
			i++;
			

			
		}//end of for loop for going through 255 bytes

		
	}//end of decode method
}
