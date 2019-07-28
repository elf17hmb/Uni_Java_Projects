package Praktikum05;

import java.util.ArrayList;
import java.util.List;

public class Start {

	public static void main(String[] args) {
		List<Integer> A = new ArrayList<>();
		Quicksort qs = new Quicksort();
		qs.populateList(A);
		qs.quicksort(A, 0, A.size() - 1);
		System.out.println(A);
	}

}
