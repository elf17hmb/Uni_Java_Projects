package whz.pti.eva.pizza.service;

import java.util.List;

import whz.pti.eva.pizza.domain.Pizza;

public interface PizzaService {
	
	List<Pizza> listAllPizzas();
	
	Pizza findPizzaByName(String name);

}
