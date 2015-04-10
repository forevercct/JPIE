package uitox.eric.ch5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.TreeMap;

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
		// queueInsertion();
		// overwriteKey();
		treeMapTraversal();
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

	public static void overwriteKey() {
		final Map<String, String> preferences = new HashMap<String, String>();
		preferences.put("like", "Barry");
		preferences.put("dislike", "snake");

		assertEquals("Barry", preferences.get("like"));

		preferences.put("like", "Hero");

		assertEquals("Hero", preferences.get("like"));

	}

	public static void treeMapTraversal() {
		final Map<Integer, String> counts = new TreeMap<Integer, String>();
		counts.put(4, "four");
		counts.put(1, "one");
		counts.put(3, "three");
		counts.put(2, "two");

		final Iterator<Integer> Keys = counts.keySet().iterator();
		
		assertEquals(Integer.valueOf(2), Keys.next());

//		while (Keys.hasNext()) {
//			String integer = (String) Keys.next();
//			System.out.print(integer + " ");
//		}

	}

}
