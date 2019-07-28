package whz.pti.eva.order.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import whz.pti.eva.BaseEntity;

@Entity
public class Ordered extends BaseEntity<Long>{
		
	private int numberOfItems;
	private String userId;
	private int totalPrice;
	
	@OneToMany
	List<OrderedItem> items = new ArrayList<>();

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderedItem> getItems() {
		return items;
	}

	public void setItems(List<OrderedItem> items) {
		this.items = items;
	}
	
	
}
