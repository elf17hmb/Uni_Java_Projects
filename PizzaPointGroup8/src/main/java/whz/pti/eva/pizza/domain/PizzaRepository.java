package whz.pti.eva.pizza.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PizzaRepository  extends JpaRepository<Pizza, Long>{
	Optional<Pizza> findPizzaByName(String name);
}
