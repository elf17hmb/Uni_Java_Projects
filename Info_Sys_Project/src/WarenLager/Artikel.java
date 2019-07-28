package WarenLager;

public class Artikel {
	private final String id;
	private String name;
	private String beschreibung;

	public Artikel(String id, String name, String beschreibung) {
		this.id = id;
		this.name = name;
		this.beschreibung = beschreibung;
	}

	public String getArtikelName() {
		return name;
	}

	public String getArtikelBeschreibung() {
		return beschreibung;
	}

	public String getArtikelId() {
		return id;
	}

}
