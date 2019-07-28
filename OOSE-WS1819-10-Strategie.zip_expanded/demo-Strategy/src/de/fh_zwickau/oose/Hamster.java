package de.fh_zwickau.oose;

public class Hamster extends Item {
	public Hamster() {
		ess = new NichtEssbar();
		weg = new NichtWegwerfbar();
	}
}
