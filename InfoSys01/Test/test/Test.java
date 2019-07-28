package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;

import infoSys.Artikel;
import infoSys.Bestellposten;
import infoSys.Lagerverwaltung;
import infoSys.Mitarbeiter;

public class Test
{

	private Lagerverwaltung lagerverwaltung;

	private Mitarbeiter m1;
	private Mitarbeiter m2;

	private Artikel a1;
	private Artikel a2;

	private Set<Bestellposten> bestellposten;
	private Set<Bestellposten> bestellposten2;

	@Before
	public void before()
	{
		lagerverwaltung = new Lagerverwaltung();

		m1 = new Mitarbeiter("01", "Bernd");
		m2 = new Mitarbeiter("02", "Horst");

		a1 = new Artikel("1", "Artikel1", "Artikel1");
		a2 = new Artikel("2", "Artikel2", "Artikel2");

		bestellposten = new HashSet<Bestellposten>();
		bestellposten.add(new Bestellposten("1", 20));
		bestellposten.add(new Bestellposten("2", 88));

		bestellposten2 = new HashSet<Bestellposten>();
//		bestellposten.add(new Bestellposten(1", 20));
//		bestellposten.add(new Bestellposten("002", 8));
	}

	@org.junit.Test
	public void bestellungAusfuehrenBestellPostenTest()
	{
//		lagerverwaltung.berechtigungErteilen(m1);
//		lagerverwaltung.berechtigungErteilen(m2);

		assertFalse(lagerverwaltung.bestellungAusfuehren(m1, bestellposten).isAusgefuehrt());

		lagerverwaltung.berechtigungErteilen(m1);
		lagerverwaltung.berechtigungErteilen(m2);
		lagerverwaltung.wareneingangBuchen(m1, a1, 999, 30);
		lagerverwaltung.wareneingangBuchen(m1, a2, 999, 30);

		assertTrue(lagerverwaltung.bestellungAusfuehren(m1, bestellposten).isAusgefuehrt());

	}

	@org.junit.Test
	public void tooHighAmountBestellungAusfuehrenTest()
	{
		lagerverwaltung.berechtigungErteilen(m2);
		lagerverwaltung.wareneingangBuchen(m2, a1, 999, 30);

		bestellposten2.add(new Bestellposten("1", 99999999));
		assertFalse(lagerverwaltung.bestellungAusfuehren(m2, bestellposten2).isAusgefuehrt());

	}

	@org.junit.Test
	public void zeroAmountBestellungAusfueherenTest()
	{
		lagerverwaltung.berechtigungErteilen(m2);
		lagerverwaltung.wareneingangBuchen(m2, a1, 999, 30);

		bestellposten2.add(new Bestellposten("1", 0));
		assertTrue(lagerverwaltung.bestellungAusfuehren(m2, bestellposten2).isAusgefuehrt());
	}

	@org.junit.Test
	public void negAmountBestellungAusfueherenTest()
	{
		lagerverwaltung.berechtigungErteilen(m2);
		lagerverwaltung.wareneingangBuchen(m2, a1, 999, 30);

		bestellposten2.add(new Bestellposten("1", -1));
//		assertFalse(lagerverwaltung.bestellungAusfuehren(m2, bestellposten2).isAusgefuehrt());
	}

