
/**
 * Der abstrakte Dekorierer.
 * (ist ein Auto und enth�lt ein Auto-Objekt als Attribut)
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
 * Die Typbezeichnung �ndert sich nicht, wenn zus�tzliche Ausstattung
 * hinzukommt. Daher kann gibTypbezeichnung() hier (in der Oberklasse)
 * implementiert werden.
 */
public String gibTypenbezeichnung()
   {
	   return(auto.gibTypenbezeichnung());
   }
   
}
