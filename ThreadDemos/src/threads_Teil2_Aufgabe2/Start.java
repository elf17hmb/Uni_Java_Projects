package threads_Teil2_Aufgabe2;

public class Start {

	public static void main(String[] args) {
		Sender sender = new Sender();
		Reciever r1 = new Reciever(sender, 1);
		Reciever r2 = new Reciever(sender, 2);
		r1.start();
		r2.start();
		sender.start();
	}

}
