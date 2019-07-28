package Praktikum05;

import java.util.Collections;
import java.util.List;
import java.util.Random;
public class Quicksort {

	public int partition(List<Integer> A, int p, int r) {
		int x = A.get(r);
		int i = p - 1;
		int j = p;
		while (j <= r - 1) {
			if (A.get(j) <= x) {
				i = i + 1;
				Collections.swap(A, i, j);
			}
			j++;
		}
		// System.out.println("Test: " + A.get(r));
		Collections.swap(A, i + 1, r);

		return i + 1;
	}
	public void quicksort(List<Integer> A, int p, int r) {
		if (p < r) {
			int q = partition(A, p, r);
			quicksort(A, p, q - 1);
			quicksort(A, q + 1, r);
		}
	}
	public void populateList(List<Integer> A) {
		Random rand = new Random();
		for (int i = 0; i < 1000; i++) {
			A.add(i, rand.nextInt(10000));
		}
	}
}
