package whz.pti.eva.cart.service;

import java.util.ArrayList; 
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.transform.Source;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


import whz.pti.eva.PizzaSize;
import whz.pti.eva.cart.domain.Cart;
import whz.pti.eva.cart.domain.CartRepository;
import whz.pti.eva.item.domain.Item;
import whz.pti.eva.item.domain.ItemRepository;
import whz.pti.eva.item.service.ItemDTO;
import whz.pti.eva.item.service.ItemService;
import whz.pti.eva.pizza.domain.Pizza;
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService{
	
	Cart cart = new Cart();
	@Autowired
	CartRepository cartRepo;
	@Autowired
	ItemRepository itemRepo;
	@Autowired
	public CartServiceImpl() {

	}

	@Override
	public List<ItemDTO> listAllItems() {
		List<Item> targetListOrigin = new ArrayList<>(cart.getItems()); 
		List<ItemDTO> targetList= new ArrayList<ItemDTO>(); 
		for (Item source: targetListOrigin ) {
	        ItemDTO target= new ItemDTO(source,source.getSize(),source.getName(),source.getQuantity(),source.getId());
//	        BeanUtils.copyProperties(source , target);
	        targetList.add(target);
	    };
	
		return targetList;
	}

	@Override
	public void addItem(Pizza pizza, int quantity, PizzaSize size) {
		Item item = new Item(pizza,quantity,size);
		cart.addItem(item);
		cartRepo.save(cart);
		itemRepo.save(item);
	}

	@Override
	public void deleteItem(Long id) {
		cart.getItems().remove(getItemById(id));
	}

	@Override
	public Item getItemById(Long itemId) {
		for(Item item : cart.getItems()) {
			if(item.getId()==itemId) {
				return item;

			}
		}
		return null;
	}

	@Override
	public void clearCart() {
		cart.getItems().clear();;
		
	}





}
