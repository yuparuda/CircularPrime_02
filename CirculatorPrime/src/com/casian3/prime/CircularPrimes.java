package com.casian3.prime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class CircularPrimes {
	static void view(ArrayList<Integer> target, Date start, Date stop) {
		System.out.println("  [ Circ Prime Count ] " + target.size());
		long process_time = stop.getTime() - start.getTime();
		System.out.println("   [ PROCESSING TIME ] " + process_time + " msec");
		System.out.println("            [ Detail ] ");
		int i = 1;
		Collections.sort(target);
		for (int t : target) {
			System.out.println(
					String.format("%5d", i++) 
					+ " : " + String.format("%8d", t) );
		}
	}
	
	// Sample [ Input : 1234 ] -> [ Output : 4123 ]
	static int turnToRight(final int number, final int number_of_magnification) {
		return (number % 10) * number_of_magnification + (number / 10);
	}

	static boolean isCircPrime(int suspect, ArrayList<Integer> primes) {
		final int digits = String.valueOf(suspect).length();
		final int magnification = (int) Math.pow(10, digits - 1);
		for (int i = 0; i < digits; ++i) {
			if (!Primes.isPrime(suspect, primes)) {
				return false;
			}
			suspect = turnToRight(suspect, magnification);
		}
		return true;
	}

	static ArrayList<Integer> findOut(int upper) {
		ArrayList<Integer> result = new ArrayList<>();
		result.add(2);	// 　Special number
		result.add(5);	// 　Special number

		// 1: get the required prime
		int sqrt_upper_limit = (int) Math.sqrt(upper) + 10;
		ArrayList<Integer> primes = Primes.findOutBetween1And(sqrt_upper_limit);

		// 2:　get a list consist of 1,3,7,9
		ArrayList<Integer> list_1_3_7_9 = new List_1_3_7_9(upper).get();
		list_1_3_7_9.remove(0); // remove {1} ... because it is no need

		// 3: find out the circular prime
		for (Integer i : list_1_3_7_9) {
			if (isCircPrime(i, primes)) {
				result.add(i);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		final int upperLimit = 1000000; // One million
		System.out.println("       [ Upper Limit ] " + upperLimit);

		Date start_time = new Date();
		ArrayList<Integer> circPrimes = CircularPrimes.findOut(upperLimit);
		Date stop_time = new Date();
		
		view(circPrimes, start_time, stop_time);
	}
}
