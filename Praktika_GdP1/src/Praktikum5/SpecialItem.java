package Praktikum5;

public class SpecialItem extends Item {
	protected double discount;

	public SpecialItem(Item specItem) {
		super(specItem);

	}

	public SpecialItem(int itemNumber, String name, double purchasePrice, int shelfLife) {
		this.itemNumber = itemNumber;
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.shelfLife = shelfLife;
		if (shelfLife > 12) {
			discount = 0.3;
		} else {
			discount = 0.1;
		}
	}

	@Override
	public double calculateSalePrice() {
		double netto = purchasePrice * (1 + MARGIN_OF_PROFIT);
		double brutto = netto * (1 + VAT);
		double rabatt = brutto * discount;
		double salePrice = brutto - rabatt;
		return salePrice;
	}

	@Override
	public String toString() {
		return "Sonderposten: " + super.toString() + "| Rabatt:" + (int) (discount * 100) + "%";
		// String string = "";
		// string = string + "Sonderposten: " + this.getItemNumber();
		// string = string + "|" + this.getName();
		// string = string + "|" + "EK: " + this.getPurchasePrice() + " Euro";
		// string = string + "|" + "VK: " + this.calculateSalePrice() + " Euro";
		// string = string + "|" + "Lagerzeit: " + this.getShelfLife() + " Monate";
		// string = string + "|" + "Rabatt: " + discount;
		// return string;
		// 55,10 + 33,06 + 10,469 = 98,629
	}

}
