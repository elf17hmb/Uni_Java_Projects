/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package third;

/**
 *
 * @author wo
 */
public class MyJoinExample extends Thread {

	private String firstName;
	private String secondName;
	private long aWhile;
	private long total;

	public MyJoinExample(String firstName, String secondName, long delay) {
		this.firstName = firstName;
		this.secondName = secondName;
		aWhile = delay;
		// setDaemon(true);
	}
	@Override
	public void run() {
		try {
			while (total < 1000) {
				System.out.print(firstName);
				Thread.sleep(aWhile);
				total += aWhile;
				System.out.print(secondName + "\n");
			}
			System.out.print(secondName + " fertig.\n");
		} catch (InterruptedException e) {
			System.out.println(firstName + secondName + e);
		}
	}

	public static void main(String[] args) {
		Thread first = new MyJoinExample("A ", "a ", 200L);
		Thread second = new MyJoinExample("B ", "b ", 300L);
		Thread third = new MyJoinExample("C ", "c ", 500L);
		first.start();
		second.start();
		third.start();
		// try { //Entkommentieren
		System.out.println("Warte nun auf ersten Thread !");
		// first.join();
		// second.join();
		// third.join();
		System.out.println("Endlich fertig !");
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// finally
		{
			System.out.println("Und nun ist Schluss !");
		}
		System.out.println("RAUS !");
	}
}
