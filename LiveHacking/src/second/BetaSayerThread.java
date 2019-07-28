package second;

public class BetaSayerThread extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			System.out.println("Beta: " + i);
			Thread.yield();
		}
	}
}
