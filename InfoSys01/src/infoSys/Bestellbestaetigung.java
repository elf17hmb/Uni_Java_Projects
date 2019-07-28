package infoSys;

/**
 * Stellt Bestellbest�tigungen f�r Bestellungen bereit.
 */
public class Bestellbestaetigung
{

	private final boolean ausgefuehrt;
	private final double gesamtpreis;

	/**
	 * Erstellt eine Bestellbest�tigung einer Bestellung und gibt die Eigenschaften
	 * dieser in der Konsole aus.
	 * 
	 * @param ausgefuehrt Gibt an, ob die jeweilige Bestellung ausgef�rht wurde
	 *                    (True)
	 * @param gesamtpreis Summe der Kosten aller Bestellposten der Bestellung
	 */
	public Bestellbestaetigung(boolean ausgefuehrt, double gesamtpreis)
	{
		this.ausgefuehrt = ausgefuehrt;
		this.gesamtpreis = gesamtpreis;
		String ausgefuehrtText;

		// F�r Textausgabe auf der Konsole:
		if (this.ausgefuehrt)
		{
			ausgefuehrtText = "Ja";
		} else
		{
			ausgefuehrtText = "Nein";
		}

		// Konsolenausgabe:
		System.out.println("Bestellbest�tigung: ");
		if (!ausgefuehrt)
		{
			System.out.println("   Kosten f�r Artikel, die nicht in einem Lagerposten sind, sind nicht mit betrachtet:");
		}
		System.out.println("   Gesamtpreis: " + this.gesamtpreis);
		System.out.println("   Bestellung ausgef�hrt: " + ausgefuehrtText);

	}

	public boolean isAusgefuehrt()
	{
		return ausgefuehrt;
	}

	public double getGesamtpreis()
	{
		return gesamtpreis;
	}

}
