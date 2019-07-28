package lambdaexercise;

import java.util.Iterator;

public class Utilities {

	public static <E> void forEach(Iterable<E> seq, Consumer<E> block) {

		Iterator<E> iter = seq.iterator();

		while (iter.hasNext()) {

			E elem = iter.next();

			block.accept(elem);

		}

	}

}
