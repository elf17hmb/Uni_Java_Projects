package Praktikum6;

public class Professor extends Member {
	public Professor(int id) {
		setCanteenPrice(250);
		this.id = id;
	}

	@Override
	public String toString() {
		String s = "Memberstatus: Professor" + "\n    ID:" + getId();
		return s;

	}

}
