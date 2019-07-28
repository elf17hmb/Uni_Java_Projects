package Praktikum4;

public class Main {

	public static void main(String[] args) {
		Box box1 = new Box(1);
		Box box2 = new Box(box1);
		Box biggerbox = box2.getBiggerBox();
		double sa = biggerbox.calculateSurfaceArea();
		double v = biggerbox.calculateVolume();
		System.out.println(v);
		System.out.println(sa);
		System.out.println(biggerbox.getHeight());
		System.out.println();
		System.out.println(box1.fitsIn(biggerbox));
	}

}
