package whz.pti.eva.cart.service;

import java.util.List;

import whz.pti.eva.PizzaSize;
import whz.pti.eva.item.domain.Item;
import whz.pti.eva.item.service.ItemDTO;
import whz.pti.eva.pizza.domain.Pizza;

public interface CartService {
	
	List<ItemDTO> listAllItems();
	
	void addItem(Pizza pizza, int quantity, PizzaSize size);
	
	void deleteItem(Long id);
	
	Item getItemById(Long itemId);
	
	void clearCart();

}
