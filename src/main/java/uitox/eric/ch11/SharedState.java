package uitox.eric.ch11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import static org.junit.Assert.*;

public class SharedState {

	public void sharedState() throws InterruptedException {
		final ExecutorService executorService = Executors.newCachedThreadPool();
		final SimpleCounter c = new SimpleCounter();
		executorService.execute(new CounterSetter(c));

		// Thread.sleep(1000);

		synchronized (c) {
			c.setNumber(200);
			assertEquals(200, c.getNumber());
		}
	}

	@Test
	public void atomicSharedState() {
		final ExecutorService executorService = Executors.newCachedThreadPool();

		final AtomicCounter c = new AtomicCounter();
		executorService.execute(new CounterSetterAtomic(c));

		final int value = c.getNumber().incrementAndGet();
		assertEquals(1, value);
	}

	private static class CounterSetter implements Runnable {
		private final SimpleCounter counter;

		private CounterSetter(SimpleCounter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			while (true) {
				counter.setNumber(100);
			}

		}

	}

	private static class CounterSetterAtomic implements Runnable {
		private final AtomicCounter counter;

		private CounterSetterAtomic(AtomicCounter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			while (true) {
				counter.getNumber().set(0);
			}

		}

	}
}
