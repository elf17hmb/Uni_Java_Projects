package second;

public class SimpleThreadExample {

	public static void main(String[] args) {
		Thread t1 = new Thread(new AlphaCounter());
		Thread t2 = new Thread(new Counter());

		t1.start();
		t2.start();

		System.out
				.println("Aktueller Thread: " + Thread.currentThread().getId());
		System.out.println(
				"Name des aktuellen Thread: "
						+ Thread.currentThread().getName());

		Thread t3 = new BetaSayerThread();

		System.out.println("Zustand von " + t1.getName() + ":" + t1.getState());
		System.out.println("Zustand von " + t2.getName() + ":" + t2.getState());
		System.out.println("Zustand von " + t3.getName() + ":" + t3.getState());
	}
}
