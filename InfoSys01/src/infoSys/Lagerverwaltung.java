package infoSys;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Stellt Objekte bereit, die Methoden zur Manipulierung der Lagerbest�nde und
 * Mitarbeiterberechtigungen bereitsetllen.
 * 
 */
public class Lagerverwaltung
{

	private Set<String> berechtigteMitarbeiter = new HashSet<String>(); // Liste aller IDs der berechtigten Mitarbeiter
	private Set<Lagerposten> lagerposten = new TreeSet<Lagerposten>(); // Sortierte Liste aller Lagerposten (nach
																		// Artikel-ID)

	/**
	 * Erstellt ein neues Objekt von Lagerverwaltung und f�gt in der Historie einen
	 * Trennstrich f�r bessere �bersichtlichkeit ein.
	 */
	public Lagerverwaltung()
	{
		addFileEntry("---------------------------------------------------------------------------------------------------------", null);
	}

	/**
	 * Berechtigt(zu Liste hinzuf�gen) den gegebenen Mitarbeiter, wenn m�glich
	 * 
	 * @param mitarbeiter Mitarbeiter, der Berechtigung erhalten soll
	 */
	public void berechtigungErteilen(Mitarbeiter mitarbeiter)
	{
		String ausgabe;

		if (mitarbeiter != null)
		{
			if (!istBerechtigt(mitarbeiter))
			{
				berechtigteMitarbeiter.add(mitarbeiter.getID());
				ausgabe = "Berechtigung erteilt!";
				addFileEntry(ausgabe, mitarbeiter);
			} else
			{
				System.out.println("Mitarbeiter " + mitarbeiter.getName() + " bereits berechtigt!");
			}
		} else
		{
			System.out.println("Kein Mitarbeiter gew�hlt!");
		}
	}

	/**
	 * Entzieht Berechtigung(von Liste entfernen) von gegebenen Mitarbeiter, wenn
	 * m�glich
	 * 
	 * @param mitarbeiter Mitarbeiter, der nicht mehr berechtigt sein soll
	 */
	public void berechtigungZurueckziehen(Mitarbeiter mitarbeiter)
	{
		String ausgabe;

		if (mitarbeiter != null)
		{
			if (istBerechtigt(mitarbeiter))
			{
				berechtigteMitarbeiter.remove(mitarbeiter.getID());
				ausgabe = "Berechtigung entzogen!";
				addFileEntry(ausgabe, mitarbeiter);
			} else
			{
				System.out.println("Mitarbeiter " + mitarbeiter.getName() + " war schon nicht berechtigt!");
			}
		} else
		{
			System.out.println("Kein Mitarbeiter gew�hlt!");
		}
	}

	/**
	 * Gibt den kompletten Lagerbestand in der Konsole aus.
	 */
	public void lagerbestandAusgeben()
	{

		int i = 0;
		for (Lagerposten lp : lagerposten)
		{
			i++;
			System.out.println("Lagerposten " + i + ": ");
			System.out.println("   Artikel: " + lp.getArtikel().getName() + " (ID: " + lp.getArtikel().getID() + ")");
			System.out.println("   Beschreibung: " + lp.getArtikel().getBeschreibung());
			System.out.println("   Lagerbestand: " + lp.getLagerbestand() + " St�ck (zu je " + lp.getPreis() + " �)");
		}
	}

	/**
	 * F�gt eine gegebene Anzahl von Artikeln einem vorhandenem/neuen Lagerposten
	 * zu, wenn m�glich. Eventuelle neue Preise �berschreiben alte Preise.
	 * 
	 * @param mitarbeiter Mitarbeiter, der f�r die Buchung verantwortlich
	 * @param artikel     Artikel, der hinzugef�gt werden soll
	 * @param anzahl      Anzahl der Artikel, die hinzugef�gt werden sollen
	 * @param preis       neuer Preis f�r hinzugef�gte Artikel
	 */
	public void wareneingangBuchen(Mitarbeiter mitarbeiter, Artikel artikel, int anzahl, double preis)
	{

		boolean artikelVorhanden = false; // hinzuzuf�gender Artikel schon in einem Lagerposten vorhanden?

		String ausgabePreisAenderung = "";
		String ausgabe = "";

		if (mitarbeiter != null && artikel != null)
		{

			if (istBerechtigt(mitarbeiter))
			{
				for (Lagerposten lp : lagerposten)
				{
					if (lp.getArtikel().getID().equals(artikel.getID()))
					{ //
						// Artikel schon vorhanden

						if (lp.getPreis() != preis)
						{ // Text f�r Historie vorbereiten, wenn neuer Preis
							ausgabePreisAenderung = " + Preis�nderung von " + lp.getPreis() + "� auf " + preis + "�";
						}
						lp.setLagerbestand(lp.getLagerbestand() + anzahl); // Lagerbestand erh�hen
						lp.setPreis(preis); // neuen Preis setzen
						artikelVorhanden = true; // Artikel schon vorhanden

						// Text f�r Historie
						ausgabe = "Neue Waren (" + anzahl + " St�ck) zu Lagerposten (mit Artikel " + artikel.getName() + ") hinzugef�gt." + ausgabePreisAenderung;
						break; // Beende Suche nach Artikel im Lager
					}
				}
				// Artikel noch nicht vorhanden
				if (!artikelVorhanden)
				{ // Artikel nicht im Lager gefunden
					lagerposten.add(new Lagerposten(artikel, anzahl, preis)); // Neuen Lagerposten erstellen
					ausgabe = "Neuer Lagerposten (mit Artikel " + artikel.getName() + " (" + anzahl + " St�ck)) erstellt.";
				}
			} else
			{ // Mitarbeiter nicht berechtigt
				ausgabe = "Buchung von Wareneingang fehlgeschlagen: Mitarbeiter nicht berechtigt!";
			}

			addFileEntry(ausgabe, mitarbeiter); // Eintrg zur Historie hinzuf�gen
		} else
		{
			System.out.println("Kein Mitarbeiter oder Artikel gew�hlt!");
		}
	}

