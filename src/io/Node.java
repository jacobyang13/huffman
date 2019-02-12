package io;

public class Node {
	public Node left;
	public Node right;
	public int value;
	
	public Node(int val) {
		this.value = val;
	}
	public Node() {
		value = -1;

	}
	
	//if the value is -1 then you return -1
	public boolean isLeaf() {
		if(this.value == -1) {
			return false;
		}
		return true;
	}
	public boolean isFull() {
		
		if(this.isLeaf()) {
			return true;
		}
		if(this.left == null || this.right == null) {
			return false;
		}
		if(this.left.isFull() && this.right.isFull()) {
			return true;
		}
		return false;
		 
	}
	/*
	 */
	//if is leaf
	//left.null and & right = null
	//left.isfull && right.isFull
}
