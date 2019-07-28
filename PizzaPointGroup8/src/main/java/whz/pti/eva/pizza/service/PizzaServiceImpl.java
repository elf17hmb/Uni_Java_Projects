package whz.pti.eva.pizza.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whz.pti.eva.pizza.domain.Pizza;
import whz.pti.eva.pizza.domain.PizzaRepository;

@Service
public class PizzaServiceImpl implements PizzaService{
	
	PizzaRepository pizzarepo;
	
	@Autowired
	public PizzaServiceImpl(PizzaRepository pizzarepo) {
		this.pizzarepo = pizzarepo;
	}

	@Override
	public List<Pizza> listAllPizzas() {
		return pizzarepo.findAll();
	}

	@Override
	public Pizza findPizzaByName(String name) {
		return pizzarepo.findPizzaByName(name).get();
	}

}
