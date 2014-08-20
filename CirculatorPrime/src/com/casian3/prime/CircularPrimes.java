package com.casian3.prime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class CircularPrimes {
	static void viewProcessingResult(ArrayList<Integer> target, Date start, Date stop) {
		System.out.println("  [ Circ Prime Count ] " + target.size());
		long processingTime = stop.getTime() - start.getTime();
		System.out
				.println("   [ PROCESSING TIME ] " + processingTime + " msec");
		System.out.println("            [ Detail ] ");
		int i = 1;
		Collections.sort(target);
		for (int t : target) {
			System.out.println(String.format("%5d", i++) + " : "
					+ String.format("%8d", t));
		}
	}

	// ex [ Input(target field) : 1234 ] -> [ Output : 4123 ]
	static int turnToRight(final int target, final int magnification) {
		return (target % 10) * magnification + (target / 10);
	}

	// suspect			: 1234
	// digitOfSuspect	:    4
	// magnification		: 1000
	static boolean isCircPrime(int suspect, ArrayList<Integer> nPrimes) {
		final int digitOfSuspect = String.valueOf(suspect).length();
		// to turn to right the target number
		final int magnification = (int) Math.pow(10, digitOfSuspect - 1);
		for (int i = 0; i < digitOfSuspect; ++i) {
			if (!Primes.isPrime(suspect, nPrimes)) {
				return false;
			}
			suspect = turnToRight(suspect, magnification);
		}
		return true;
	}

	static ArrayList<Integer> findOutCircPrimesUntil(int upper) {
		ArrayList<Integer> nCircPrimes = new ArrayList<>();
		nCircPrimes.add(2); // 　Special circular prime number
		nCircPrimes.add(5); // 　Special circular prime number

		// 1: generate pure primes (2, 3, 5, 7,,,) until square root of upper
		int sqrtOfUpperLimit = (int) Math.sqrt(upper) + 10; // 10 is the buffer
		ArrayList<Integer> nPurePrimes = Primes
				.generatePrimesBetween1And(sqrtOfUpperLimit);

		// 2: generate a List that is consisted of 1,3,7,9 only
		ArrayList<Integer> list_1_3_7_9 = new List_1_3_7_9(upper).getListUntilUpper();
		list_1_3_7_9.remove(0); // remove {1} , because a number "1" is Not the
								// prime.

		// 3: find out all circular primes using "Pure Primes" and
		// "1,3,7,9 list"
		for (Integer i : list_1_3_7_9) {
			if (isCircPrime(i, nPurePrimes)) {
				nCircPrimes.add(i);
			}
		}
		return nCircPrimes;
	}

	public static void main(String[] args) {
		final int upperLimit = 1000000; // One million
		System.out.println("       [ Upper Limit ] " + upperLimit);

		Date startTime = new Date();
		ArrayList<Integer> nCircPrimes = CircularPrimes
				.findOutCircPrimesUntil(upperLimit);
		Date stopTime = new Date();

		viewProcessingResult(nCircPrimes, startTime, stopTime);
	}
}
