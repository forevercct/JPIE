package uitox.eric.ch9;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameTestWithMock {
	private static final Logger logger = LoggerFactory
			.getLogger(GameTestWithMock.class);

	private final Game gameUnderTest;
	private final HighScoreService mockHihgScoreService;

	public GameTestWithMock() {
		final List<String> firstHighScoreList = Arrays.asList("Alice", "Bob",
				"Charlie", "Dave", "Elizabeth");
		final List<String> secondHighScoreList = Arrays.asList("Fred",
				"Georgia", "Helen", "Ian", "Jane");

		this.mockHihgScoreService = mock(HighScoreService.class);

		Mockito.when(mockHihgScoreService.getTopFivePlayers())
				.thenReturn(firstHighScoreList).thenReturn(secondHighScoreList);

		this.gameUnderTest = new Game(mockHihgScoreService);
	}

	@Test
	public void highScoreDisplay() {
		final String firstExpectedPlayerList = "1.Alice" + "2.Bob"
				+ "3.Charlie" + "4.Dave" + "5.Elizabeth";

		final String secondExpectedPlayerList = "1. Fred\n" + "2. Georgia\n"
				+ "3. Helen\n" + "4. Ian\n" + "5. Jane\n";

		final String firstCall = gameUnderTest.displayHighScores();
		final String secondCall = gameUnderTest.displayHighScores();

		assertEquals(firstExpectedPlayerList, firstCall);
//		assertEquals(secondExpectedPlayerList, secondCall);

		logger.info(firstCall);
		logger.info(firstExpectedPlayerList);
		
		verify(mockHihgScoreService, times(2)).getTopFivePlayers();
	}
}
