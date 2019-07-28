/**
 * Ein konkreter Dekorierer
 *
 */
class Navigationssystem extends Ausstattung
{
   public Navigationssystem (Auto auto)
   {
      super (auto);
   }

   public void zeigeDetails() // "dekoriert" die Details
   {
      auto.zeigeDetails();
      System.out.print (", Navigationssystem");
   }

   public int gibKosten() // "dekoriert" die Kosten
   {
      return auto.gibKosten() + 2500;
   }
}