	/**
	 * F�hrt eine Bestellung aus, indem Waren dem Lager entommen werden, wenn
	 * m�glich, und eine Bestellbest�tigung zur�ckgegeben wird.
	 * 
	 * @param mitarbeiter   Mitarbeiter, der f�r Bestellung verantwortlich ist.
	 * @param bestellposten Eine Liste an Bestellposten mit Artikeln, die geordert
	 *                      werden
	 * @return Bestellbest�tigung, die angibt, ob Bestellung ausgef�hrt wurde, bzw
	 *         zu welchem Preis dies geschah
	 */
	public Bestellbestaetigung bestellungAusfuehren(Mitarbeiter mitarbeiter, Set<Bestellposten> bestellposten)
	{
		boolean ausgefuehrt = true; // Gibt an, ob Bestellung ausgef�hrt werden kann
		double gesamtpreis = 0.0; // Gesamtkosten der Bestellung
		boolean ausfuehrbar = false; // Gibt an, ob ein spezieller Bestellposten ausf�hrbar ist

		String ausgabe = ""; // Test f�r Eintrag in Historie
		String warenVorhanden = "";// Text mit allen bestellten Artikeln, wenn Bestellung m�glich
		String warenFehlen = "";// Text mit allen zur Er�llung einer Bestellung noch notwendigen Artikeln

		Set<Lagerposten> newLagerposten = new TreeSet<Lagerposten>(); // Kopie von Orginal-Lagerposten-Sammlung
		for (Lagerposten lp : lagerposten)
		{ // Erstelle Kopie ("by value")
			newLagerposten.add(new Lagerposten(new Artikel(lp.getArtikel().getID(), lp.getArtikel().getName(), lp.getArtikel().getBeschreibung()), lp.getLagerbestand(), lp.getPreis()));
		}

		boolean meldungGeschrieben = false; // Gibt an, ob f�r jeweilgen Bestellposten schon ein Text f�r Historie
											// geschrieben

		if (mitarbeiter != null && bestellposten != null && !bestellposten.isEmpty())
		{

			if (!istBerechtigt(mitarbeiter))
			{
				ausgefuehrt = false; // Mitarbeiter nicht berechtigt --> Ausf�hrbarkeit=false
				ausgabe = "Bestellung fehlgeschlagen : Mitarbeiter nicht berechtigt";
			}

			// Pr�fe Bestellung auf Durchf�hrbarkeit, entferne Waren und Berechne
			// Gesamtkosten
			for (Bestellposten bp : bestellposten)
			{// Gehe alle Bestellposten durch
				if (bp != null)
				{ // Pr�fe, ob Bestellung nicht leer
					ausfuehrbar = false;
					for (Lagerposten lp : newLagerposten)
					{// Gleiche mit maximal allen Lagerposten ab
						meldungGeschrieben = false;
						if (bp.getID().equals(lp.getArtikel().getID()))
						{ // Artikel gefunden
							gesamtpreis += lp.getPreis() * bp.getAnzahl(); // Artikelkosten zu Gesamtkosten hinzuf�gen
							if (bp.getAnzahl() <= lp.getLagerbestand())
							{ // Lagerbestand ausreichend gro�
								ausfuehrbar = true;
								warenVorhanden += lp.getArtikel().getName() + " (" + bp.getAnzahl() + " St�ck) ; ";
								lp.setLagerbestand(lp.getLagerbestand() - bp.getAnzahl());// Reduziere den Lagerbestand
							} else
							{ // Lagerbestand zu klein f�r Bestellung
								warenFehlen += lp.getArtikel().getName() + " (" + (bp.getAnzahl() - lp.getLagerbestand()) + " St�ck) ; ";
								meldungGeschrieben = true;
							}
							break; // Beende suche nach Artikel, da schon gefunden
						}

					}

					if (!ausfuehrbar)
					{// Wenn aktueller Bestellposten der Bestellung nicht ausf�hrbar
						ausgefuehrt = false; // --> komplette Bestellung auch nicht ausf�hrbar
						if (!meldungGeschrieben)
						{ // Artikel gar nicht gefunden
							warenFehlen += "ArtikelID " + bp.getID() + " (" + (bp.getAnzahl()) + " St�ck) ; ";
						}
					}
				} else
				{
					ausgefuehrt = false; // nicht ausf�hrbar, wenn eine Bestellung einen Null-Bestellposten enth�lt
					warenFehlen += "keine ID eingegeben ; ";
				}
			}

			// Runden auf 2 Dezimalstellen
			gesamtpreis *= 100;
			Math.round(gesamtpreis);
			gesamtpreis /= 100;

			// Reduziere Lagerbest�nde bei komplett ausf�hrbaren Bestellungen
			if (ausgefuehrt)
			{
				lagerposten.clear();
				lagerposten.addAll(newLagerposten); // alten Lagerposten mit neuem �berschreiben

				warenVorhanden = warenVorhanden.substring(0, warenVorhanden.length() - 3); // letzte Trennzeichenkette
																							// entfernen
				ausgabe = "Bestellung ausgef�hrt! (Einnahmen: " + gesamtpreis + " � durch Artikel: " + warenVorhanden + ")";
			} else
			{ // Wenn komplette Bestellung nicht ausf�hrbar
				if (ausgabe.equals(""))
				{ // Wenn noch nicht geschrieben, dass Mitarbeiter nicht berechtigt

					warenFehlen = warenFehlen.substring(0, warenFehlen.length() - 3);// letzte Trennzeichenkette
																						// entfernen

					ausgabe = "Bestellung nicht ausf�hrbar! : Folgendes fehlt: " + warenFehlen;
				}
			}

			addFileEntry(ausgabe, mitarbeiter);
		} else
		{
			System.out.println("Kein Mitarbeiter oder Bestellposten gew�hlt!");
			ausgefuehrt = false;
		}

		return new Bestellbestaetigung(ausgefuehrt, gesamtpreis);
	}

