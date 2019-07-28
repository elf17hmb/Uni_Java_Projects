package Praktikum6;

public class Start {

	public static void main(String[] args) {
		Student student1 = new Student(1);
		Member student2 = new Student(2);
		Professor professor1 = new Professor(33);
		Member member1 = new Member(444);
		University university = new University();
		university.addMember(student1);
		university.addMember(student2);
		university.addMember(professor1);
		university.addMember(member1);
		Member byid = university.getMemberById(33);
		System.out.println(university.getSumOfCanteenPayment());
		System.out.println(byid.toString());

	}

}
