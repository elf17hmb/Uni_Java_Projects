package whz.pti.eva.cart.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import whz.pti.eva.pizza.domain.Pizza;

public interface CartRepository  extends JpaRepository<Cart, Long>{

}
