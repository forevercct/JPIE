package uitox.eric.ch7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericExercise {
	private static final Logger logger = LoggerFactory
			.getLogger(GenericExercise.class);

	public static void main(String[] args) {
		// addOneTest();
		// updateListTest();
		mapListTest();
	}

	public static List<Integer> addOne(final List<Integer> numbers) {
		final ArrayList<Integer> toReturn = new ArrayList<Integer>();

		for (final Integer number : numbers) {
			toReturn.add(number + 1);
		}

		return toReturn;
	}

	public static void addOneTest() {
		final List<Integer> source = Arrays.asList(1, 2, 3, 4);

		logger.info("The result of checked functions: {}",
				Arrays.toString(addOne(source).toArray()));
	}

	public static List<Integer> updateList(final List<Integer> numbers,
			final IntegerOperation op) {
		final ArrayList<Integer> toReturn = new ArrayList<Integer>(
				numbers.size());

		for (final Integer number : numbers) {
			toReturn.add(op.performOperation(number));
		}

		return toReturn;
	}

	public static void updateListTest() {
		final List<Integer> numbers = Arrays.asList(4, 7, 2, -2, 8, -5, -7);

		final List<Integer> actual = updateList(numbers,
				new IntegerOperation() {

					public Integer performOperation(Integer value) {
						return Math.abs(value);
					}
				});

		logger.info("The result of checked functions: {}",
				Arrays.toString(actual.toArray()));
	}

	public static <A, B> List<B> mapList(final List<A> values,
			final GenericOperation<A, B> op) {
		final ArrayList<B> toReturn = new ArrayList<B>(values.size());

		for (final A a : values) {
			toReturn.add(op.performOperation(a));
		}

		return toReturn;
	}

	public static void mapListTest() {
		final List<String> strings = Arrays.asList("acing", "the", "java",
				"interview");

		final List<Integer> actual = mapList(strings,
				new GenericOperation<String, Integer>() {

					public Integer performOperation(String value) {

						return value.length();
					}

				});

		logger.info("The result of checked functions: {}",
				Arrays.toString(actual.toArray()));
	}
}
