package de.fh_zwickau.oose;

import java.io.File;
import java.io.IOException;

// JAXP
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;


// SAX
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * JAXP_mit_SAX zeigt, wie eine XML-Datei mit SAX eingelesen wird.
 * Die Implementierung des ContentHandler-Interfaces sorgt dann dafür,
 * dass der Dateiinhalt aufbereitet und angezeigt wird.
 *
 */


public class JAXP_mit_SAX_Demo {

	public static void main(String args[]) {

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setNamespaceAware(true);
			
			
/* Jetzt wird ein neuer SAX-Parser erzeugt.
 * Beachte, dass nicht gesagt wird, welcher Parser das ist.
 * Daher ist in dieser Datei auch kein Import des aktuell benutzten Parsers nötig
 * (etwa import org.xml.sax.XMLReader;)
 * Will oder muss man dennoch mit den spezifischen Methoden eines bestimmten Parsers arbeiten,
 * bekommt man Zugriff auf diesen mit parser.getXMLReader();
 *
 */
			
			SAXParser parser = factory.newSAXParser();
			
			parser.parse(new File("bestand.xml"), new MyContentHandler());
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (SAXException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}

class MyContentHandler extends DefaultHandler {
	boolean printMe = false;
	static int count = 0;


	// Ereignis "Start eines XML-Elements"
	@Override	// überschreibt Methode aus XMLFilterImpl
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes attributes) {

		if (localName.equals("buch")) {
			System.out.println("\nUnd noch ein Buch gefunden...");
			count++;
		}

		if (localName.equals("titel") || localName.equals("autor") 
				|| localName.equals("verlag")
				|| localName.equals("erscheinungsjahr")
				|| localName.equals("isbn") ) {

			System.out.print(localName + ": ");
			printMe = true;

		} 
		
		
		else
			printMe = false; // Ausleiher u.a. sollen nicht gedruckt werden
	}
	
	// Ereignis "Text-Inhalt innerhalb eines XML-Elements erkannt"
	@Override	// überschreibt Methode aus XMLFilterImpl
	public void characters(char[] ch, int start, int length) {
		String gotString = new String(ch, start, length);
		if (printMe) {
			if (!gotString.trim().equals("")) {
				System.out.println(gotString.trim());
			}
		}
	}

	// Ereignis "Ende eines XML-Elements"
	@Override	// überschreibt Methode aus XMLFilterImpl
	public void endElement(String namespaceURI, String localName, String qName) {
		if (localName.equals("bestand"))
			System.out.println("Zahl der gefundenen Bücher: " + count);

	}

}
