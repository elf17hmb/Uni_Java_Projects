package infoSys;

/**
 * Stellt Artikel mit spezifischen Eigenschaften bereit.
 */
public class Artikel
{
	private final String id;
	private String name;
	private String beschreibung;

	/**
	 * Erstellt einen neuen Artikel mit gegebenen Eigenschaften.
	 * 
	 * @param id           ID des Artikels
	 * @param name         Name des Artikels
	 * @param beschreibung Beschreibung des Artikels
	 */
	public Artikel(String id, String name, String beschreibung)
	{
		this.id = id;
		this.name = name;
		this.beschreibung = beschreibung;
	}

	public String getID()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getBeschreibung()
	{
		return beschreibung;
	}
}
