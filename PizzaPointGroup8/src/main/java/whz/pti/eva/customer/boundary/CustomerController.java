package whz.pti.eva.customer.boundary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import whz.pti.eva.customer.domain.Customer;
import whz.pti.eva.customer.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/customers")
	public String listAllCustomers(Model model) {
		List<Customer> list = customerService.listAllCustomers();
//		List<DeliveryAdress> addressList = customerService.lista
		model.addAttribute("listAllCustomers", list);
		return "customers";
		
	}
	
	
}
