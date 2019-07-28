package whz.pti.eva.item.domain;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import whz.pti.eva.BaseEntity;
import whz.pti.eva.PizzaSize;
import whz.pti.eva.pizza.domain.Pizza;

@Entity
public class Item  extends BaseEntity<Long>{

	
	public int quantity;
	public String name;
	
	@OneToOne
	private Pizza pizza;
	
	public PizzaSize size;
	
	public Item() {
		
	}
	
	public Item(Pizza pizza, int quantity, PizzaSize size) {
		this.pizza = pizza;
		this.quantity = quantity;
		this.size = size;
		this.name = pizza.getName();
	}

	public Pizza getPizza() {
		return pizza;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PizzaSize getSize() {
		return size;
	}

	public void setSize(PizzaSize size) {
		this.size = size;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	
	

}
