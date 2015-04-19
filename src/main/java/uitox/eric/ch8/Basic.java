package uitox.eric.ch8;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class Basic {
	private static final Logger logger = LoggerFactory.getLogger(Basic.class);
	public static int BASIC_VALUE = 6;
	
	public static void main(String[] args) {
		Basic basic = new Basic();
		basic.absoluteOfMostNegativeValue();
	}
	
	public static void Upcasting() {
		int value = Integer.MAX_VALUE;
		long biggerValue = value + 1;
		logger.info("the max value of int: {}", value);
		logger.info("bigger value: {}", biggerValue);
	}

	public static void Downcasting() {
		long veryLargeNumber = Long.MAX_VALUE;
		int fromLargeNumber = (int) veryLargeNumber;

		logger.info("fromLargeNumber value: {}", fromLargeNumber);
	}

	
	public  void absoluteOfMostNegativeValue() {
		final int mostNegative = Integer.MIN_VALUE;
		final long negated = Math.abs(mostNegative);

		logger.info("negated value: {}", negated);
		System.out.println(getClass());
		assertFalse("No positive equivalent of Integer.MIN_VALUE", negated > 0);
	}

	@Test
	public void objectMemoryAssignment() {
		List<String> list1 = new ArrayList<String>();
		list1.add("entry in list1");

		assertTrue(list1.size() == 1);

		List<String> list2 = list1;
		list2.add("entry in list2");

		assertTrue(list2.size() == 2);
	}
	
	@Test
	public void staticVariableAccess() {
		assertEquals(Basic.BASIC_VALUE, 6);
	}
}
