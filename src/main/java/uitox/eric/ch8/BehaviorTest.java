package uitox.eric.ch8;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class BehaviorTest {
	private static final Logger logger = LoggerFactory
			.getLogger(BehaviorTest.class);

	public void wrongSetBehavior() {
		final Set<Person> people = new HashSet<Person>();
		final Person person1 = new Person("Alice", 28);
		final Person person2 = new Person("Bob", 30);
		final Person person3 = new Person("Charlie", 22);

		final boolean person1Added = people.add(person1);
		final boolean person2Added = people.add(person2);
		final boolean person3Added = people.add(person3);

		assertTrue(person1Added && person2Added && person3Added);

		// logically equal to person1
		final Person person1Again = new Person("Alice", 28);
		// should return false, as Alice is already in the set
		final boolean person1AgainAdded = people.add(person1Again);
		// But will return true as the equals method has not been implemented
		assertFalse(person1AgainAdded);
		assertEquals(3, people.size());
	}

	public void arraysAsObjects() {
		Map<String, Object> mapping = new HashMap<String, Object>();
		mapping.put("key", new int[] { 0, 1, 2, 3, 4, 5 });

		assertTrue(mapping.get("key") instanceof int[]);
	}

	public void stringChanges() {
		final String greeting = "Good Morning, Dave";
		final String substring = greeting.substring(4);

		assertTrue(substring.equals(" Morning, Dave"));
		assertFalse(greeting.equals(substring));
	}

	public void intEquality() {
		final Integer int1 = Integer.valueOf(new String("128"));
		final Integer int2 = Integer.valueOf(new String("128"));

		assertTrue(int1 == int2);
	}

	public static Stack<A> pushAllA(final List<? extends A> listOfA) {
		final Stack<A> stack = new Stack<A>();
		for (A a : listOfA) {
			stack.push(a);
		}

		return stack;
	}

	public void usePushAllA() {
		final ArrayList<A> list = new ArrayList<A>();
		for (int i = 0; i < 10; i++) {
			list.add(new A());
		}

		final Stack<A> stack = pushAllA(list);
		// logger.info(stack.pop().toString());
	}

	public void usePushAllAWithBs() {
		final ArrayList<B> list = new ArrayList<B>();
		for (int i = 0; i < 10; i++) {
			list.add(new B());
		}

		final Stack<A> stack = pushAllA(list);
		logger.info(stack.pop().toString());
	}

	public void mutateBookRecordState() throws NoSuchFieldException,
			IllegalAccessException {
		final BookRecord record = new BookRecord("Suzanne Collins",
				"The Hunger Games");

		final Field author = record.getClass().getDeclaredField("author");
		author.setAccessible(true);
		author.set(record, "Catching Fire");
		logger.info("The author is {}", record.getAuthor());
	}

	@Test
	public void showLinkedHashmapProperties() {
		final LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();

		linkedHashMap.put(10, "ten");
		linkedHashMap.put(20, "twenty");
		linkedHashMap.put(30, "thirty");
		linkedHashMap.put(40, "forty");
		linkedHashMap.put(50, "fifty");

		assertEquals("fifty", linkedHashMap.get(50));

		final Iterator<Integer> keyIterator = linkedHashMap.keySet().iterator();
		assertEquals("ten", linkedHashMap.get(keyIterator.next()));
		assertEquals("twenty", linkedHashMap.get(keyIterator.next()));
		assertEquals("thirty", linkedHashMap.get(keyIterator.next()));
		assertEquals("forty", linkedHashMap.get(keyIterator.next()));
		assertEquals("fifty", linkedHashMap.get(keyIterator.next()));

		final HashMap<Integer, String> regularHashMap = new HashMap<Integer, String>();

		regularHashMap.put(10, "ten");
		regularHashMap.put(20, "twenty");
		regularHashMap.put(30, "thirty");
		regularHashMap.put(40, "forty");
		regularHashMap.put(50, "fifty");

		final ArrayList<String> hashMapValues = new ArrayList<String>(
				regularHashMap.values());
		final ArrayList<String> linkedHashMapValues = new ArrayList<String>(
				linkedHashMap.values());

		logger.info("hashMapValues: {}",
				Arrays.toString(hashMapValues.toArray()));
		logger.info("linkedHashMapValues: {}",
				Arrays.toString(linkedHashMapValues.toArray()));

	}
	
//	@Test
//	public void run() {
//		List<Integer> ints = new ArrayList<Integer>();
//		ints.add(1);
//		ints.add(2);
//		ints.add(3);
//
//		List<Integer> newInts = new ArrayList<Integer>();
//		ints.forEach(i -> {
//			newInts.add(i + 10);
//		});
//
//		for (int i = 0; i < ints.size(); i++) {
//			assert ints.get(i) == newInts.get(i);
//		}
//
//		assert ints.size() == newInts.size();
//
//		System.out.println("Validated");
//	}
}
