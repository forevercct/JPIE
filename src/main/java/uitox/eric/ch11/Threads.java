package uitox.eric.ch11;

public class Threads {
	public static void main(String[] args) throws InterruptedException {
		Threads mainThread = new Threads();
		final Thread separateThread = new Thread(mainThread.new ThreadPrinter());
		separateThread.start();
		for (int i = 0; i < 5; i++) {
			System.err.println("From the main thread: "
					+ Thread.currentThread().getName());
			Thread.sleep(1000);
		}

	}

	public class ThreadPrinter implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				System.err.println("From the new thread: "
						+ Thread.currentThread().getName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}
}
