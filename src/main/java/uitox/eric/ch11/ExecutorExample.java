package uitox.eric.ch11;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ExecutorExample {

	public void executorExample() {
		final ExecutorService executor = Executors.newCachedThreadPool();
		final Runnable threadNamePrinter = new InfiniteThreadNamePrinter();

		System.out.println("Main thread: " + Thread.currentThread().getName());
		executor.execute(threadNamePrinter);

	}

	public void waitToComplete() throws InterruptedException {
		final ExecutorService executor = Executors.newCachedThreadPool();
		final CountDownLatch latch = new CountDownLatch(1);
		executor.execute(new FiniteThreadNamePrinterLatch(latch));
		latch.await(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void sameThread() {
		final Executor executor = new Executor() {

			@Override
			public void execute(Runnable command) {
				command.run();
			}
		};

		System.out.println("Run from thread: "
				+ Thread.currentThread().getName());
		executor.execute(new FiniteThreadNamePrinter());

	}

	private static class FiniteThreadNamePrinter implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 25; i++) {
				System.out.println("Run from thread: "
						+ Thread.currentThread().getName());
			}
		}
	}

	private static class InfiniteThreadNamePrinter implements Runnable {

		@Override
		public void run() {
			while (true) {
				System.out.println("Run from thread: "
						+ Thread.currentThread().getName());
			}
		}

	}

	private static class FiniteThreadNamePrinterLatch implements Runnable {
		final CountDownLatch latch;

		private FiniteThreadNamePrinterLatch(final CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {
			for (int i = 0; i < 25; i++) {
				System.out.println("Run from thread: "
						+ Thread.currentThread().getName());
			}

			latch.countDown();
		}

	}
}
