package whz.pti.eva.order.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import whz.pti.eva.cart.service.CartServiceImpl;
import whz.pti.eva.item.service.ItemDTO;
import whz.pti.eva.order.domain.Ordered;
import whz.pti.eva.order.domain.OrderedItem;
import whz.pti.eva.order.domain.OrderedItemRepository;
import whz.pti.eva.order.domain.OrderedRepository;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderedServiceImpl {

	Ordered order = new Ordered();
	
	@Autowired 
	private OrderedRepository orderedrepo;
	
	@Autowired
	private OrderedItemRepository ordereditemrepo;
	

	public void orderCartItems(List<ItemDTO> items) {
		orderedrepo.save(order);
		List<ItemDTO> sourcelist = items;
		List<OrderedItem> targetlist = new ArrayList<>();
		for(ItemDTO source : sourcelist) {
			OrderedItem ordereditem = new OrderedItem();
			ordereditem.setPrice(source.getPrice());
			ordereditem.setQuantity(source.getQuantity());
			ordereditem.setSize(source.getSize());
			ordereditem.setName(source.getPizzaname());
			ordereditem.setPizzaId(source.getItem().getPizza().getId());
			targetlist.add(ordereditem);
			ordereditemrepo.save(ordereditem);
		}
		order.getItems().addAll(targetlist);
		
	}
	
	public List<OrderedItem> listAllOrderedItems(){
		return order.getItems();
		
	}
	
}
