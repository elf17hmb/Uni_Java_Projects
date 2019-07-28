package de.fh_zwickau.oose;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * 
 * Die Klasse JAXB_Demo4 zeigt, wie ein Bestand-Objekt (das seinerseits
 * eine ArrayList aus mehreren Buch-Objekten enthält) in ein XML-Dokument überführt wird. 
 *
 */
public class JAXB_Demo4 {

	private static final String XMLDATEI = "./bestand-erzeugt-mit-jaxb.xml";

	public static void main(String[] args)  {

		ArrayList<Buch> bookList = new ArrayList<Buch>();

		// Bücher anlegen...
		Buch buch1 = new Buch();
		buch1.setIsbn("1-861005-37-7");
		buch1.setBuchtitel("All you ever wanted to know about XML");
		buch1.setAutor("Stefanie Olm");
		buch1.setErscheinungsjahr(2008);
		buch1.setVerlag("Cambridge University Press");
		bookList.add(buch1);

		Buch buch2 = new Buch();
		buch2.setIsbn("0-201-79168-4");
		buch2.setBuchtitel("XML für Sozialwissenschaftler");
		buch2.setAutor("Beate Schumann");
		buch2.setErscheinungsjahr(2012);
		buch2.setVerlag("Springer-Verlag");
		bookList.add(buch2);
		
		// Bestand-Objekt wird erzeugt und die Bücher zu dessen Bücherliste hinzugefügt:
		Bestand bestand = new Bestand();
		bestand.setBuecher(bookList);
		

		// Marshaller wird erzeugt:
		
		try {
		JAXBContext context = JAXBContext.newInstance(Bestand.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		// Marshaller gibt den Bestand als Stream auf System.out aus:
		m.marshal(bestand, System.out);
		
		
		// Marshaller gibt den Bestand per Writer in eine Datei aus:
		Writer w = null;
		w = new FileWriter(XMLDATEI);
			m.marshal(bestand, w);
			w.close();
	
		
		/*
		 * Jetzt wird die gerade erzeugte XML-Datei wieder eingelesen
		 * (Unmarshaller) und daraus ein Bestand-Objekt erzeugt.
		 * Dessen Methoden können wir benutzen, um die Informationen zu
		 * den einzelnen Büchern auszulesen.
		 */
		System.out.println();
		System.out.println("Aus der XML-Datei gelesene Daten: ");
		Unmarshaller um = context.createUnmarshaller();
		Bestand b2 = (Bestand) um.unmarshal(new FileReader(
				XMLDATEI));

		for (int i = 0; i < b2.getBuecherliste().toArray().length; i++) {
			System.out.println("Buch " + (i + 1) + ": "
					+ b2.getBuecherliste().get(i).getBuchtitel() + ", Verlag: "
					+ b2.getBuecherliste().get(i).getVerlag());
		}
		
		
		}
		
		catch (JAXBException e)
		{e.printStackTrace();}
		
		
		catch (IOException e)
		{System.out.println("Fehler bei der Arbeit mit Datei " + XMLDATEI);
		e.printStackTrace();}	
		




	}

}
