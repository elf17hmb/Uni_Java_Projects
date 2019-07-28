package threads;

public class Start {
	public static void main(String[] args) {
		// Aufgabe1
		// for (int i = 0; i < 100; i++) {
		// Thread t = new Demo100();
		// t.start();
		//
		// }

		// Runnable r = new RunIt();
		// Thread trunnable = new Thread(r);
		// trunnable.start();

		// Aufgabe 2
		// SayHello cat = new SayHello("nya nya");
		// SayHello dog = new SayHello("wuff wuff");
		//
		//
		// // for (int i = 0; i < 3; i++) {
		// // cat.printText();
		// // dog.printText();
		// // }
		//
		// // for (int i = 0; i < 3; i++) {
		// // cat.run();
		// // dog.run();
		// // }
		//
		// // cat.start();
		// // dog.start();
		// ThreadStarter ts = new ThreadStarter();
		// ts.startThread();
		//
		// Praktikum 9 -Teil2
		// Aufgabe 1
		Omegaphone lul = new Omegaphone();
		Tier cat = new Tier("nya nya", lul);
		Tier dog = new Tier("wuff wuff", lul);
		Tier donkey = new Tier("ye, yeye, yeye", lul);
		Tier cock = new Tier("kukareku", lul);
		cat.start();
		dog.start();
		donkey.start();
		cock.start();
	}
}
