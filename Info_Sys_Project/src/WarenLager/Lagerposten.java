package WarenLager;

public class Lagerposten {
	private int lagerbestand;
	private double preis;
	private Artikel artikel;

	public Lagerposten(Artikel artikel, int lagerbestand, double preis) {
		this.lagerbestand = lagerbestand;
		this.preis = preis;
		this.artikel = artikel;
	}

	public Artikel getLagerArtikel() {
		return artikel;
	}

	public int getLagerBestand() {
		return lagerbestand;
	}

	public void setLagerBestand(int lagerbestand) {
		this.lagerbestand = lagerbestand;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

}
