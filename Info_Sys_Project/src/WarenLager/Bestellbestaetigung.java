package WarenLager;

public class Bestellbestaetigung {
	private final boolean ausgefuehrt;
	private final double gesamtpreis;

	public Bestellbestaetigung(boolean ausgefuehrt, double gesamtpreis) {
		this.ausgefuehrt = ausgefuehrt;
		this.gesamtpreis = gesamtpreis;
	}

}
