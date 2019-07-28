package whz.pti.eva.customer.service;

import java.util.List;

import whz.pti.eva.customer.domain.Customer;
import whz.pti.eva.deliveryAddress.domain.DeliveryAddress;
import whz.pti.eva.pizza.domain.Pizza;
import whz.pti.eva.security.domain.UserCreateForm;

public interface CustomerService {
	
	List<Customer> listAllCustomers();
	
	void createCustomer(UserCreateForm form);

	Customer getByLoginName(String loginName);
	
	List<DeliveryAddress> listAllAddressesById(Long id);
}
