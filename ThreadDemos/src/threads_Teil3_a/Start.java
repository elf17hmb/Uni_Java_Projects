package threads_Teil3_a;

public class Start {

	public static void main(String[] args) {
		Car c1 = new Car("VW", 1400);
		Car c2 = new Car("KAMAZ", 100);
		Car c3 = new Car("Monster-Truck", 560);
		Car c4 = new Car("Mercedes-Benz", 900);
		Car c5 = new Car("LadaKalina", 1000);
		Car c6 = new Car("Audi", 3100);
		Car c7 = new Car("bla", 1200);
		Car c8 = new Car("blabla", 5000);

		Thread t1 = new Thread(c1);
		Thread t2 = new Thread(c2);
		Thread t3 = new Thread(c3);
		Thread t4 = new Thread(c4);
		Thread t5 = new Thread(c5);
		Thread t6 = new Thread(c6);
		Thread t7 = new Thread(c7);
		Thread t8 = new Thread(c8);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();

	}

}
