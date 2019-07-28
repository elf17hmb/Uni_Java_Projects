package de.fh_zwickau.oose;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.JAXBException;

// Die generierten Klassen müssen natürlich importiert werden:
import de.fh_zwickau.bestand.*;



/**
 * JAXB wird zum Unmarshalling eines XML-Dokuments genutzt.
 */

public class JAXB_Demo1 {

	public static void main(String args[]) {
		try {
			JAXBContext jc = JAXBContext.newInstance("de.fh_zwickau.bestand");
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			/* Die folgende Zeile kann auch entfallen, wenn wir keine Validierung der XML-Datei
			 * gegen das Schema wünschen:
			 */
			unmarshaller.setEventHandler(new BooksValidationEventHandler());

			/*
			 * Die Klasse Bestand muss hier mitsamt Paketnamen angegeben werden, da es im
			 * aktuellen Paket eine weitere Klasse mit gleichem Namen gibt.
			 */
			de.fh_zwickau.bestand.Bestand bestand = (de.fh_zwickau.bestand.Bestand) unmarshaller
					.unmarshal(new FileInputStream("bestand.xml"));
			 
			/*  
			 * Der (generierte) Methodenname getBuch ist etwas irreführend.
			 * Tatsächlich wird eine Liste von BuchType-Objekten geliefert.
			 * Gleiches gilt weiter unten für die Methode getAutor.
			 */
			List<BuchType> bookList = bestand.getBuch();
			
			/* Jetzt sind alle buch-Elemente der XML-Datei in einer Liste von BuchType-Objekten enthalten
			 *
			 */

			for (int i = 0; i < bookList.size(); i++) {
				System.out.println("Und noch ein Buch gefunden... ");
				BuchType book =  bookList.get(i);
				System.out.println("Titel: " + book.getTitel());
				System.out.println("Erscheinungsjahr: " + book.getErscheinungsjahr());

				System.out.println("Anzahl der Autoren: "
						+ book.getAutor().size());

				List<String> authors = book.getAutor();
				for (int j = 0; j < book.getAutor().size(); j++) {
					String authorName = authors.get(j);
					System.out.println("Autor: " + authorName.trim());
				}
				System.out.println();
			}

		} 
		catch (FileNotFoundException e) {
			System.out.println("Datei konnte nicht gefunden werden" + e);;
		}
		
		/* UnmarshalException wird z.B. geworfen, wenn die Validierung schiefgeht
		 * 
		 */
		catch (UnmarshalException e) {
			e.printStackTrace();
		}
		
		catch (JAXBException e) {
			e.printStackTrace();
		}
		

	}

	public static class BooksValidationEventHandler implements
			ValidationEventHandler {

		public boolean handleEvent(ValidationEvent ve) {

			if (ve.getSeverity() == ValidationEvent.FATAL_ERROR
					|| ve.getSeverity() == ValidationEvent.ERROR) {
				ValidationEventLocator locator = ve.getLocator();

				System.out.println("Ungültiges Dokument: ");
				System.out.println("Fehler: " + ve.getMessage());
				System.out.println("Der Fehler wurde gefunden in Zeile "
						+ locator.getLineNumber() + ", Spalte "
						+ locator.getColumnNumber());
			}
			/* Durch die Rückgabe von true wird erreicht, dass das Unmarshalling
			 * auch für XML-Dokumente, die nicht dem Schema entsprechen, ausgeführt wird.
			 * Die JAXB-Spezifikation erlaubt JAXB-Implementierungen, die auch für nicht
			 * erfolgreich validierte Dokumente ein Unmarshalling durchführen.
			 * Sie erlaubt es jedoch ebenso, in diesem Falle ein Unmarshalling zu verweigern.
			 */
			return true;
		}
	}
}
