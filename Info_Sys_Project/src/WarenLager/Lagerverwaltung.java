package WarenLager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Lagerverwaltung {
	Set<String> berechtigteMitarbeiter = new HashSet<>();
	List<Lagerposten> lagerbestand = new ArrayList<>();

	public Lagerverwaltung() {

	}

	public void berechtigungErteilen(Mitarbeiter mitarbeiter) {
		berechtigteMitarbeiter.add(mitarbeiter.getId());
	}

	public void berechtigungZurueckziehen(Mitarbeiter mitarbeiter) {
		berechtigteMitarbeiter.remove(mitarbeiter.getId());
	}

	public void lagerbestandAusgeben() {
		for (Lagerposten lp : lagerbestand) {
			String artikelname = lp.getLagerArtikel().getArtikelName();
			String artikelbeschreibung = lp.getLagerArtikel().getArtikelBeschreibung();
			int bestand = lp.getLagerBestand();
			double preis = lp.getPreis();
			System.out.println("Artikel: " + artikelname + " Beschreibung: " + artikelbeschreibung + " Lagerbestand: "
					+ bestand + " Preis: " + preis);
		}
	}

	public void wareneingangBuchen(Mitarbeiter mitarbeiter, Artikel artikel, int bestand, double preis) {
		int aktuellerbestand;
		if (berechtigteMitarbeiter.contains(mitarbeiter.getId())) {
			Iterator<Lagerposten> iterator = lagerbestand.iterator();
			while (iterator.hasNext()) {
				Lagerposten lp = iterator.next();
				if (lp.getLagerArtikel().getArtikelId().equals(artikel.getArtikelId())) {
					aktuellerbestand = lp.getLagerBestand();
					bestand += aktuellerbestand;
					iterator.remove();
				}
			}
			lagerbestand.add(new Lagerposten(artikel, bestand, preis));
		} else {
			System.out.println("Mitarbeiter nicht berechtigt");
		}

	}

	public Bestellbestaetigung bestellungAusfuehren(Mitarbeiter mitarbeiter, List<Bestellposten> bplist) {
		Bestellbestaetigung bbfalse = new Bestellbestaetigung(false, 0.0);
		Bestellbestaetigung bb;
		boolean ausgefuehrt = false;
		double gesamtpreis = 0;

		if (berechtigteMitarbeiter.contains(mitarbeiter.getId())) {
			for (Bestellposten bp : bplist) {
				String bartikelid = bp.getArtikelID();
				int bartikelanzahl = bp.getAnzahl();
				for (Lagerposten lp : lagerbestand) {
					String lartikelid = lp.getLagerArtikel().getArtikelId();
					if (lartikelid.equals(bartikelid)) {
						if (lp.getLagerBestand() <= bp.getAnzahl()) {
							double bestellpreis = lp.getPreis() * lp.getLagerBestand();
							lp.setLagerBestand((lp.getLagerBestand() - bartikelanzahl)); // int entnommen =
																							// lp.getLagerBestand() -
																							// bartikelanzahl;
							System.out.println(lp.getLagerArtikel().getArtikelName() + " entnommen");
							gesamtpreis += bestellpreis;
							ausgefuehrt = true;
						} else {
							System.out.println("nicht genug");
							ausgefuehrt = false;
						}
					}
				}
			}
		} else {
			System.out.println("Mitarbeiter nicht berechtigt");
			ausgefuehrt = false;
		}
		bb = new Bestellbestaetigung(ausgefuehrt, gesamtpreis);
		return bb;
	}

}
