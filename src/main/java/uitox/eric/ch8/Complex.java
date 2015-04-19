package uitox.eric.ch8;

import org.junit.Test;

import static org.junit.Assert.*;

public class Complex {
	private final double real;
	private final double imaginary;

	public Complex(final double real, final double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	public Complex add(final Complex other) {
		return new Complex(this.real + other.real, this.imaginary
				+ other.imaginary);
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		Complex complex = (Complex) o;
		if (Double.compare(this.real, complex.real) != 0)
			return false;

		if (Double.compare(this.imaginary, complex.imaginary) != 0)
			return false;

		return true;
	}

	@Test
	public void complexNumberAddition() {
		final Complex expected = new Complex(6, 2);

		final Complex a = new Complex(8, 0);
		final Complex b = new Complex(-2, 2);

		assertEquals(a.add(b), expected);
	}
}
