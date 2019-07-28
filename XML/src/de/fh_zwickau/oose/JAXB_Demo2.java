package de.fh_zwickau.oose;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import de.fh_zwickau.bestand.BuchType;

/**
 * /** JAXB wird zum Unmarshalling eines XML-Dokuments genutzt, dann wird das
 * erstellte Objekt ge�ndert und schlie�lich das Ergebnis in eine neue XML-Datei
 * zur�ckgeschrieben.
 */

public class JAXB_Demo2 {

	public static void main(String args[]) {
		try {
			JAXBContext jc = JAXBContext.newInstance("de.fh_zwickau.bestand");

			// Unmarshalling:
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			/*
			 * Die Klasse Bestand muss hier mitsamt Paketname angegeben werden,
			 * da es im aktuellen Paket eine weitere Klasse mit gleichem Namen
			 * gibt.
			 */
			de.fh_zwickau.bestand.Bestand bestand = (de.fh_zwickau.bestand.Bestand) unmarshaller
					.unmarshal(new File("bestand.xml"));

			// �nderungen an den Objekten
			List<BuchType> bookList = bestand.getBuch();

			for (int i = 0; i < bookList.size(); i++) {
				BuchType book = bookList.get(i);
				if (book.getIsbn().equals("3-89842-365-4")) {
					book.setTitel("ge�ndert!");

					/*
					 * Ebenso k�nnte man jetzt weitere Knoten erzeugen und in
					 * das Dokument einf�gen. Dazu w�rde man die generierte
					 * Klasse de.fh_zwickau.bestand.ObjectFactory ben�tigen:
					 * ObjectFactory objFactory = new ObjectFactory(); ...und
					 * dann create...-Methoden auf objFactory aufrufen.
					 */
				}

			}

			// Marshalling:
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					new Boolean(true));
			marshaller.marshal(bestand, new FileOutputStream(
					"bestand-ge�ndert-mit-jaxb.xml"));
			System.out
					.println("Ge�nderte Datei wurde geschrieben nach bestand-ge�ndert-mit-jaxb.xml");

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		catch (FileNotFoundException e) {
			// "File not found" hei�t hier: Konnte Datei nicht schreiben
			// (z.B. weil das angegebene Verzeichnis nicht existiert oder
			// keine Berechtigung zum Schreiben vorliegt)
			System.out.println("Datei konnte nicht angelegt werden!");
			e.printStackTrace();
		}
	}
}
