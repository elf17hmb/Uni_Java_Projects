package threads;

public class Tier extends Thread {
	private String greeting;
	private final Object LOCK = new Object();
	Omegaphone phone;
	public Tier(String greeting, Omegaphone phone) {
		this.greeting = greeting;
		this.phone = phone;
	}

	@Override
	public void run() {
		phone.printText(greeting);
	}

}
