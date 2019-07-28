package third;

public class JoinExample {

	static class JoinerThread extends Thread {
		public int result;

		@Override
		public void run() {
			System.out.println("run");
			result = 1;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		JoinerThread t = new JoinerThread();
		t.start();
		// try {
		// t.join();
		// } catch (InterruptedException e) {
		// // ...
		// }
		Thread.sleep(1000);
		System.out.println(t.result);
	}
}
