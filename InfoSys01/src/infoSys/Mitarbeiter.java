package infoSys;

/**
 * Stellt Mitarbeiter bereit.
 */
public class Mitarbeiter
{
	private final String id;
	private String name;

	/**
	 * Legt einen neuen Mitarbeiter mit gegebenen Eigenschaften an.
	 * 
	 * @param id   ID des Mitarbeiters
	 * @param name Name des Mitarbeiters
	 */
	public Mitarbeiter(String id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public String getID()
	{
		return id;
	}
}
