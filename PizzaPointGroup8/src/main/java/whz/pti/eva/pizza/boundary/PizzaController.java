package whz.pti.eva.pizza.boundary;

import java.math.BigDecimal;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import whz.pti.eva.PizzaSize;
import whz.pti.eva.customer.domain.Customer;
import whz.pti.eva.customer.service.CustomerService;
import whz.pti.eva.cart.service.CartService;
import whz.pti.eva.item.domain.Item;
import whz.pti.eva.item.service.ItemDTO;
import whz.pti.eva.pizza.domain.Pizza;
import whz.pti.eva.pizza.service.PizzaService;
import whz.pti.eva.security.domain.CurrentUser;
import whz.pti.eva.security.service.user.UserService;


@Controller
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaservice;
	
	@Autowired
	private UserService userService;

	@Autowired 
	private CartService cartservice;
	
	@Autowired
	private CustomerService customerService;
	
	
	@RequestMapping(value = "/first", method = {RequestMethod.POST, RequestMethod.GET})
    public String firstPage( Model model) {

        return "redirect:pizzas";
    }
	
	@RequestMapping(value = { "/", "/pizzas" })
	public String listAllPizzas(Model model) {
		countItems(model);
		
		List<Pizza> list = pizzaservice.listAllPizzas();
		model.addAttribute("listAllPizzas", list);
		model.addAttribute("pizzaSize", PizzaSize.class);
		return "pizzas";
		
	}
	
	@RequestMapping(value ="/pizzas/addToCart", method = RequestMethod.GET)
	public String addToCart(@RequestParam int quantity, @RequestParam String pizzaName, @RequestParam PizzaSize size, Model model) {
		if(quantity>0) {
		Pizza pizza =pizzaservice.findPizzaByName(pizzaName);
		cartservice.addItem(pizza, quantity, size);
		model.addAttribute("pizza", pizza);}
		return "redirect:/pizzas";
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
