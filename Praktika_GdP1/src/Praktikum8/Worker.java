package Praktikum8;

public class Worker extends Person {

	public Worker(int income) {
		super(income);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int calculateTaxableIncome() {
		int taxableincome;
		if (income > 2400) {
			taxableincome = income - 2400;
			return taxableincome;
		} else {
			return 0;
		}
	}

}
