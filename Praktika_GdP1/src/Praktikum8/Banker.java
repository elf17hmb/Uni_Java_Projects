package Praktikum8;

public class Banker extends Person {

	public Banker(int income) {
		super(income);
	}

	@Override
	public int calculateTax() {
		int taxableinc = calculateTaxableIncome();
		int tax = (int) Math.round(taxableinc * 0.25) + 1000;
		if (tax < income) {
			return tax;
		} else
			return income;
	}
}
