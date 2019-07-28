package threads;

public class ThreadStarter {

	public void startThread() {
		Thread t = new Thread(new RunIt());
		t.setDaemon(true);
		t.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Main Methode fertig");

	}

}
