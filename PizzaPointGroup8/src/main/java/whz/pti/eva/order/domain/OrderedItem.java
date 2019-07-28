package whz.pti.eva.order.domain;



import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import whz.pti.eva.BaseEntity;
import whz.pti.eva.PizzaSize;

@Entity
public class OrderedItem extends BaseEntity<Long>{

	
	private Long pizzaId;
	private String name;
	private int quantity;
	private String userId;
	private PizzaSize size;
	private BigDecimal price;
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
	public Long getPizzaId() {
		return pizzaId;
	}
	public void setPizzaId(Long pizzaId) {
		this.pizzaId = pizzaId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
