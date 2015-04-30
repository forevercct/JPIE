package uitox.eric.ch9;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

@RunWith(RandomizedRunner.class)
public class TestWithParameters {
	private final int a;
	private final int b;
	private final int expectedAddition;
	private final int expectedSubtraction;
	private final int expectedMultiplication;
	private final int expectedDivision;

	public TestWithParameters(final int a, final int b,
			final int expectedAddition, final int expectedSubtraction,
			final int expectedMultiplication, final int expectedDivision) {
		this.a = a;
		this.b = b;
		this.expectedAddition = expectedAddition;
		this.expectedSubtraction = expectedSubtraction;
		this.expectedMultiplication = expectedMultiplication;
		this.expectedDivision = expectedDivision;
	}

	@Parameterized.Parameters
	public static List<Integer[]> parameters() {
		return new ListOfIntegerArraysForTest();
	}

	@Test
	public void addNumbers() {
		assertEquals(expectedAddition, a + b);
	}

	@Test
	public void subtractNumbers() {
		assertEquals(expectedSubtraction, a - b);
	}

	@Test
	public void multiplyNumbers() {
		assertEquals(expectedMultiplication, a * b);
	}

	@Test
	public void divideNumbers() {
		assertEquals(expectedDivision, a / b);
		
	}

}
