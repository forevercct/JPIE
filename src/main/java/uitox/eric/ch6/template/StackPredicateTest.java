package uitox.eric.ch6.template;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class StackPredicateTest {
	private Stack stack;

	private static final Logger logger = LoggerFactory
			.getLogger(StackPredicateTest.class);

	@Before
	public void createStack() {
		stack = new Stack();
		for (int i = 1; i <= 10; i++) {
			stack.push(i);
		}
	}

	// @Test
	// public static void main(String[] args) {
	// final StackPredicateTest test = new StackPredicateTest();
	// test.createStack();
	// test.evenPredicate();
	// }

	@Test
	public void evenPredicate() {
		// anonymous inner class
		final Stack filtered = stack.filter(new StackPredicate() {

			public boolean isValid(int i) {

				return (i % 2 == 0);
			}
		});

		assertEquals(Integer.valueOf(10), filtered.remove());
		assertEquals(Integer.valueOf(8), filtered.remove());
		assertEquals(Integer.valueOf(6), filtered.remove());
	}

	@Test
	public void allPredicate() {
		final Stack filtered = stack.filter(new StackPredicate() {

			public boolean isValid(int i) {

				return true;
			}
		});

		assertEquals(Integer.valueOf(10), filtered.remove());
		assertEquals(Integer.valueOf(9), filtered.remove());
		assertEquals(Integer.valueOf(8), filtered.remove());
	}
}
