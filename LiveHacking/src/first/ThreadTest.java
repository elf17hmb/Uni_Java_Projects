package first;

public class ThreadTest {

	public static void main(String[] args) {
		Runnable threadJob = new MyRunnable();
		Thread myThread = new Thread(threadJob);

		myThread.start();

		try {
			myThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		System.out.println("zurück in Main"); // Wird als erster
												// geprintet(außer man versetzt
												// main ins Schlaf)
	}

}
