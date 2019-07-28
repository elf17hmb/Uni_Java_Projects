package Praktikum5;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {
	protected int itemNumber;
	protected String name;
	protected double purchasePrice;
	protected int shelfLife;
	protected static final double MARGIN_OF_PROFIT = 0.6; // Handelsspanne, 60 Prozent des Einkaufspreises
	protected static final double VAT = 0.19; // Mehrwertsteuer, 19 Prozent des Preises

	public Item(int itemNumber, String name, double purchasePrice, int shelfLife) {
		this.itemNumber = itemNumber;
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.shelfLife = shelfLife;
	}

	public Item(Item otherItem) {
		this(otherItem.itemNumber * 1000, "Kopie_" + otherItem.name, otherItem.purchasePrice, otherItem.shelfLife);
	}

	public Item() {

	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	} // https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
		// https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html
		// https://docs.oracle.com/javase/7/docs/api/java/math/RoundingMode.html

	public double calculateSalePrice() {
		double netto = purchasePrice * (1 + MARGIN_OF_PROFIT);
		double brutto = netto * (1 + VAT);
		return brutto;
	}

	@Override
	public String toString() {
		String string = "";
		string = string + getItemNumber();
		string = string + "|" + getName();
		string = string + "| EK: " + round(getPurchasePrice(), 2) + " Euro |";
		string = string + "VK: " + round(calculateSalePrice(), 2) + " Euro |";
		string = string + "Lagerzeit: " + getShelfLife() + " Monate";
		return string;
	}

	// Getters and Setters
	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public int getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(int shelfLife) {
		this.shelfLife = shelfLife;
	}

}
