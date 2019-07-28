package second;

public class Counter implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			System.out.println("Counter: " + i);
			try {
				Thread.sleep(500);
				System.out.println("is Interrupted = "
						+ Thread.currentThread().isInterrupted());
				Thread.currentThread().interrupt();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				Thread.currentThread().interrupt();
			}
			Thread.yield();
		}
	}

}
