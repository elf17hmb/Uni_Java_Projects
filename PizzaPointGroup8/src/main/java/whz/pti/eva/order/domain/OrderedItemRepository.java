package whz.pti.eva.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import whz.pti.eva.item.domain.Item;

public interface OrderedItemRepository  extends JpaRepository<OrderedItem, Long>{

}
