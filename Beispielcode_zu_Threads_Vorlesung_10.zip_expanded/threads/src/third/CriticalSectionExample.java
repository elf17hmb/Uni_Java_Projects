package third;

public class CriticalSectionExample {

	static int counter;

	static Runnable r = new Runnable() {
		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				// int c = counter;
				// c++;
				// counter = c;
				counter++;
			}
		}
	};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(counter); // Ausgabe nicht immer 2000
	}

}
