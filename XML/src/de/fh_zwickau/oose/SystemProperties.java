package de.fh_zwickau.oose;

public class SystemProperties {

	public static void main(String[] args) {
		  java.util.Properties systemproperties = System.getProperties();
		  systemproperties.list(System.out);

	}

}
