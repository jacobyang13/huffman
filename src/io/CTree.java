package io;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CTree {
	public Node root;
	public CTree() {
		root = new Node();
	}
	public Node getRoot() {
		return this.root;
	}
	//insert into tree
	//recursion, go left and insert, if not then 
	//while loop or for loop based on the length of value
	//then left.is node == null
	//then right.is node == null
	
	
	public void insert(int value, int length) {
		Node temp;
		Node curN = this.root;
		int i = 0;
		while(i < length) {
			if(curN.left == null) {
				//System.out.println("curN.left is null");
				if(i == length -1) {
					//System.out.println("curN.setleft with value");
					temp = new Node(value);
					curN.left = temp;
					i++;
					break;
				}
			
				
				//System.out.println("curN.setleft with -1");
				temp = new Node();
				curN.left = temp;
				curN = curN.left;
				i++;
				continue;
			
			}
			if(curN.left.isFull()) {
				//System.out.println("curN.left.isFull");
				if(curN.right == null) {
					//System.out.println("curN.right is null");
					if(i == length -1) {
						//System.out.println("curN.setright with value");
						temp = new Node(value);
						curN.right = temp;
						i++;
						break;
					}
					
						//System.out.println("curN.setright with value");
						temp = new Node();
						curN.right = temp;
						
					
				}
				//System.out.println("curN.moveright");
				curN = curN.right;
				i++;
				continue;
			}//end of getleft.isfull
			//System.out.println("curN.moveleft at bottom");
			curN = curN.left;
			i++;
		}//end of while
		
	}//end of insert method
	
	public void createTree(ArrayList<Integer> sortedKeys, HashMap<Integer,ArrayList<Integer>> map) {

			Collections.sort(sortedKeys);
			for(int keyLength: sortedKeys) {
				ArrayList<Integer> temp = map.get(keyLength);
				//System.out.print("Length of Bit " + keyLength+" | ");
				 for(int charValue : temp){
			           // System.out.print(charValue+" ");
			            this.insert(charValue, keyLength);
			        }
			        //System.out.println();
			}//end of printing statement
			
			
	}
	
}
