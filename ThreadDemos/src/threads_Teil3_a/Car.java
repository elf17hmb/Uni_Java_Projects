package threads_Teil3_a;

import java.util.concurrent.Semaphore;

public class Car implements Runnable {
	String name;
	int gewicht;
	private static final Semaphore semaphore = new Semaphore(5000, false);

	public Car(String name, int gewicht) {
		this.name = name;
		this.gewicht = gewicht;
	}

	@Override
	public void run() {
		try {
			long startTime = System.currentTimeMillis();

			Thread.sleep((long) (Math.random() * 100));

			System.out.println("---------------------");
			System.out.println("Auto: " + this.name + " Gewicht: " + gewicht
					+ " Zeit vergangen (in ms): "
					+ (System.currentTimeMillis() - startTime) + " Anfahren");

			semaphore.acquire(gewicht);

			System.out.println("--------------------");
			System.out.println("Auto: " + this.name + " Gewicht: " + gewicht
					+ " Zeit vergangen (in ms): "
					+ (System.currentTimeMillis() - startTime) + " Überfahrt");

			Thread.sleep((long) (Math.random() * 100));

			System.out.println("--------------------");
			System.out.println("Auto: " + this.name + " Gewicht: " + gewicht
					+ " Zeit vergangen (in ms): "
					+ (System.currentTimeMillis() - startTime)
					+ " Verlassen der Brücke");

			semaphore.release(gewicht);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
