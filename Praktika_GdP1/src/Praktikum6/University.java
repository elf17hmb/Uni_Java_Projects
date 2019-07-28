package Praktikum6;

import java.util.ArrayList;

public class University {
	ArrayList<Member> members = new ArrayList<>();

	public void addMember(Member member) {
		if (member == null)
			throw new IllegalArgumentException("Member ist Null!");
		members.add(member);
	}

	public int getSumOfCanteenPayment() {
		int sum = 0;
		for (Member k : members) {
			sum += k.getCanteenPrice();
		}
		return sum;
	}

	public Member getMemberById(int id) {
		for (Member m : members) {
			int ayylmao = m.getId();
			if (ayylmao == id) {
				return m;
			}
		}
		return null;
	}

}
