package uitox.eric.ch7;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PopularQuestions {
	private static final Logger logger = LoggerFactory
			.getLogger(PopularQuestions.class);

	private static Map<Integer, Integer> fibCache = new HashMap<Integer, Integer>();

	public static void main(String[] args) {
		// fizzBuzz(15);
		// alternativeFizzBuzz(16);
		// fibonacci(0);
		// logger.info("Fibonacci: {}", fibN(45));
		logger.info("The result of checked functions: {}",
				isPalindrome("Lev el"));
	}

	public static void fizzBuzz(final int n) {
		final List<String> toReturn = new ArrayList<String>(n);

		for (int i = 1; i <= n; i++) {
			if (i % 15 == 0) {
				toReturn.add("FizzBuzz");
			} else if (i % 3 == 0) {
				toReturn.add("Fizz");
			} else if (i % 5 == 0) {
				toReturn.add("Buzz");
			} else {
				toReturn.add(Integer.toString(i));
			}
		}

		logger.info("FizzBuzz: {}", Arrays.toString(toReturn.toArray()));
	}

	public static void alternativeFizzBuzz(final int n) {
		final List<String> toReturn = new ArrayList<String>(n);

		for (int i = 1; i <= n; i++) {
			final String word = toWord(i, 3, "Fizz") + toWord(i, 5, "Buzz");

			if (StringUtils.isEmpty(word)) {
				toReturn.add(Integer.toString(i));
			} else {
				toReturn.add(word);
			}

		}

		logger.info("FizzBuzz: {}", Arrays.toString(toReturn.toArray()));
	}

	private static String toWord(final int value, final int divisor,
			final String word) {
		return (value % divisor == 0) ? word : "";
	}

	public static List<Integer> fibonacci(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("n must not be less than zero");
		}

		if (n == 0) {
			return new ArrayList<Integer>();
		}

		if (n == 1) {
			return Arrays.asList(0);
		}

		if (n == 2) {
			return Arrays.asList(0, 1);
		}

		final List<Integer> seq = new ArrayList<Integer>(n);
		seq.add(0);
		n--;
		seq.add(1);
		n--;

		while (n > 0) {
			int a = seq.get(seq.size() - 1);
			int b = seq.get(seq.size() - 2);
			seq.add(a + b);
			n--;
		}

		return seq;
	}

	@Test(expected = IllegalArgumentException.class)
	public void fibonacciList() {
		assertEquals(0, fibonacci(0).size());
		assertEquals(Arrays.asList(0), fibonacci(1));
		assertEquals(Arrays.asList(0, 1), fibonacci(2));
		assertEquals(Arrays.asList(0, 1, 1), fibonacci(3));
		assertEquals(Arrays.asList(0, 1, 1, 2), fibonacci(4));
	}

	public static int fibN(final int n) {
		if (n < 0) {
			throw new IllegalArgumentException("n must not be less than zero");
		}

		if (n == 1)
			return 1;
		if (n == 0)
			return 0;

		return fibN(n - 1) + fibN(n - 2);
	}

	public static int cachedFibN(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("n must not be less than zero");
		}

		fibCache.put(0, 0);
		fibCache.put(1, 1);

		return recursiveCachedFibN(n);
	}

	private static int recursiveCachedFibN(int n) {
		if (fibCache.containsKey(n)) {
			return fibCache.get(n);
		}

		int value = recursiveCachedFibN(n - 1) + recursiveCachedFibN(n - 2);
		fibCache.put(n, value);

		return value;
	}

	@Test
	public void largeFib() {
		final long nonCachedStart = System.nanoTime();
		assertEquals(1134903170, fibN(45));
		final long nonCachedFinish = System.nanoTime();
		assertEquals(1134903170, cachedFibN(45));
		final long CachedFinish = System.nanoTime();

		logger.info("Non cached time: {} nanoseconds", nonCachedFinish
				- nonCachedStart);
		logger.info("cached time: {} nanoseconds", CachedFinish
				- nonCachedFinish);
	}

	public static long factorial(int n) {
		if (n < 1) {
			throw new IllegalArgumentException("n must be greater than zero");
		}

		int toReturn = 1;

		for (int i = 1; i <= n; i++) {
			toReturn *= i;
		}

		return toReturn;
	}

	public static String reverse(final String s) {
		final StringBuilder sb = new StringBuilder(s.length());

		for (int i = s.length() - 1; i >= 0; i--) {
			sb.append(s.charAt(i));
		}

		return sb.toString();
	}

	public static String inPlaceReverse(final String s) {
		final StringBuilder sb = new StringBuilder(s);

		for (int i = 0; i < sb.length() / 2; i++) {
			final char tmp = sb.charAt(i);
			final int otherEnd = sb.length() - 1 - i;
			sb.setCharAt(i, sb.charAt(otherEnd));
			sb.setCharAt(otherEnd, tmp);
		}

		return sb.toString();
	}

	public static boolean isPalindrome(final String s) {
		String toCheck = s.toLowerCase();
		int left = 0;
		int right = toCheck.length() - 1;

		while (left <= right) {

			while (left < toCheck.length()
					&& !Character.isLetter(s.charAt(left))) {
				left++;
			}

			while (right > 0 && !Character.isLetter(s.charAt(right))) {
				right--;
			}

			if (left > toCheck.length() || right < 0)
				return false;

			if (toCheck.charAt(left) != toCheck.charAt(right))
				return false;

			left++;
			right--;
		}

		return true;
	}
}
