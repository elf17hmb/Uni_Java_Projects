package lambdaexercise;

import java.util.ArrayList;
import java.util.List;

public class Start {
	int x = 1;

	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(5);
		numbers.add(4);
		// Iterator iter = numbers.iterator();
		//
		// while (iter.hasNext()) {
		//
		// Integer elem = (Integer) iter.next();
		//
		// System.out.println(elem);
		//
		// }

		// Utilities.forEach(numbers, new Consumer<Integer>() {
		// @Override
		// public void accept(Integer elem) {
		// System.out.println(elem);
		// }
		// });

		// Utilities.forEach(numbers, e -> System.out.println(e));
		// Utilities.forEach(numbers, System.out::println);
		numbers.forEach(System.out::println);
		Runnable r1 = () -> System.out.println("Hello World Two!");
		r1.run();
		Sam_uebung y = (int x) -> x + 1;
		System.out.println(y.calc(10));
	}

}
