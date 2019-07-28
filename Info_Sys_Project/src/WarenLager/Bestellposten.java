package WarenLager;

public class Bestellposten {
	private final String artikelID;
	private final int anzahl;

	public Bestellposten(String artikelID, int anzahl) {
		this.artikelID = artikelID;
		this.anzahl = anzahl;
	}

	public int getAnzahl() {
		return anzahl;
	}

	public String getArtikelID() {
		return artikelID;
	}

}
