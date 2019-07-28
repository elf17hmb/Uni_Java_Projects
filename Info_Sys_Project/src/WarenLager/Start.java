package WarenLager;

import java.util.ArrayList;
import java.util.List;

public class Start {

	public static void main(String[] args) {
		Lagerverwaltung lv = new Lagerverwaltung();
		Mitarbeiter m1 = new Mitarbeiter("1", "Elon Musk");
		Mitarbeiter m2 = new Mitarbeiter("2", "Vladimir Putin");
		Mitarbeiter m3 = new Mitarbeiter("3", "Araragi Koyomi");
		Artikel apfeln = new Artikel("10", "Apfeln", "Saftige, rote Apfeln");
		Artikel merchendise = new Artikel("11", "Merchendise", "Anime/Spiel-figuren jeder Art");
		Artikel schwerter = new Artikel("12", "Schwerter", "Säbel, Degen, Florett, Katana und mehr");
		Bestellposten bp1 = new Bestellposten("10", 300);
		Bestellposten bp2 = new Bestellposten("11", 100);
		Bestellposten bp3 = new Bestellposten("12", 5);
		List<Bestellposten> bplist = new ArrayList<>();
		bplist.add(bp1);
		bplist.add(bp2);
		// bplist.add(bp3);
		lv.berechtigungErteilen(m1);
		lv.berechtigungErteilen(m2);
		lv.wareneingangBuchen(m1, merchendise, 30, 200.00);
		lv.wareneingangBuchen(m1, merchendise, 30, 400.00);
		lv.wareneingangBuchen(m3, schwerter, 15, 2000.00);
		lv.wareneingangBuchen(m2, apfeln, 700, 23.22);
		lv.wareneingangBuchen(m2, apfeln, 700, 233.22);
		lv.lagerbestandAusgeben();
		lv.bestellungAusfuehren(m1, bplist);

	}

}
