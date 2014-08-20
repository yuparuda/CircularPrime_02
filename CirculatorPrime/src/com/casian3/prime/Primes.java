package com.casian3.prime;

import java.util.ArrayList;

public class Primes {
	static boolean isPrime(final int suspect, final ArrayList<Integer> found_primes) {
		final double sprt_of_suspect = Math.sqrt(suspect);
		for (int i = 0; found_primes.get(i) <= sprt_of_suspect; ++i) {
			if (suspect % found_primes.get(i) == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static ArrayList<Integer> generatePrimesBetween1And(final int upper) {
		final ArrayList<Integer> primes = new ArrayList<>(upper / 2);
		primes.add(2); // add a special prime.
		for (int i = 3; i < upper; i += 2) {
			if (isPrime(i, primes)) {
				primes.add(i);
			}
		}
		return primes;
	}
}
