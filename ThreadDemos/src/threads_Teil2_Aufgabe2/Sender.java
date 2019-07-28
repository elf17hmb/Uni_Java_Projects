package threads_Teil2_Aufgabe2;

public class Sender extends Thread {
	String sp1, sp2;
	int spNr;
	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				entscheidung();
				System.out.println("---------------------------");
				this.notifyAll();

			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void ref(int spNr, String ergeb) {
		if (spNr == 1) {
			sp1 = ergeb;
		} else {
			sp2 = ergeb;
		}
	}

	public void entscheidung() {
		if (sp1 == sp2) {
			System.out.println("***Unentschieden!***");
		} else if (sp1.equals("Schere!") && sp2.equals("Papier!")
				|| sp1.equals("Stein!") && sp2.equals("Schere!")) {
			System.out.println("***Spieler    1    hat gewonnen!***");
		} else {
			System.out.println("***Spieler    2    hat gewonnen!***");
		}

	}
}
