package de.fh_zwickau.oose;

import java.io.IOException;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * 
 * Die Klasse JAXB_Demo3 zeigt, wie ein einzelnes Buch-Objekt in eine XML-Datei geschrieben wird.
 * Wichtig ist dazu, dass in der Klasse Buch die Annotation @XmlRootElement steht:
 * @XmlRootElement
 * public class Buch {...}
 * In diesem Falle ist keine Generierung von Klassen mit einem Binding Compiler nötig.
 *
 */

public class JAXB_Demo3 {


	public static void main(String[] args) throws JAXBException, IOException {

	
		Buch buch1 = new Buch();
		buch1.setIsbn("1-861005-37-7");
		buch1.setBuchtitel("All you ever wanted to know about XML");
		buch1.setAutor("Stefanie Olm");
		buch1.setErscheinungsjahr(2008);
		buch1.setVerlag("Cambridge University Press");
		
		// Marshaller wird erzeugt:		
		   JAXBContext context = 
			JAXBContext.newInstance(Buch.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		// Marshaller gibt das Buch-Objekt als Stream auf System.out aus:		
			   m.marshal(buch1, System.out);
			   
			   
			   
	}
		
		
}

