package infoSys;

/**
 * Stellt Bestellposten eines Artikels für eine Bestellung bereit.
 */
public class Bestellposten
{
	private final String artikelID;
	private final int anzahl;

	/**
	 * Erstellt eine neue Bestellung eines Artikels.
	 * 
	 * @param artikelID ID des Artikels
	 * @param anzahl    Anzahl der Artikel
	 */
	public Bestellposten(String artikelID, int anzahl)
	{
		this.artikelID = artikelID;
		this.anzahl = anzahl;
	}

	public String getID()
	{
		return artikelID;
	}

	public int getAnzahl()
	{
		return anzahl;
	}
}
