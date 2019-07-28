package Praktikum01;

public class Start {

	public static void main(String[] args) {
		Calculator c1 = new Calculator("2+3*4/2");
		Calculator c2 = new Calculator("2+3*4/2-1*9");
		Calculator c3 = new Calculator("+2+3*4/2*0/5+1");
		System.out.println(c1.calculate());
		System.out.println(c2.calculate());
		System.out.println(c3.calculate());
	}

}
