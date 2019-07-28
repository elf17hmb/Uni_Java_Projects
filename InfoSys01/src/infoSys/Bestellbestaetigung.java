package infoSys;

/**
 * Stellt Bestellbestätigungen für Bestellungen bereit.
 */
public class Bestellbestaetigung
{

	private final boolean ausgefuehrt;
	private final double gesamtpreis;

	/**
	 * Erstellt eine Bestellbestätigung einer Bestellung und gibt die Eigenschaften
	 * dieser in der Konsole aus.
	 * 
	 * @param ausgefuehrt Gibt an, ob die jeweilige Bestellung ausgefürht wurde
	 *                    (True)
	 * @param gesamtpreis Summe der Kosten aller Bestellposten der Bestellung
	 */
	public Bestellbestaetigung(boolean ausgefuehrt, double gesamtpreis)
	{
		this.ausgefuehrt = ausgefuehrt;
		this.gesamtpreis = gesamtpreis;
		String ausgefuehrtText;

		// Für Textausgabe auf der Konsole:
		if (this.ausgefuehrt)
		{
			ausgefuehrtText = "Ja";
		} else
		{
			ausgefuehrtText = "Nein";
		}

		// Konsolenausgabe:
		System.out.println("Bestellbestätigung: ");
		if (!ausgefuehrt)
		{
			System.out.println("   Kosten für Artikel, die nicht in einem Lagerposten sind, sind nicht mit betrachtet:");
		}
		System.out.println("   Gesamtpreis: " + this.gesamtpreis);
		System.out.println("   Bestellung ausgeführt: " + ausgefuehrtText);

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
