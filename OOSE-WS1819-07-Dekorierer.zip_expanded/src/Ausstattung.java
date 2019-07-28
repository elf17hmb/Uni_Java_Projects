
/**
 * Der abstrakte Dekorierer.
 * (ist ein Auto und enthält ein Auto-Objekt als Attribut)
 *
 */
public abstract class Ausstattung implements Auto
{
   protected Auto auto;

   public Ausstattung (Auto auto)
   {
      this.auto = auto;
   }
   

/* 
 * Die Typbezeichnung ändert sich nicht, wenn zusätzliche Ausstattung
 * hinzukommt. Daher kann gibTypbezeichnung() hier (in der Oberklasse)
 * implementiert werden.
 */
public String gibTypenbezeichnung()
   {
	   return(auto.gibTypenbezeichnung());
   }
   
}
