package de.fh_zwickau.oose;

public abstract class Item {
	Essbarkeit ess;
	Wegwerfbarkeit weg;

	public Item() {
	}

	// Die ersten drei Methoden sind für das traditionelle Strategie-Muster notwendig:
	public void essenAufrufen() {
		System.out.println(ess.essen());
	}

	public void wegwerfenAufrufen() {
		System.out.println(weg.wegwerfen());
	}

	public void setEssbarkeit(Essbarkeit ess) {
		this.ess = ess;
	}

	// Die beiden folgenden Methoden werden in der funktionalen Variante benötigt:
	public void essenAufrufenFunktional(Essbarkeit ess) {
		System.out.println(ess.essen());
	}

	public void wegwerfenAufrufenFunktional(Wegwerfbarkeit weg) {
		System.out.println(weg.wegwerfen());
	}
}
