package third;

public class ThreadLocalExample {

	public static class MyRunnable implements Runnable {

		private ThreadLocal<Integer> threadLocal = // vertauscht
				new ThreadLocal<Integer>();

		// MyInt threadLocal = new MyInt(); //vertauscht

		@Override
		public void run() {
			threadLocal.set((int) (Math.random() * 100D));

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}

			System.out.println(threadLocal.get());
		}
	}

	public static class MyInt {

		int value;

		public int get() {
			return value;
		}

		public void set(int value) {
			this.value = value;
		}

	}

	public static void main(String[] args) throws InterruptedException {
		MyRunnable sharedRunnableInstance = new MyRunnable();

		Thread thread1 = new Thread(sharedRunnableInstance);
		Thread thread2 = new Thread(sharedRunnableInstance);

		thread1.start();
		thread2.start();

		thread1.join(); // wait for thread 1 to terminate
		thread2.join(); // wait for thread 2 to terminate
	}

}
