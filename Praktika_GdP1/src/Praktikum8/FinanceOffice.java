package Praktikum8;

public class FinanceOffice {

	public static void main(String[] args) {
		// Code ggf. zuerst auskommentieren, damit das Projekt kompiliert werden kann
		Person[] persons = new Person[5];
		Person joeUnemployed = new Person(6400);
		persons[0] = joeUnemployed;
		Worker suziHardworking = new Worker(36000);
		persons[1] = suziHardworking;
		Banker fredMoneymaker = new Banker(4000000);
		persons[2] = fredMoneymaker;
		Queen elisabeth = new Queen(1000000);
		persons[3] = elisabeth;
		Banker billyBadLuck = new Banker(500);
		persons[4] = billyBadLuck;

		FinanceOffice ukOffice = new FinanceOffice();
		System.out.println("Steuereinnahmen des Landes: " + ukOffice.calculateSumOfTaxes(persons) + " Pfund");
	}

	/**
	 * Calculates the sum of the taxes of given persons.
	 * 
	 * @param personArray
	 *            some persons
	 * @return the sum of all the taxes of the persons
	 */
	public int calculateSumOfTaxes(Person[] personArray) {
		int taxSum = 0;
		for (int i = 0; i < personArray.length; i++) {
			taxSum += personArray[i].calculateTax();
			System.out.println(personArray[i].calculateTax());
		}
		// for (Person p : personArray) {
		// taxSum += p.calculateTax();
		// System.out.println(p.calculateTax());
		// }
		return taxSum;
	}
}