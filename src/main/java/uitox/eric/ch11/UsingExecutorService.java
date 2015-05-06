package uitox.eric.ch11;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class UsingExecutorService {
	public static void main(String[] args) throws InterruptedException,
			ExecutionException, TimeoutException {
		
		final ExecutorService executorService = Executors.newCachedThreadPool();
		final long startTime = System.currentTimeMillis();
		final Future<Double> future = executorService
				.submit(new PiCalculator());
		final double pi = future.get(10, TimeUnit.SECONDS);

		final long stopTime = System.currentTimeMillis();
		System.out.printf("Calculated Pi in %d milliseconds: %10.9f%n",
				stopTime - startTime, pi);
		
		executorService.shutdown();
	}

	private static class PiCalculator implements Callable<Double> {

		@Override
		public Double call() throws Exception {
			double currVal = 1.0;
			double nextVal = 0.0;
			double denominator = 1.0;

			for (int i = 0; Math.abs(nextVal - currVal) > 0.000000001d; denominator += 2, i++) {
				currVal = nextVal;
				if (i % 2 == 1) {
					nextVal = currVal - (1 / denominator);
				} else {
					nextVal = currVal + (1 / denominator);
				}
				
//				System.out.println(i + ": currVale is " + currVal);
//				System.out.println(i + ": nextVal is " + nextVal);
			}

			return currVal * 4;
		}

	}
}
