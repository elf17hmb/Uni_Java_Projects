package recursion;

public class Matryoschka
{
	private int zahl = 0;

	private Matryoschka anotherM;

	public Matryoschka()
	{

	}

	public Matryoschka(int zahl)
	{
		this.zahl = zahl;
	}

	public int getZahl()
	{
		return zahl;
	}

	public void setZahl(int zahl)
	{
		this.zahl = zahl;
	}

	public Matryoschka getAnotherM()
	{
		return anotherM;
	}

	public void setAnotherM(Matryoschka anotherM)
	{
		this.anotherM = anotherM;
	}

}
