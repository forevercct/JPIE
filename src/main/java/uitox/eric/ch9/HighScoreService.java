package uitox.eric.ch9;

import java.util.List;

public interface HighScoreService {
	List<String> getTopFivePlayers();
	boolean saveHighScore(int score, String playerName);
}
