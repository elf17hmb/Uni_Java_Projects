package recursion;

public class MatryoschkaManager
{

//	private int maxcount = 0;
//	private int anzahlMatryoschkas = 0;

	public MatryoschkaManager()
	{

	}

//	public MatryoschkaManager(int maxcount)
//	{
//		this.maxcount = maxcount;
//	}
//
//	public Matryoschka einpacken(Matryoschka m)
//	{
//		if (m.getZahl() == maxcount)
//		{
//			return m;
//		}
//		Matryoschka m2 = new Matryoschka(m.getZahl() + 1);
//		m.setAnotherM(m2);
//		return einpacken(m2);
//	}

	public Matryoschka besseresEinpacken(Matryoschka m, int anzahlEinzupacken)
	{
		if (anzahlEinzupacken == 0)
		{
			return m;
		}
		Matryoschka mNext = new Matryoschka();
		m.setAnotherM(mNext);
		return besseresEinpacken(mNext, anzahlEinzupacken - 1);
	}
//
//	public int count(Matryoschka m)
//	{
//		if (m.getAnotherM() == null)
//		{
//			return anzahlMatryoschkas;
//
//		}
//		anzahlMatryoschkas += 1;
//		return count(m.getAnotherM());
//
//	}

	public int bessererCount(Matryoschka m)
	{
		return bessererCount(m, 0);
	}

	public int bessererCount(Matryoschka m, int count)
	{
		if (m.getAnotherM() == null)
		{
			return count;
		}
		count += 1;
		return bessererCount(m.getAnotherM(), count);
	}

}
