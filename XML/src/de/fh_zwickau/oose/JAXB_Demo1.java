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

// Die generierten Klassen m�ssen nat�rlich importiert werden:
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
			 * gegen das Schema w�nschen:
			 */
			unmarshaller.setEventHandler(new BooksValidationEventHandler());

			/*
			 * Die Klasse Bestand muss hier mitsamt Paketnamen angegeben werden, da es im
			 * aktuellen Paket eine weitere Klasse mit gleichem Namen gibt.
			 */
			de.fh_zwickau.bestand.Bestand bestand = (de.fh_zwickau.bestand.Bestand) unmarshaller
					.unmarshal(new FileInputStream("bestand.xml"));
			 
			/*  
			 * Der (generierte) Methodenname getBuch ist etwas irref�hrend.
			 * Tats�chlich wird eine Liste von BuchType-Objekten geliefert.
			 * Gleiches gilt weiter unten f�r die Methode getAutor.
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

				System.out.println("Ung�ltiges Dokument: ");
				System.out.println("Fehler: " + ve.getMessage());
				System.out.println("Der Fehler wurde gefunden in Zeile "
						+ locator.getLineNumber() + ", Spalte "
						+ locator.getColumnNumber());
			}
			/* Durch die R�ckgabe von true wird erreicht, dass das Unmarshalling
			 * auch f�r XML-Dokumente, die nicht dem Schema entsprechen, ausgef�hrt wird.
			 * Die JAXB-Spezifikation erlaubt JAXB-Implementierungen, die auch f�r nicht
			 * erfolgreich validierte Dokumente ein Unmarshalling durchf�hren.
			 * Sie erlaubt es jedoch ebenso, in diesem Falle ein Unmarshalling zu verweigern.
			 */
			return true;
		}
	}
}
