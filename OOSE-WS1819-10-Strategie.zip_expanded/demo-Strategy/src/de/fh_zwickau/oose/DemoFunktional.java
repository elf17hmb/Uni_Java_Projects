package de.fh_zwickau.oose;

public class DemoFunktional {

	/**
	 * Bei der Verwendung von Lambda-Ausdrücken als Paramtern beim Funktionsaufruf
	 * kann einer Methode über den Aufrufparameter (also zur Laufzeit des Programms)
	 * ihre Arbeitsweise vorgegeben werden.
	 *
	 * Vorteil: Die Klassen Essbar, NichtEssbar, NochNichtEssbar, Wegwerfbar und NichtWegwerfbar
	 * werden jetzt nicht mehr benötigt.
	 *
	 * Erfordert Java >= 8
	 */
	public static void main(String[] args) {

		/**
		 * Attribute, die die funktionalen Interfaces Essbarkeit und Wegwerfbarkeit implementieren,
		 * Beachte, dass Essbarkeit und Wegwerfbarkeit auch tatsächlich funktionale Interfaces sein
		 * müssen. Sie definieren genau eine Methode.
		 */
		//werden durch einen Lambda-Ausdruck initialisiert.
		Essbarkeit nichtEssbar = () -> "Kann nicht gegessen werden!";
		Essbarkeit nochtNichtEssbar = () -> "Noch kann das nicht gegessen werden!";
		Essbarkeit essbar = () -> "Schmatz! Kau!";

		Wegwerfbarkeit wegwerfbar = () -> "Hau weg!";
		Wegwerfbarkeit nichtWegwerfbar = () -> "Kann nicht weggeworfen werden!";

		System.out.println("*** esse Hamster / wegwerfen Hamster ***");
		Item fritz = new Hamster();
		fritz.essenAufrufenFunktional(nichtEssbar);
		fritz.wegwerfenAufrufenFunktional(nichtWegwerfbar);

		Item haselnuss = new Nuss();
		System.out.println("*** esse Nuss ***");
		haselnuss.essenAufrufenFunktional(nochtNichtEssbar);
		System.out.println("*** Die Nuss ist jetzt geknackt... ***");
		System.out.println("*** esse Nuss / wegwerfen Nuss ***");
		haselnuss.essenAufrufenFunktional(essbar);
		haselnuss.wegwerfenAufrufenFunktional(wegwerfbar);


	}

}
