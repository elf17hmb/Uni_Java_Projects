package infoSys;

import java.util.HashSet;
import java.util.Set;

/**
 * Dient dem Starten des Programmes.
 */
public class Start
{

	public static void main(String[] args)
	{

		Lagerverwaltung lv = new Lagerverwaltung();

		Mitarbeiter m1 = new Mitarbeiter("1", "Adam");
		Mitarbeiter m2 = new Mitarbeiter("2", "Bernd");
		Mitarbeiter m3 = new Mitarbeiter("3", "Clemens");
		Mitarbeiter m4 = new Mitarbeiter("10", "Dora");

		Artikel a1 = new Artikel("1", "Schwamm", "Reinigungsmittel");
		Artikel a2 = new Artikel("2", "Schraube", "Ersatzteil");
		Artikel a3 = new Artikel("3", "3D-Drucker", "tech. Gerät");
		Artikel a4 = new Artikel("4", "Tannenbaum", "Weihnachtsdeko");
		Artikel a5 = new Artikel("5", "Taschenlampe", "Gadget");

		Set<Bestellposten> bestellung1 = new HashSet<Bestellposten>();
		bestellung1.add(new Bestellposten("2", 300));
		bestellung1.add(new Bestellposten("2", 300));
		Set<Bestellposten> bestellung2 = new HashSet<Bestellposten>();
		bestellung2.add(new Bestellposten("1", 5));
		// bestellung2.add(new Bestellposten("5", 10)); //Bestellposten sollten nicht 2
		// gleiche Artikel enthalten
		Set<Bestellposten> bestellung3 = new HashSet<Bestellposten>();
		bestellung3.add(new Bestellposten("2", 43));
		bestellung3.add(new Bestellposten("4", 2));
		Set<Bestellposten> bestellung4 = new HashSet<Bestellposten>();
		bestellung4.add(null);
		Set<Bestellposten> bestellung5 = new HashSet<Bestellposten>();
		bestellung5.add(new Bestellposten("4", 0));
		Set<Bestellposten> bestellung6 = new HashSet<Bestellposten>();
		bestellung6.add(new Bestellposten("1", 5));
		bestellung6.add(new Bestellposten("2", 30));
		bestellung6.add(new Bestellposten("3", 1));
		Set<Bestellposten> bestellung7 = new HashSet<Bestellposten>();
		bestellung7.add(new Bestellposten("20", 5));
		Set<Bestellposten> bestellung8 = new HashSet<Bestellposten>();

		// -----------------------------------------
		lv.berechtigungErteilen(m1);
		lv.berechtigungErteilen(m1);
		lv.berechtigungZurueckziehen(m2);
		lv.berechtigungErteilen(m2);
		lv.berechtigungErteilen(m4);
		lv.berechtigungErteilen(m3);
		lv.berechtigungZurueckziehen(m3);
		lv.berechtigungErteilen(null);
		lv.berechtigungZurueckziehen(null);

		lv.wareneingangBuchen(m1, a1, 10, 0.43);
		lv.wareneingangBuchen(m3, a2, 1, 0.1);
		lv.wareneingangBuchen(m4, a2, 200, 0.03);
		lv.wareneingangBuchen(m2, a3, 10, 3299);
		lv.bestellungAusfuehren(m1, bestellung1);
		lv.bestellungAusfuehren(m4, bestellung2);
		lv.wareneingangBuchen(m1, a4, 20, 19.99);
		lv.bestellungAusfuehren(m4, bestellung3);
		lv.wareneingangBuchen(null, null, 0, 0);
		lv.bestellungAusfuehren(m1, bestellung4);
		lv.wareneingangBuchen(m1, a1, 5, 0.45);
		lv.bestellungAusfuehren(m2, bestellung5);
		lv.wareneingangBuchen(m4, a5, 50, 10);
		lv.wareneingangBuchen(m1, a5, 0, 9.99);
		lv.bestellungAusfuehren(m1, bestellung6);
		lv.bestellungAusfuehren(m2, bestellung7);
		lv.bestellungAusfuehren(m4, bestellung8);
		lv.bestellungAusfuehren(m2, null);

		lv.lagerbestandAusgeben();

	}
}
