package de.fh_zwickau.oose;

import java.io.*;
import java.util.Date;
import java.beans.*;

public class JavaBeans_Demo
{
  public static void main( String args[] ) throws Exception
  {
    String filename = "datum.ser.xml";

    // Serialisieren

    try
    {
      XMLEncoder  o = new XMLEncoder( new FileOutputStream(filename) );
      o.writeObject( "Eine Zeichenkette" );
      o.writeObject( new Date() );
      o.close();
    } catch ( IOException e )
    { e.printStackTrace();}
    
    // Deserialisieren (Wir verwenden die gerade geschriebene Datei.)
    
    try
    {
      XMLDecoder o = new XMLDecoder(
        new FileInputStream(filename) );

      String string = (String) o.readObject();
      Date date = (Date) o.readObject();
      o.close();

      System.out.println( string );
      System.out.println( date );
    }
    catch ( IOException e ) { }
  }
}
