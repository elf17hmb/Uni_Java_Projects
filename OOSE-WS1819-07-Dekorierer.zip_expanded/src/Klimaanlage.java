/**
 * Ein konkreter Dekorierer
 *
 */
class Klimaanlage extends Ausstattung
{
   public Klimaanlage(Auto auto) 
   {
      super(auto);
   }
   
   public void zeigeDetails() // "dekoriert" die Details
   {
      auto.zeigeDetails();
      System.out.print (", Klimaanlage");
   }
    
   public int gibKosten() // "dekoriert" die Kosten
   {
      return auto.gibKosten() + 1500;
   }
}
