
package de.fh_zwickau.oose;

import java.io.FileOutputStream;
import java.io.IOException;

// JAXP
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
// DOM
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
//XSL
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerException;

/**
 * 
 * JAXP_mit_DOM_Demo2 zeigt, wie mit JAXP+DOM ein XML-Dokument erzeugt
 * und das Ergebnis in eine neue Datei geschrieben wird.
 * Man erkennt, dass beim Schreiben mehr getan wird, als nur den String auszugeben:
 * Die Zeichenkette "<XML>" wird in der XML-Datei korrekt als "&lt;XML&gt;" geschrieben!
 *
 */
public class JAXP_mit_DOM_Demo2
{
  public static void main( String[] args )
  { 
	try {
		DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(false);
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();

		// Ein neues org.w3c.dom.Document wird angelegt
		Document document = documentBuilder.newDocument();

		// und die einzelnen Elemente werden nach und nach an den XML-Baum gehängt:
		Element bestand = document.createElement("bestand");
		document.appendChild(bestand);

		Element book = document.createElement("buch");
		bestand.appendChild(book);
		
		Attr attr = document.createAttribute("signatur");
		attr.setValue("A317");
		book.setAttributeNode(attr);

		Element name = document.createElement("titel");
		Text nameText = document.createTextNode("Keine Angst vor <XML>");
		name.appendChild(nameText);
		book.appendChild(name);
		
		Element autor = document.createElement("autor");
		Text autorText = document.createTextNode("Peter Strolch");
		autor.appendChild(autorText);
		book.appendChild(autor);
		
		Element verlag = document.createElement("verlag");
		Text verlagText = document.createTextNode("Random House");
		verlag.appendChild(verlagText);
		book.appendChild(verlag);

		Element erscheinungsjahr = document.createElement("erscheinungsjahr");
		Text erscheinungsjahrText = document.createTextNode("2012");
		erscheinungsjahr.appendChild(erscheinungsjahrText);
		book.appendChild(erscheinungsjahr);

		Element ISBN = document.createElement("isbn");
		Text ISBNText = document.createTextNode("1-861005-37-7");
		ISBN.appendChild(ISBNText);
		book.appendChild(ISBN);

		// Schließlich wird das org.w3c.dom.Document in eine Datei geschrieben
		// (dazu wird XSL mit einem Transformer-Objekt verwendet.)
		DOMSource domSource = new DOMSource(document);
		StreamResult streamResult = new StreamResult(new FileOutputStream(
				"bestand-erzeugt-mit-dom.xml"));
		TransformerFactory transformerFactory = TransformerFactory.newInstance();

		Transformer transformer = transformerFactory.newTransformer();
		// Dokument wird mit Zeilenumbrüchen erstellt:
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(domSource, streamResult);

		System.out.println("Dokument wurde geschrieben nach bestand-erzeugt-mit-dom.xml");

	}
       catch (ParserConfigurationException e)
       {System.out.println("Parser unterstützt nicht die gewünschten Featueres: " +e);
       }
       catch (IOException e)
       {System.out.println("IO-Exception: " + e);
       }
       catch (TransformerConfigurationException e)
       {System.out.println("TransformerConfigurationException:  + e");
       }
 
       catch (TransformerException e)
       {System.out.println("TransformerException:  + e");
       }
    

  
  }
}

