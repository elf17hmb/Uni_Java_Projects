package de.fh_zwickau.oose;

import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Text;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class JDOM_Demo {

	public static void main(String[] args) {
		try {
			Document doc = new SAXBuilder(false).build("bestand.xml");

			titelausgabe_mit_iterator(doc);
			// ausgabe_der_gesamten_datei(doc);
			// manipuliere_inhalt(doc);
			// ausgabe_der_gesamten_datei(doc);
		} catch (JDOMException jde) {
			jde.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// ////////////////////////////////////////////////////////////////////////////////
	private static void ausgabe_der_gesamten_datei(Document doc) throws IOException {
		new XMLOutputter().output(doc, System.out);
	}

	// ////////////////////////////////////////////////////////////////////////////////
	private static void titelausgabe_mit_iterator(Document doc) {
		Element e = doc.getRootElement();
		List<Element> buecherElems = e.getChildren();

		for (Element buchElem : buecherElems) {
			Element titelElem = buchElem.getChild("titel");
			Text titelContent = (Text) titelElem.getContent(0);
			String titel = titelContent.getText();
			System.out.println(titel);
			// oder kürzer: System.out.println(buchElem.getChildText("titel"));
		}

	}

	// ////////////////////////////////////////////////////////////////////////////////
	private static void manipuliere_inhalt(Document doc) {
		List<Element> buecherListe = doc.getRootElement().getChildren();
		for (Element buchElem : buecherListe) {
			if (buchElem.getChildText("isbn").equals("0-201-79168-4")) {
				buchElem.removeChild("vormerkung");
				Element ausleiheElem = new Element("ausleihe");
				Element nameElem = new Element("name");
				nameElem.addContent("Stefanie Rast");
				ausleiheElem.addContent(nameElem);
				buchElem.addContent(ausleiheElem);
			}
			/*
			 * In einem realen Programm würde man hier natürlich noch den Fall
			 * behandeln, dass kein Buch mit der entspr. ISBN gefunden wird.
			 */
		}

	}
}
