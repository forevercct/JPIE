package uitox.eric.ch9;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class HamcrestExample {
	
	@Test
	public void useHamcrest() {
		final Integer a = 400;
		final Integer b = 100;
		
		assertThat(a, is(notNullValue()));
		assertThat(a, is(equalTo(400)));
		assertThat(b-a, is(greaterThan(0)));
	}
}
