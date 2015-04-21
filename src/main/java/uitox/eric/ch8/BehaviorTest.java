package uitox.eric.ch8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

	@Test
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
	
	@Test
	public void usePushAllA() {
		final ArrayList<A> list = new ArrayList<A>();
		for (int i = 0; i < 10; i++) {
			list.add(new A());
		}
		
		final Stack<A> stack = pushAllA(list);
		//logger.info(stack.pop().toString());
	}
	
	@Test
	public void usePushAllAWithBs() {
		final ArrayList<B> list = new ArrayList<B>();
		for (int i = 0; i < 10; i++) {
			list.add(new B());
		}
		
		final Stack<A> stack = pushAllA(list);
		logger.info(stack.pop().toString());
	}
}
