package whz.pti.eva.item.service;	

import java.math.BigDecimal;

import whz.pti.eva.PizzaSize;
import whz.pti.eva.item.domain.Item;
import whz.pti.eva.pizza.domain.Pizza;

public class ItemDTO {
	
	public Long itemId;
	public String name;
	public int quantity;
	public PizzaSize size;
	public Item item;	
	public BigDecimal price;
	public BigDecimal priceSmall = new BigDecimal(0);
	public BigDecimal priceMedium = new BigDecimal(0);
	public BigDecimal priceLarge = new BigDecimal(0);
	
	
	public ItemDTO(Item item, PizzaSize size, String name, int quantity, Long itemId) {
		this.item=item;
		this.size = size;
		this.name = name;
		this.quantity = quantity;
		this.itemId = itemId;
		calcPrice();
	}
	
	public void calcPrice() {
		Pizza pizza =item.getPizza();
		priceSmall = pizza.getPriceSmall().multiply(new BigDecimal(quantity));
		priceMedium = pizza.getPriceMedium().multiply(new BigDecimal(quantity));
		priceLarge = pizza.getPriceLarge().multiply(new BigDecimal(quantity));
		if(size.equals(size.Large)) {
			price = priceLarge;
		}
		if(size.equals(size.Medium)) {
			price = priceMedium;
		}
		if(size.equals(size.Small)) {
			price = priceSmall;
		}
	}
	
	public String getPizzaname() {
		return name;
	}
	public void setPizzaname(String pizzaname) {
		this.name = pizzaname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public PizzaSize getSize() {
		return size;
	}
	public void setSize(PizzaSize size) {
		this.size = size;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getItemId() {
		return itemId;
	}

	public BigDecimal getPriceSmall() {
		return priceSmall;
	}

	public void setPriceSmall(BigDecimal priceSmall) {
		this.priceSmall = priceSmall;
	}

	public BigDecimal getPriceMedium() {
		return priceMedium;
	}

	public void setPriceMedium(BigDecimal priceMedium) {
		this.priceMedium = priceMedium;
	}

	public BigDecimal getPriceLarge() {
		return priceLarge;
	}

	public void setPriceLarge(BigDecimal priceLarge) {
		this.priceLarge = priceLarge;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	
	
}
