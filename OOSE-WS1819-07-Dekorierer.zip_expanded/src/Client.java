
class Client {
	
	// eine Limousine mit Klimaanlage:
    public static void main(String[] args) {  
        Auto auto = new Klimaanlage(new Limousine());
        System.out.println(auto.gibTypenbezeichnung());
        auto.zeigeDetails();
        System.out.println("\nfuer " + auto.gibKosten()
                + " Euro\n");

        // Wir fügen noch einige weitere Sonderausstattungen hinzu:
        auto = new Navigationssystem(new Seitenairbags(auto));
        System.out.println(auto.gibTypenbezeichnung());
        auto.zeigeDetails();
        System.out.println("\nfuer " + auto.gibKosten()
                + " Euro\n");

        // und jetzt noch ein anderes Auto (diesmal ein Cabrio):
        auto = new Navigationssystem(new Seitenairbags(new Cabrio()));
        System.out.println(auto.gibTypenbezeichnung());
        auto.zeigeDetails();
        System.out.println("\nfuer " + auto.gibKosten()
                + " Euro\n");
    }
}
