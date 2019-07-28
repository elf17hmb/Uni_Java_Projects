package recursion;

public class Main
{

	public static void main(String[] args)
	{
		MatryoschkaManager mManager = new MatryoschkaManager();
		Matryoschka m = new Matryoschka();

		System.out.println("Zähle Matrjoschka in einem leeren Matryoschka: " + mManager.bessererCount(m));

		Matryoschka m1 = new Matryoschka(1);

		Matryoschka m2 = new Matryoschka(2);

		Matryoschka m3 = new Matryoschka(3);

		m.setAnotherM(m1);
		m1.setAnotherM(m2);
		m2.setAnotherM(m3);
		System.out.println("In der größten Matrjoschka sind " + mManager.bessererCount(m) + " andere enthalten");
		System.out.println("In der zweiten Matrjoschka sind " + mManager.bessererCount(m1) + " andere enthalten");
		System.out.println("In der dritten Matrjoschka sind " + mManager.bessererCount(m2) + " andere enthalten");
		System.out.println("In der vierten Matrjoschka sind " + mManager.bessererCount(m3) + " andere enthalten");

//		mManager.besseresEinpacken(m, 99);
//		System.out.println("In dieser Matryoschka sind " + mManager.bessererCount(m) + " andere enthalten");
	}

}
