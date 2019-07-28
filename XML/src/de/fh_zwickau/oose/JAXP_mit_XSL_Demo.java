package de.fh_zwickau.oose;

import java.io.File;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

public class JAXP_mit_XSL_Demo {

  public static void main(String[] args) {

    try {
      // Eingabedatei:
      Source inputXML = new StreamSource(
        new File("bestand.xml"));

      // XSLT-Datei (beschreibt die Transformation):
      Source inputXSL = new StreamSource(
        new File("bestand.xsl"));

      // Ausgabedatei:
      Result outputXHTML = new 	StreamResult(
        new File("bestand.html"));

      // neue Transformer-Factory:
      TransformerFactory factory = TransformerFactory.newInstance();

     
      // ... und ein Transformer-Objekt erzeugt:
      Transformer transformer = factory.newTransformer(inputXSL);
      
      /* Alternative: Die Anweisungen aus der XSLT-Datei werden vorcompiliert...
       * Templates templates = factory.newTemplates(inputXSL);
       * Transformer transformer = templates.newTransformer();
       * Das geht schneller, wenn das selbe Stylesheet mehrfach verwendet wird
       * und ist threadsicher.
       */
      
      // Transformation durchführen:
      transformer.transform(inputXML, outputXHTML);
    } catch (TransformerConfigurationException e) {
      System.out.println("Der XSLT-Prozessor unterstützt nicht die gewünschten Features.");
    } catch (TransformerException e) {
      System.out.println("Fehler bei der Transformation.");
    }
  }
}