package whz.pti.eva.cart.boundary;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import whz.pti.eva.PizzaSize;
import whz.pti.eva.cart.service.CartService;
import whz.pti.eva.item.domain.Item;
import whz.pti.eva.item.service.ItemDTO;
import whz.pti.eva.order.service.OrderedServiceImpl;

@Controller
public class CartController {

	@Autowired 
	private CartService cartservice;
	
	@Autowired
	private OrderedServiceImpl orderedservice;
	
	@RequestMapping("/cart")
	public String goToCart(Model model) {
		System.out.println("Go To Cart!");
		countItems(model);
		model.addAttribute("pizzaSize", PizzaSize.class);
//		System.out.println("LIST ALL ITEMS: " + items.size());
		return "cart";
	}
	
	@RequestMapping(value="/cart/deleteFromCart", method = RequestMethod.GET)
	public String deleteFromCart(Model model, @RequestParam Long id) {
		cartservice.deleteItem(id);
		return "redirect:/cart";
		
	}
	
	@RequestMapping(value ="/cart/edit", method = RequestMethod.GET)
	public String editCartItem(@RequestParam int quantity, @RequestParam Long id,@RequestParam PizzaSize size,Model model) {
		Item item =cartservice.getItemById(id);
		if(quantity>0) {
			item.setQuantity(quantity);
			item.setSize(size);
		} else {
			cartservice.deleteItem(id);
		}
		return "redirect:/cart";
	}
	
	@RequestMapping(value="/cart/order/add", method = RequestMethod.GET)
	public String orderItem(Model model) {
		countItems(model);
		if(!cartservice.listAllItems().isEmpty()) {
			List<ItemDTO> list = cartservice.listAllItems();
			orderedservice.orderCartItems(list);
			cartservice.clearCart();
			model.addAttribute("pizzaSize", PizzaSize.class);
			model.addAttribute("listAllOrderedItems", orderedservice.listAllOrderedItems());
			
		}
		
		return "order";
		
	}
	
	@RequestMapping("/cart/order")
	public String listOrderedItems(Model model) {
		countItems(model);
		model.addAttribute("pizzaSize", PizzaSize.class);
		model.addAttribute("listAllOrderedItems", orderedservice.listAllOrderedItems());
		return "order";
		
	}
	
	public void countItems(Model model) {
		List<ItemDTO> items = cartservice.listAllItems();
		model.addAttribute("listAllItems", items);
		BigDecimal price= new BigDecimal(0);
		for(ItemDTO itemDTO : items) {
			price = price.add(itemDTO.getPrice());
		}
		model.addAttribute("totalprice", price);
	}
}
