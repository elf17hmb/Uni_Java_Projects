package whz.pti.eva.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import whz.pti.eva.customer.domain.Customer;
import whz.pti.eva.customer.domain.CustomerRepository;
import whz.pti.eva.deliveryAddress.domain.DeliveryAddress;
import whz.pti.eva.pizza.domain.Pizza;
import whz.pti.eva.pizza.domain.PizzaRepository;
import whz.pti.eva.security.domain.UserCreateForm;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	CustomerRepository customerRepo;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}

	@Override
	public List<Customer> listAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public void createCustomer(UserCreateForm form) {
		Customer customer = new Customer();
		customer.setLoginName(form.getLoginName());
		customer.setEmail(form.getEmail());
		customerRepo.save(customer);
		
	}

	@Override
	public Customer getByLoginName(String loginName) {
		return customerRepo.findByLoginName(loginName).orElse(null);
	}

	@Override
	public List<DeliveryAddress> listAllAddressesById(Long id) {
		Customer customer =customerRepo.findById(id).get();
		return customer.getDeliveryAdress();
	}

	

}
