package io;

import java.util.Comparator;

public class encodeNodeComparator implements Comparator<encodeNode>{

	@Override
	public int compare(encodeNode o1, encodeNode o2) {
		// TODO Auto-generated method stub
		if(o1.height == o2.height) {
			if(o1.occurence == o2.occurence) {
			return 0;
			}
		}
		if(o1.height < o2.height) {
			if(o1.occurence == o2.occurence) {
			return -1;
			}
		}
		if(o1.height > o2.height) {
			if(o1.occurence == o2.occurence) {
			return 1;
			}
		}
		if(o1.occurence < o2.occurence) {
			return -1;
		}
		if(o1.occurence > o2.occurence) {
			return 1;
		}
		return 0;//shouldnt get to here
	}

}
