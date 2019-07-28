package threads_Teil2_Aufgabe2;

import java.util.Random;

public class Reciever extends Thread {
	Sender sender;
	int spNr;

	public Reciever(Sender sender, int spNr) {
		this.sender = sender;
		this.spNr = spNr;
	}

	@Override
	public void run() {
		synchronized (sender) {
			while (true) {
				Random r = new Random();
				int v = r.nextInt(3);
				String ergeb = null;
				switch (v) {
					case 0 :
						ergeb = "Schere!";
						break;
					case 1 :
						ergeb = "Stein!";
						break;
					case 2 :
						ergeb = "Papier!";
						break;

				}
				System.out.println(
						"Spielernummer: " + spNr + " Ergebnis: " + ergeb);
				sender.ref(spNr, ergeb);
				try {
					sender.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
}
