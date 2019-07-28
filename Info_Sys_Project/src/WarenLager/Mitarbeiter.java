package WarenLager;

public class Mitarbeiter {
	private final String id;
	private String name;

	public Mitarbeiter(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

}
