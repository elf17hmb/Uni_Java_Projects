package Praktikum7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccountAdministration {
	private HashMap<String, Account> accounts = new HashMap<String, Account>();

	public AccountAdministration() {
	}

	public void createAccount(String name, String password) {
		Account account = new Account(name, password);
		accounts.put(name, account);
	}

	private void getPassword(String name) {
		accounts.get(name).getPassword();
	}

	private void getLogin(String name) {
		accounts.get(name).getLogin();
	}

	public void printAllAccounts() {
		for (Account acc : accounts.values()) {
			String name = acc.getName();
			String passw = acc.getPassword();
			String login = acc.getLogin();
			System.out.println("Name: " + name + " Passwort: " + passw + " Login: " + login);
		}
	}

	public List<Account> filterByFirstLetter(char letter) {
		List<Account> accountlist = new ArrayList<>();
		for (Account acc : accounts.values()) {
			String name = acc.getName();
			char firstletter = name.charAt(0);
			if (letter == firstletter) {
				accountlist.add(acc);

			}
		}
		return accountlist;
	}
}
