package Praktikum7;

public class Account {
	private String name;
	private String login;
	private String password;
	private int id;
	private static int counter = 1;

	public Account(String name, String password) {
		this.name = name;
		this.password = password;
		id = 999 + counter;
		counter++;
		createLogin();
	}

	public void createLogin() {
		// String initial = Character.toString(name.charAt(0)) +
		// Character.toString(name.charAt(1));
		// String fullid = String.valueOf(id);
		// String matrik = "";
		// for (int i = 0; i < 4; i++) {
		// matrik = matrik + Character.toString(fullid.charAt(i));
		// }
		// login = initial + matrik;
		String initial = name.substring(0, 2);// viel leichter - mit substring() (STRING Java doc)
		String matrikel = Integer.toString(id).substring(0, 4);
		login = initial + matrikel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

}
