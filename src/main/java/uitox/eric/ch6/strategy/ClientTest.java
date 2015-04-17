package uitox.eric.ch6.strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class ClientTest {
	private final Logging logging;

	public ClientTest(final Logging logging) {
		this.logging = logging;
	}

	public static void main(String[] args) throws IOException {
		useConsoleLogging();
//		useFileLogging();
	}

	public void doWork(final int count) {
		if (count % 2 == 0) {
			logging.write("Even number: " + count);
		}
	}

	@Test
	public static void useConsoleLogging() {
		final Logging mockLogging = new ConsoleLogging();
		final ClientTest clinet = new ClientTest(mockLogging);
		clinet.doWork(32);
		
	}

	public static void useFileLogging() throws IOException {
		final File tempFile = File.createTempFile("test", "log");
		final ClientTest clinet = new ClientTest(new FileLogging(tempFile));
		clinet.doWork(41);
		clinet.doWork(42);
		clinet.doWork(43);

		String currentLine;
		final BufferedReader reader = new BufferedReader(new FileReader(
				tempFile));
		
		while ((currentLine = reader.readLine()) != null) {
			System.out.println(currentLine + " ");
		}
	}
	
//	public static void useMockLogging() {
//		final Logging mockLogging = mock(Logging.class);
//	}
//
//	private static Logging mock(Class<Logging> logger) {
//		// TODO Auto-generated method stub
//		return logger.;
//	}

}
