package whz.pti.eva.cart.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import whz.pti.eva.BaseEntity;
import whz.pti.eva.customer.domain.Customer;
import whz.pti.eva.item.domain.Item;

@Entity
public class Cart extends BaseEntity<Long>{
	
	private int quantity;
	private String userid;
	private String key;
	
	@OneToOne
	private Customer customer;

	@OneToMany(cascade= CascadeType.ALL)
	private List<Item> items = new ArrayList<>();
	
	public Cart() {}
	
	public List<Item> getItems(){
		return items;
	}
	
	public void addItem(Item item) {
		this.items.add(item);
	}
	

	
}
