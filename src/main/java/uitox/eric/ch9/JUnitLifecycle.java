package uitox.eric.ch9;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JUnitLifecycle {
	private static final Logger logger = LoggerFactory
			.getLogger(JUnitLifecycle.class);

	private static int counter = 0;

	@BeforeClass
	public static void suiteSetUp() {
		assertEquals(0, counter);
		counter++;
		logger.info("the value of the counter in @BeforeClass: {} ", counter);
	}

	public JUnitLifecycle() {
		counter++;
		logger.info("the value of the counter in constructor: {}", counter);
	}

	@Before
	public void prepareTest() {
		counter++;
		logger.info("the value of the counter in @Before: {} ", counter);
	}

	@Test
	public void performFirstTest() {
		counter++;
		logger.info("the value of the counter in @Test1: {} ", counter);
	}
	
	@Test
	public void performSecondTest() {
		counter++;
		logger.info("the value of the counter in @Test2: {} ", counter);
	}
	
	@After
	public void cleanupTest() {
		counter++;
		logger.info("the value of the counter in @After for cleaning up: {} ", counter);
	}
}
