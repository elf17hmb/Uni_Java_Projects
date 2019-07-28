package Praktikum4;

public class Box {
	private double width;
	private double height;
	private double length;

	// Konstruktoren
	public Box(double width, double height, double length) {
		this.width = width;
		this.height = height;
		this.length = length;
	}

	public Box(double side) {
		this(side, side, side);
	}

	public Box(Box otherBox) {
		this(otherBox.width, otherBox.height, otherBox.length);
	}

	// Getter und Setter

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	// Andere Aufgaben

	public double calculateVolume() {
		double v;
		v = width * height * length;
		return v;
	}

	public double calculateSurfaceArea() {
		double a;
		a = 2 * (length * width + width * height + height * length);
		return a;
	}

	public Box getBiggerBox() {
		Box biggerbox = new Box(width * 1.25, height * 1.25, length * 1.25);
		return biggerbox;
	}

	public boolean fitsIn(Box otherBox) {
		if (otherBox.width > this.width && otherBox.height > this.height && otherBox.length > this.length) {
			return true;
		} else {
			return false;
		}
	}

}
