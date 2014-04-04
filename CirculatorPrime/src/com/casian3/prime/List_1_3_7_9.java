package com.casian3.prime;

import java.util.ArrayList;

// Number made ​​up of 1, 3, 7, 9 only
public class List_1_3_7_9 {
	static final int[] ELEMENTS = { 1, 3, 7, 9 };
	private final ArrayList<Integer> final_result;
	private final int limit;

	// recursion
	// ex [ Fist Input : 1,3,7,9 ] -> [ Output : 11, 13, 17, 19, 31,,,]
	int[] generate(int[] current) {
		int[] result = new int[current.length * ELEMENTS.length];

		int base = 0;
		int i = 0;
		for (int c : current) {
			base = 10 * c;

			for (int e : ELEMENTS) {
				result[i] = base + e;
				if (result[i] > this.limit) { // bad code maybe
					return null;
				}
				final_result.add(result[i++]);
			}
		}
		return generate(result);
	}

	public ArrayList<Integer> get() {
		generate(ELEMENTS);
		return final_result;
	}

	// limit : 1000000;
	List_1_3_7_9(int limit) {
		this.limit = limit;
		final_result = new ArrayList<>(this.limit); // bad
		for (int e : ELEMENTS) {
			final_result.add(e);
		}
	}
}
