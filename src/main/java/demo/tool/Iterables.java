package demo.tool;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

//Utile pour les sets et lists, mais pas le map
public class Iterables {

	private Iterables() {
	    throw new IllegalStateException("Utility class");
	}

	public static <E> void forEach(Iterable<? extends E> elements, BiConsumer<Integer, ? super E> action) {
		Objects.requireNonNull(elements);
		Objects.requireNonNull(action);

		int index = 0;
		for (E element : elements) {
			action.accept(index++, element);
		}
	}

	public static <K, V> void forEach(Map<? extends K, ? extends V> elements,
			BiConsumer<Integer, ? super Map.Entry<? extends K, ? extends V>> action) {
		Objects.requireNonNull(elements);
		Objects.requireNonNull(action);

		int index = 0;
		for (Map.Entry<? extends K, ? extends V> entry : elements.entrySet()) {
			action.accept(index++, entry);
		}
	}
}
