package first;

public class MyRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("auf die Plätze fertig los - run");
		los();

	}

	private void los() {
		System.out.println(" los gehts");
		tuNochMehr();
	}

	private void tuNochMehr() {
		System.out.println("oben auf dem Stack");
	}

}
