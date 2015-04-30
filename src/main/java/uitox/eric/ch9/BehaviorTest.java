package uitox.eric.ch9;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BehaviorTest {

	@Test
	public void assertionWithMessage() {
		final List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(1);

		assertTrue("The numbers list should not be empty", numbers.isEmpty());
	}

	
}
