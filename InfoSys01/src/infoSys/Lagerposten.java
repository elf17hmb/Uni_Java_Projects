package infoSys;

/**
 * Stellt Lagerposten mit jeweiligem Artikel und deren Kosten bereit.
 */
public class Lagerposten implements Comparable<Lagerposten>
{

	private int lagerbestand;
	private double preis;
	private Artikel artikel;

	/**
	 * Erstellt einen neuen Lagerposten mit angegebenen Werten.
	 * 
	 * @param artikel      Artikel, der dem Lagerposten zugewiesen wird
	 * @param lagerbestand Anzahl der vorhandenen Artikel im Lagerposten
	 * @param preis        Preis für je einen Artikel des Lagerpostens
	 */
	public Lagerposten(Artikel artikel, int lagerbestand, double preis)
	{
		this.lagerbestand = lagerbestand;
		this.preis = preis;
		this.artikel = artikel;

	}

	public int getLagerbestand()
	{
		return lagerbestand;
	}

	public void setLagerbestand(int lagerbestand)
	{
		this.lagerbestand = lagerbestand;
	}

	public double getPreis()
	{
		return preis;
	}

	public void setPreis(double preis)
	{
		this.preis = preis;
	}

	public Artikel getArtikel()
	{
		return artikel;
	}

	// Dient der späteren Sortierung der Lagerposten nach Artikel-ID
	@Override
	public int compareTo(Lagerposten lp)
	{

		return this.getArtikel().getID().compareTo(lp.getArtikel().getID()); // vergleicht die Artikel-IDs miteinander
	}

}
