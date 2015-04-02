package uitox.eric.ch4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
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
		// bubbleSortImpl();
		// insertSortImpl();
		quickSortImpl();
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

	public static void insertSortImpl() {
		final Integer[] numbers = { -3, -5, 1, 7, 4, -2 };
		final List<Integer> results;
		results = insertSort(Arrays.asList(numbers));
		logger.info("After sorting: {}", Arrays.toString(results.toArray()));
	}

	public static List<Integer> insertSort(final List<Integer> numbers) {
		final List<Integer> sortedList = new LinkedList<Integer>();

		originalList: for (Integer number : numbers) {
			for (int i = 0; i < sortedList.size(); i++) {
				if (number < sortedList.get(i)) {
					sortedList.add(i, number);
					continue originalList;
				}
			}

			sortedList.add(sortedList.size(), number);
		}

		return sortedList;
	}

	public static void quickSortImpl() {
		final Integer[] numbers = { 24, 3, 85, 43, 20, 45, 67, 64, 34, 87 };
		final List<Integer> results;
		results = quickSort(Arrays.asList(numbers));
		logger.info("After sorting: {}", Arrays.toString(results.toArray()));
	}

	public static List<Integer> quickSort(final List<Integer> numbers) {
		if (numbers.size() < 2) {
			return numbers;
		}

		final Integer pivot = numbers.get(0);
		final List<Integer> lower = new ArrayList<Integer>();
		final List<Integer> higher = new ArrayList<Integer>();

		for (int i = 1; i < numbers.size(); i++) {
			if (numbers.get(i) < pivot) {
				lower.add(numbers.get(i));
			} else {
				higher.add(numbers.get(i));
			}
		}

		final List<Integer> sorted = quickSort(lower);
		sorted.add(pivot);
		sorted.addAll(quickSort(higher));

		return sorted;
	}
}
