package Praktikum6;

public class Member {
	protected int id;
	private int canteenPrice;

	// public Member (int id, int canteenPrice) {
	//
	// }
	//
	// public Member() {
	// this(id,300); ----> Professor und Student super();
	// }
	public Member() {

	}

	public Member(int id) {
		this.id = id;
		setCanteenPrice(300);
	}

	public int getCanteenPrice() {
		return canteenPrice;
	}

	protected void setCanteenPrice(int canteenPrice) {
		this.canteenPrice = canteenPrice;
	}

	protected int getId() {
		return id;
	}

	@Override
	public String toString() {
		String s = "Memberstatus: Member" + "\n    ID:" + getId();
		return s;

	}

}
