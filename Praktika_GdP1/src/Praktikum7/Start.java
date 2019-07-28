package Praktikum7;

import java.util.ArrayList;
import java.util.List;

public class Start {

	public static void main(String[] args) {
		AccountAdministration acad = new AccountAdministration();
		acad.createAccount("Idiot", "12345");
		acad.createAccount("Durak", "12342345");
		acad.createAccount("Tupiza", "12323423445");
		acad.createAccount("Dubina", "1223432424345");
		acad.printAllAccounts();
		List<Account> accounts = new ArrayList<>();
		accounts = acad.filterByFirstLetter('D');
		for (Account ac : accounts) {
			System.out.println(ac.getName());
		}

	}

}
