package whz.pti.eva.item.service;

import java.util.List;

import whz.pti.eva.item.domain.Item;

public interface ItemService {
	

    void addItemToCart();
	
	List<Item> listAllItems();
	
	void deleteItemFromCart();
	
	Item getItemById(Long id);

}
