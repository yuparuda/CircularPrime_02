package com.casian3.prime;

import java.util.ArrayList;

public class List_1_3_7_9 {
	static final int[] ELEMENTS = { 1, 3, 7, 9 };
	private final ArrayList<Integer> resultList;
	private final int limit;

	// using the recursion
	// ex [ Fist Input : 1,3,7,9 ] -> [ Output : 11, 13, 17, 19, 31,,,]
	int[] initFinalResultBy(int[] currentList) {
		int[] newList = new int[currentList.length * ELEMENTS.length];
		int base = 0;
		int i = 0;
		
		for (int c : currentList) {
			base = 10 * c;
			
			for (int e : ELEMENTS) {
				newList[i] = base + e;
				if (newList[i] > this.limit) { // bad code maybe
					return null;
				}
				resultList.add(newList[i++]);
			}
		}
		return initFinalResultBy(newList);
	}

	public ArrayList<Integer> getListUntilUpper() {
		initFinalResultBy(ELEMENTS);
		return resultList;
	}

	// ex limit : 1000000;
	List_1_3_7_9(int limit) {
		this.limit = limit;
		resultList = new ArrayList<>(this.limit); // to improve this limit
		for (int e : ELEMENTS) {
			resultList.add(e);
		}
	}
}
