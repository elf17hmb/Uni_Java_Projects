package Praktikum8;

public class Person {
	public int income;

	public Person(int income) {
		this.income = income;
	}

	public int calculateTaxableIncome() {
		return income;
	}

	public int calculateTax() {
		double taxableinc = calculateTaxableIncome();
		// int tax = (int) Math.round(taxableinc * 0.25);
		int tax = (int) (taxableinc * 0.25);
		return tax;
	}

	public int getIncome() {
		return income;
	}

}