	@org.junit.Test
	public void gesamtPreisBestellungAusfueherenTest()
	{
		lagerverwaltung.berechtigungErteilen(m2);
		lagerverwaltung.wareneingangBuchen(m2, a1, 999, 10);

		bestellposten2.add(new Bestellposten("1", 10));
		assertTrue(lagerverwaltung.bestellungAusfuehren(m2, bestellposten2).getGesamtpreis() == 100);
	}

//	// Berechtigug des Mitarbeiters Zurückziehen
//	@Test
//	public void berechtigungZuruekziehenMitarbeiterExistiertTest()
//	{
//		lagerverwaltung.berechtigungErteilen(m1);
//		assertTrue(lagerverwaltung.getBerechtigteMitarbeiter().contains(m1.getID()));
//		lagerverwaltung.berechtigungZurueckziehen(m1);
//		assertFalse(lagerverwaltung.getBerechtigteMitarbeiter().contains(m1.getID()));
//	}
//
//	@Test
//	public void berechtigungZuruekziehenMitarbeiterKeineRechteTest()
//	{
//		assertEquals(0, lagerverwaltung.getBerechtigteMitarbeiter().size());
//		lagerverwaltung.berechtigungZurueckziehen(m2);
//		assertEquals(0, lagerverwaltung.getBerechtigteMitarbeiter().size());
//	}
//
//	@Test
//	public void berechtigungZuruekziehenMitarbeiterExistiertNichtTest()
//	{
//		assertEquals(0, lagerverwaltung.getBerechtigteMitarbeiter().size());
//		lagerverwaltung.berechtigungZurueckziehen(null);
//		assertEquals(0, lagerverwaltung.getBerechtigteMitarbeiter().size());
//	}
//
//	// Wareneingang Test
//	@Test
//	public void wareneingangNormaleEingabenTest()
//	{
//		lagerverwaltung.berechtigungErteilen(m1);
//		assertEquals(0, lagerverwaltung.getLagerposten().size());
//		lagerverwaltung.wareneingangBuchen(m1, a1, 1, 1.5);
//		assertEquals(1, lagerverwaltung.getLagerposten().size());
//	}
//
//	@Test
//	public void wareneingangKeineBerechtigungTest()
//	{
//		assertEquals(0, lagerverwaltung.getLagerposten().size());
//		lagerverwaltung.wareneingangBuchen(m1, a1, 1, 1.5);
//		assertEquals(0, lagerverwaltung.getLagerposten().size());
//	}
//
//	@Test
//	public void wareneingangMehrerArtikelTest()
//	{
//		lagerverwaltung.berechtigungErteilen(m1);
//		assertEquals(0, lagerverwaltung.getLagerposten().size());
//		lagerverwaltung.wareneingangBuchen(m1, a1, 1, 1.5);
//		assertEquals(1, lagerverwaltung.getLagerposten().size());
//		lagerverwaltung.wareneingangBuchen(m1, a2, 5, 2.8);
//		assertEquals(2, lagerverwaltung.getLagerposten().size());
//	}
//
//	@Test
//	public void wareneingangNullTest()
//	{
//		// Artikel null
//		lagerverwaltung.berechtigungErteilen(m1);
//		assertEquals(0, lagerverwaltung.getLagerposten().size());
//		lagerverwaltung.wareneingangBuchen(m1, null, 1, 1.5);
//		assertEquals(0, lagerverwaltung.getLagerposten().size());
//		// Mitarbeiter null
//		assertEquals(0, lagerverwaltung.getLagerposten().size());
//		lagerverwaltung.wareneingangBuchen(null, a1, 1, 1.5);
//		assertEquals(0, lagerverwaltung.getLagerposten().size());
//	}
//
//	@Test
//	public void wareneingangRichtigeArtikelTest()
//	{
//		lagerverwaltung.berechtigungErteilen(m1);
//		lagerverwaltung.wareneingangBuchen(m1, a1, 1, 1.5);
//
//		List<Lagerposten> lagerposten = lagerverwaltung.getLagerposten();
//		if (lagerposten.size() == 1)
//		{
//			assertEquals(a1, lagerposten.get(0).getArtikel());
//			assertEquals(1, lagerposten.get(0).getLagerbestand());
//			assertEquals(1.5, lagerposten.get(0).getPreis(), 0.1);
//		}
//
//	}
//
//	@Test
//	public void berechtigungErteilenNullTest()
//	{
//		lagerverwaltung.berechtigungErteilen(null);
//
//		assertTrue(lagerverwaltung.getBerechtigteMitarbeiter().size() == 0);
//	}
//
//	@Test
//	public void berechtigungErteilenTest()
//	{
//		assertTrue(lagerverwaltung.getBerechtigteMitarbeiter().size() == 0);
//		lagerverwaltung.berechtigungErteilen(m1);
//		assertTrue(lagerverwaltung.getBerechtigteMitarbeiter().contains(m1.getID()));
//		lagerverwaltung.berechtigungErteilen(m1);
//		assertTrue(lagerverwaltung.getBerechtigteMitarbeiter().size() == 1);
//
//		lagerverwaltung.berechtigungErteilen(m2);
//		assertTrue(lagerverwaltung.getBerechtigteMitarbeiter().size() == 2);
//
//	}
//
//	@Test
//	public void berechtigungErteilenIdGleichTest()
//	{
//		Mitarbeiter mike = new Mitarbeiter("1", "Mike");
//		Mitarbeiter tanner = new Mitarbeiter("1", "Tanner");
//		lagerverwaltung.berechtigungErteilen(mike);
//		lagerverwaltung.berechtigungErteilen(tanner);
//		assertTrue(lagerverwaltung.getBerechtigteMitarbeiter().size() == 1);
//	}
//
//	@Test
//	public void berechtigungErteilenExtremeTest()
//	{
//		for (int i = 0; i < 10000; i++)
//		{
//			Mitarbeiter m = new Mitarbeiter("" + i, "");
//			lagerverwaltung.berechtigungErteilen(m);
//			assertTrue(lagerverwaltung.getBerechtigteMitarbeiter().contains(m.getID()));
//		}
//		assertTrue(lagerverwaltung.getBerechtigteMitarbeiter().size() == 10000);
//	}
//
//	@Test
//	public void bestellungAusfuehrenNullTest()
//	{
//		assertFalse(lagerverwaltung.bestellungAusfuehren(null, null).isAUSGEFUEHRT());
//	}
//
//	@Test
//	public void bestellungAusfueherenMitarbeiterBerechtigungTest()
//	{
//		lagerverwaltung.wareneingangBuchen(m1, a1, 999, 30);
//		lagerverwaltung.wareneingangBuchen(m1, a2, 999, 30);
//
//		assertFalse(lagerverwaltung.bestellungAusfuehren(m1, bestellposten).isAUSGEFUEHRT());
//		lagerverwaltung.berechtigungErteilen(m1);
//		lagerverwaltung.berechtigungErteilen(m2);
//		assertTrue(lagerverwaltung.bestellungAusfuehren(m1, bestellposten).isAUSGEFUEHRT());
//		assertTrue(lagerverwaltung.bestellungAusfuehren(m2, bestellposten).isAUSGEFUEHRT());
//
//	}

}
