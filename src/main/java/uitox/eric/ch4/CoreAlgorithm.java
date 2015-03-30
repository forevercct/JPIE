package uitox.eric.ch4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class CoreAlgorithm {
	private static final Logger logger = LoggerFactory
			.getLogger(CoreAlgorithm.class);

	public static void main(String[] args) {
		// sortInts();
		// sortObjects();
		// sortNotComparable();
		// customSorting();
		bubbleSortImpl();
	}

	@Test
	public static void sortInts() {
		final int[] numbers = { -3, -5, 1, 7, 4, -2 };
		final int[] expected = { -5, -3, -2, 1, 4, 7 };

		Arrays.sort(numbers);
		logger.info("After sorting: {}", Arrays.toString(numbers));
		assertArrayEquals(expected, numbers);
	}

	@Test
	public static void sortObjects() {
		final String[] strings = { "z", "x", "y", "abc", "zzz", "zazzy" };

		final String[] expected = { "abc", "x", "y", "z", "zazzy", "zzz" };

		Arrays.sort(strings);
		logger.info("After sorting: {}", Arrays.toString(strings));
		assertArrayEquals(expected, strings);
	}

	private static class NotComparable {
		private int i;

		private NotComparable(final int i) {
			this.i = i;
		}
	}

	@Test
	public static void sortNotComparable() {
		final List<NotComparable> objects = new ArrayList<NotComparable>();
		for (int i = 0; i < 10; i++) {
			objects.add(new NotComparable(i));
		}

		try {
			Arrays.sort(objects.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}

		fail();
	}

	// comparator is used to give exact control over the ordering
	public static class ReverseNumericalOrder implements Comparator<Integer> {

		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}

	}

	@Test
	public static void customSorting() {
		final List<Integer> numbers = Arrays.asList(4, 7, 1, 6, 3, 5, 4);
		final List<Integer> expected = Arrays.asList(7, 6, 5, 4, 4, 3, 1);

		Collections.sort(numbers, new ReverseNumericalOrder());
		logger.info("After sorting: {}", Arrays.toString(numbers.toArray()));
		assertEquals(expected, numbers);
	}

	public static void bubbleSortImpl() {
		final int[] numbers = { -3, -5, 1, 7, 4, -2 };
		bubbleSort(numbers);
		logger.info("After sorting: {}", Arrays.toString(numbers));
	}

	public static void bubbleSort(int[] numbers) {
		boolean numberSwitched;

		do {
			numberSwitched = false;

			for (int i = 0; i < numbers.length - 1; i++) {
				if (numbers[i + 1] < numbers[i]) {
					int temp = numbers[i + 1];
					numbers[i + 1] = numbers[i];
					numbers[i] = temp;
					numberSwitched = true;
				}
			}

		} while (numberSwitched);
	}
}
