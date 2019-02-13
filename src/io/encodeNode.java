package io;

public class encodeNode {
	public encodeNode left;
	public encodeNode right;
	public int value;
	public int height;
	public int occurence;
	
	public encodeNode(int valueT, int occurenceT) {
		this.value = valueT;
		this.height = 0;
		this.occurence = occurenceT;
	}
	public encodeNode(encodeNode leftT, encodeNode rightT) {
		this.left = leftT;
		this.right = rightT;
		this.value = -1;
		this.occurence = leftT.occurence + rightT.occurence;
		this.height = Math.max(leftT.height, rightT.height) + 1;
	}
	public boolean isLeaf() {
		if(value == -1) {
			return false;
		}
		
		return true;
	}
}
