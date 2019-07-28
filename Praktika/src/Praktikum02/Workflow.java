package Praktikum02;

public class Workflow {

	public static void main(String[] args) {
		Studentenregister stureg = new Studentenregister();
		System.out.println(stureg.finde("Meier", "Max", "23.04.1994").getNachname());
		stureg.finde("Vladimir", "Putin", "01.08.1998");
	}

}
