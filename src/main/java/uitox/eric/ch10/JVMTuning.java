package uitox.eric.ch10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class JVMTuning {
	private static final Logger logger = LoggerFactory
			.getLogger(JVMTuning.class);

	@Test
	public void listReversals() {
		final List<Integer> givenList = Arrays.asList(1, 2, 3, 4, 5);
		logger.info(Arrays.toString(reverseRecursive(givenList).toArray()));
		logger.info(Arrays.toString(reverseIterative(givenList).toArray()));
	}

	@Test
	public void weakReferenceStackManipulation() {
		final WeakReferenceStack<ValueContainer> stack = new WeakReferenceStack<ValueContainer>();
		final ValueContainer expected = new ValueContainer(
				"Value for the stack");
		stack.push(new ValueContainer("Value for the stack"));

		ValueContainer peekedValue = stack.peek();
		assertEquals(expected, peekedValue);
		assertEquals(expected, stack.peek());
		peekedValue = null;
		System.gc();
		assertNull(stack.peek());

	}

	private List<Integer> reverseRecursive(List<Integer> list) {
		if (list.size() <= 1) {
			return list;
		} else {
			List<Integer> reversed = new ArrayList<Integer>();
			reversed.add(list.get(list.size() - 1));
			reversed.addAll(reverseRecursive(list.subList(0, list.size() - 1)));
			return reversed;
		}

	}

	private List<Integer> reverseIterative(List<Integer> list) {
		for (int i = 0; i < list.size() / 2; i++) {
			final int temp = list.get(i);
			list.set(i, list.get(list.size() - 1 - i));
			list.set(list.size() - 1 - i, temp);
		}

		return list;
	}

	@Test
	public void addShudownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.err.println("Shutting down JVM at time: " + new Date());
			}
		});
	}
}
