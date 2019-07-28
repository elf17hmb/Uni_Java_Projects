package threads;

public class RunIt implements Runnable {

	@Override
	public void run() {
		int i = 1;
		while (i > 0) {
			if (i % 2 == 0) {
				System.out.println("läuft noch");
			}
			i++;

		}

	}

}
