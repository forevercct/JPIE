package uitox.eric.ch6.strategy;

public class ConsoleLogging implements Logging {

	public void write(final String message) {
		System.out.println(message);
	}
	
}
