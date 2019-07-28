/**
 * Ein konkreter Dekorierer
 *
 */

class Seitenairbags extends Ausstattung
{
   public Seitenairbags(Auto auto)
   {
      super (auto);
   }
   
   public void zeigeDetails() // "dekoriert" die Details
   {
      auto.zeigeDetails();
      System.out.print(", Seitenairbags");
   }
   
   public int gibKosten() // "dekoriert" die Kosten
   {
      return auto.gibKosten() + 1000;
   }
}
