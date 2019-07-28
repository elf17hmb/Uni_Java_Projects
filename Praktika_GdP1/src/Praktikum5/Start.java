package Praktikum5;

import java.util.ArrayList;

public class Start {

	public static void main(String[] args) {
		Item item1 = new Item(10, "Nachname Vorname", 23.40, 20);
		Item itemcopy = new Item(item1);
		SpecialItem specItem1 = new SpecialItem(3, "Vladimir Putin", 55.1, 5);
		SpecialItem specItem2 = new SpecialItem(5, "Leeroy Jenkins", 55.1, 5);
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(item1);
		items.add(itemcopy);
		items.add(specItem1);
		items.add(specItem2);
		// for (int i=0; i<items.size(); i++) {
		// double salePrice = items.get(i).calculateSalePrice();
		// }
		double preissumme = 0;
		for (Item item : items) {
			double sprice = item.calculateSalePrice();
			preissumme = sprice + preissumme;

		}

		System.out.println(item1.getName());
		System.out.println(itemcopy.getName());
		System.out.println(item1.calculateSalePrice());
		System.out.println(item1.toString());
		System.out.println(itemcopy.toString());
		System.out.println(specItem1.calculateSalePrice());
		System.out.println(specItem1.toString());
		System.out.println(specItem2.toString());
		System.out.println("Durchschnittlicher Preis: " + preissumme / items.size());
	}

}
