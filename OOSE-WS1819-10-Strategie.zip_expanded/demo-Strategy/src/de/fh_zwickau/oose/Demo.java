package de.fh_zwickau.oose;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("*** esse Hamster / wegwerfen Hamster ***");
		Item fritz = new Hamster();
		fritz.essenAufrufen();
		fritz.wegwerfenAufrufen();

		Item haselnuss = new Nuss();
		System.out.println("*** esse Nuss ***");
		haselnuss.essenAufrufen();
		// Die Nuss wird geknackt:
		haselnuss.setEssbarkeit(new Essbar());
		System.out.println("*** Die Nuss ist jetzt geknackt... ***");
		System.out.println("*** esse Nuss / wegwerfen Nuss ***");
		haselnuss.essenAufrufen();
		haselnuss.wegwerfenAufrufen();
		/*  Natürlich ist erst essen und dann wegwerfen nicht sinnvoll.
		* Das Streichen des Items aus der Inventarliste wird hier aber nicht
		behandelt */

	}

}
