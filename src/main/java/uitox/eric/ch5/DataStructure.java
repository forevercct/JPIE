package uitox.eric.ch5;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class DataStructure {
	private static final Logger logger = LoggerFactory
			.getLogger(DataStructure.class);

	public static void main(String[] args) {
		// arrayDefinitions();
		// arrayCopy();
		queueInsertion();
	}

	@Test
	public static void arrayDefinitions() {
		final int[] integers = new int[3];
		final Random r = new Random();
		final String[] randomArrayLength = new String[r.nextInt(100)];

		logger.info("The lenght of randomArrayLength is {}",
				randomArrayLength.length);
	}

	@Test
	public static void arrayCopy() {
		int[] integers = { 0, 1, 2, 3, 4 };

		int[] newIntegersArray = new int[integers.length + 1];
		System.arraycopy(integers, 0, newIntegersArray, 0, integers.length);
		integers = newIntegersArray;
		integers[5] = 5;

		logger.info("The elements of integers array are {}",
				Arrays.toString(integers));

		assertEquals(5, integers[5]);
	}

	@Test
	public static void queueInsertion() {
		final Queue<String> queue = new LinkedList<String>();
		queue.add("first");
		queue.add("second");
		queue.add("third");

		logger.info("The elements of queue  are {}",
				Arrays.toString(queue.toArray()));

		assertEquals("first", queue.remove());
		assertEquals("second", queue.remove());
		assertEquals("third", queue.peek());
		assertEquals("third", queue.remove());
	}

}
