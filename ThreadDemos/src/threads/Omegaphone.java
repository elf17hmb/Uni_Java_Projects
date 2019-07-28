package threads;

public class Omegaphone {
	public synchronized void printText(String greeting) {
		System.out.println(greeting);
	}
}
