package third;

public class InterruptExample {

	public static void main(String[] args){
		Thread t = new Thread()
		{
			@Override
			public void run() {
				System.out.println("Thread am Start");
				
				while (!isInterrupted()) {
					System.out.println("Thread läuft");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						System.out.println("Schlaf unterbrochen");
//						interrupt();
					}
				}
				System.out.println("Thread unterbrochen und am Ende");
			}
		};
		t.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// Fehlerbehandlung
		}
		t.interrupt();
	}
}
