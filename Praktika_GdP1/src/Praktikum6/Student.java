package Praktikum6;

public class Student extends Member {
	public Student(int id) {
		setCanteenPrice(150);
		this.id = id;
	}

	@Override
	public String toString() {
		String s = "Memberstatus: Student" + "\n    ID:" + getId();
		return s;

	}

}
