package Praktikum7_Uebungen;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Start implements Comparator<String> {
	List<String> hey = Arrays.asList("Hello", "Hi", "Ola", "Huhu", "Hola");
	final Predicate<String> isShortWord = str -> str.length() <= 3;
	final Predicate<String> containsH = str -> str.contains("H");

	public static void main(String[] args) {
		Start start = new Start();
		start.threeLetters();
		start.findH();
		start.getLength();
		start.filterWithPredicates();
		start.getEachLength();
		start.mapLengths();
	}

	public void threeLetters() {
		hey.stream()
				.filter(str -> str.length() >= 3)
				.sorted((o1, o2) -> compare(o1, o2))
				.collect(Collectors.toList()).forEach(System.out::println);
	}

	public void findH() {
		System.out.println();
		hey.stream()
				.filter(str -> str.contains("H"))
				.map(String::toUpperCase).forEach(System.out::println);
	}

	public void getLength() {
		System.out.println("\n" + hey.stream()
				.mapToInt(String::length)
				.sum());
	}

	public void filterWithPredicates() {
		System.out.println();
		hey.stream()
				.filter(isShortWord)
				.filter(containsH)
				.forEach(System.out::println);
	}

	public void getEachLength() {
		System.out.println("\n" +
				hey.stream()
						.map(String::length)
						.collect(Collectors.toList()));
	}

	public void mapLengths() {
		System.out.println("\n" + hey.stream()
				.collect(Collectors.groupingBy(String::length)));
	}

	@Override
	public int compare(String o1, String o2) {
		if (o1.charAt(2) > o2.charAt(2)) {
			return 1;
		}
		if (o1.charAt(2) < o2.charAt(2)) {
			return -1;
		}
		return 0;
	}

}
