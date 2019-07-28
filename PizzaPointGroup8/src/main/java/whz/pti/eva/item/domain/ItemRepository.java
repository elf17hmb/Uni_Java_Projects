package whz.pti.eva.item.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import whz.pti.eva.cart.domain.Cart;

public interface ItemRepository  extends JpaRepository<Item, Long>{

}
