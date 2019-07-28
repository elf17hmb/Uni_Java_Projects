	package de.fh_zwickau.oose;

import java.io.File;
import java.io.IOException;

// JAXP
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
// DOM
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
/**
 * 
 * JAXP_mit_DOM_Demo1 zeigt, wie mit JAXP+DOM eine XML-Datei eingelesen und
 * die dort enthaltenen Inhalte (Text-Contents) auf die Standardausgabe ausgegeben wird.
 *
 */
public class JAXP_mit_DOM_Demo1
{
  public static void main( String[] args )
  { // DocumentBuilderFactory erzeugen
	// Nicht verwirren lassen:
	// Wir haben es hier mit einer Factory zu tun, die eine Factory erzeugt...
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
    	// Eigenschaften der Factory setzen, z.B.
    	factory.setValidating(false);
    	factory.setNamespaceAware(false);
    	// Und jetzt erzeugt die Factory ein DocumentBuilder-Objekt:
    	DocumentBuilder builder = factory.newDocumentBuilder();

    	// Einlesen der XML-Datei, es wird ein org.w3c.dom.Document erzeugt:
    	Document document = builder.parse( new File("bestand.xml") );
    	// org.w3c.dom.Node hat u.a. die einfache Methode "gib alle enthaltenen
    	// Texte aus":
    	System.out.println( document.getFirstChild().getTextContent() );
    }
       catch (ParserConfigurationException e)
       {System.out.println("Parser unterstützt nicht die gewünschten Featueres: " +e);
       }
       catch (IOException e)
       {System.out.println("IO-Exception: " + e);
       }
       catch (SAXException e)
       {System.out.println("SAX-Exception:  + e");
       }
    

  
  }
}

