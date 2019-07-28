package de.fh_zwickau.oose;

public class NichtWegwerfbar implements Wegwerfbarkeit {
	public String wegwerfen() {
		return ("Kann nicht weggeworfen werden!");
	}
}
