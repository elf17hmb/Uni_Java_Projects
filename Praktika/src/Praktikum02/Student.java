package Praktikum02;

public class Student {
	private String nachname, vorname, gebdatum;
	private int studId;
	private boolean semGeb; // Semester Gebühr
	private static int maxStudId = 0;

	public Student(int studId, String nachname, String vorname, String gebdatum, boolean semGeb) {
		this.nachname = nachname;
		this.vorname = vorname;
		this.gebdatum = gebdatum;
		this.semGeb = semGeb;
		if (studId > maxStudId) {
			maxStudId = studId;
			this.studId = studId;
		} else if (studId < maxStudId) {
			maxStudId++;
			this.studId = maxStudId;
		}
	}

	public String getGebdatum() {
		return gebdatum;
	}

	public void setGebdatum(String gebdatum) {
		this.gebdatum = gebdatum;
	}

	public boolean isSemGeb() {
		return semGeb;
	}

	public void setSemGeb(boolean semGeb) {
		this.semGeb = semGeb;
	}

	public String getNachname() {
		return nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public int getStudId() {
		return studId;
	}

	public static int getMaxStudId() {
		return maxStudId;
	}

}