	/**
	 * Pr�ft ob gegebener Mitarbeiter berechtigt ist.
	 * 
	 * @param mitarbeiter Zu pr�fender Mitarbeiter
	 * 
	 * @return Wahrheitswert, ob Mitarbeiter berechtigt
	 */
	private boolean istBerechtigt(Mitarbeiter mitarbeiter)
	{
		return berechtigteMitarbeiter.contains(mitarbeiter.getID()); // Suche Mitarbeiter-ID in der Liste der
																		// berechtigten Mitarbeiter
	}

	/**
	 * F�gt einen Eintrag mit gegebenem Inhalt zur Historie hinzu
	 * 
	 * @param s           Text des Eintrags
	 * @param mitarbeiter Verantwortlicher Mitarbeiter f�r jeweilige Aktion
	 */
	private void addFileEntry(String s, Mitarbeiter mitarbeiter)
	{
		String fileName = "Historie.txt"; // Historie-Datei
		String mitarbeiterText = ""; // Text, der Mitarbeiter, seine ID und evt. Leerzeichen enth�lt
		int namenLaenge = 15; // L�nge, auf die alle Namen+ID mittels " " gestreckt werden
		String fuellZeichen = ""; // Leerezeichen nach Namen/ID

		if (mitarbeiter != null)
		{
			if (mitarbeiter.getName().length() + mitarbeiter.getID().length() < namenLaenge)
			{ // Name und ID kleiner
				// Zeichenkette als
				// maximal angenommene
				for (int i = mitarbeiter.getName().length() + mitarbeiter.getID().length(); i < namenLaenge; i++)
				{ // auf
					// maximalL�nge
					// bringen
					fuellZeichen += " ";
				}
			}
			mitarbeiterText += mitarbeiter.getName() + " (ID: " + mitarbeiter.getID() + ")" + fuellZeichen + ": "; // 2.
																													// Teil(Mitarbeiter)
																													// eines
																													// Eintrages
		}
		s = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm")) + " : " + mitarbeiterText + s; // Datum
																														// vor
																														// jeden
																														// Eintrag
																														// schreiben
		try
		{ // Try notwendig
			PrintWriter pw = new PrintWriter(new FileWriter(fileName, true), true); // True f�r anh�ngen an
																					// bestehende datei
			pw.println(s);
			pw.close();
		} catch (Exception e)
		{
		}
	}

	public Set<String> getBerechtigteMitarbeiter()
	{
		return berechtigteMitarbeiter;
	}

	public void setBerechtigteMitarbeiter(Set<String> berechtigteMitarbeiter)
	{
		this.berechtigteMitarbeiter = berechtigteMitarbeiter;
	}

	public Set<Lagerposten> getLagerposten()
	{
		return lagerposten;
	}

	public void setLagerposten(Set<Lagerposten> lagerposten)
	{
		this.lagerposten = lagerposten;
	}

}
