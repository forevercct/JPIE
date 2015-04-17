package uitox.eric.ch7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class Iterators {

	public static void main(String[] args) {
		multipleIterators();
	}

	@Test
	public static void multipleIterators() {
		final Iterator<Integer> a = Arrays.asList(1, 2, 3, 4, 5).iterator();
		final Iterator<Integer> b = Arrays.asList(6).iterator();
		final Iterator<Integer> c = new ArrayList<Integer>().iterator();
		final Iterator<Integer> d = new ArrayList<Integer>().iterator();
		final Iterator<Integer> e = Arrays.asList(7, 8, 9).iterator();

		final Iterator<Integer> singleIterator = ListIterator
				.singleIterator(Arrays.asList(a, b, c, d, e));

		while (singleIterator.hasNext()) {
			System.out.print(singleIterator.next() + " ");
		}

	}

	public static class ListIterator<T> implements Iterator<T> {

		private final Iterator<Iterator<T>> listIterator;
		private Iterator<T> currentIterator;

		public ListIterator(List<Iterator<T>> iterators) {
			this.listIterator = iterators.iterator();
			this.currentIterator = listIterator.next();
		}

		public static <T> Iterator<T> singleIterator(
				final List<Iterator<T>> iteratList) {
			return new ListIterator<T>(iteratList);

		}

		public boolean hasNext() {
			if (!currentIterator.hasNext()) {
				if (!listIterator.hasNext()) {
					return false;
				}

				currentIterator = listIterator.next();
				hasNext();
			}

			return true;
		}

		public T next() {
			hasNext();

			return currentIterator.next();
		}

	}

}
