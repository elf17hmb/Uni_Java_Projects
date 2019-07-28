package whz.pti.eva.security.boundary;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import whz.pti.eva.customer.domain.Customer;
import whz.pti.eva.customer.service.CustomerServiceImpl;
import whz.pti.eva.deliveryAddress.domain.DeliveryAddress;
import whz.pti.eva.security.domain.User;
import whz.pti.eva.security.domain.UserCreateForm;
import whz.pti.eva.security.service.dto.UserDTO;
import whz.pti.eva.security.service.user.UserService;
import whz.pti.eva.security.service.validator.UserCreateFormValidator;


@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private CustomerServiceImpl customerService;
    private UserCreateFormValidator userCreateFormValidator;

    @Autowired
    public UserController(UserService userService, CustomerServiceImpl chatUserService, UserCreateFormValidator userCreateFormValidator) {
        this.userService = userService;
        this.customerService = chatUserService;
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @InitBinder("myform")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/users")
    public String getUsersPage(Model model) {
        log.info("Getting users page");
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("customers", customerService.listAllCustomers());
        return "users";
    } 

    @PreAuthorize("#id == principal.id or hasAuthority('ADMIN')")
    @RequestMapping(value = "/users/customers/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String getUserPage(@RequestParam Optional<String> error,@PathVariable Long id, Model model) {
        log.debug("Getting user page for user= " + id);
        UserDTO userDTO = userService.getUserById(id);
        User user = userService.getUserByEmail(userDTO.getEmail()).get();
        Customer customer = customerService.getByLoginName(userDTO.getLoginName());
        List<DeliveryAddress> dalist = customer.getDeliveryAdress();
        model.addAttribute("customer", customer);
        model.addAttribute("user", user);
        model.addAttribute("deliveryAddressList", dalist);
        return "customer";
    }


    @RequestMapping(value = "/users/register")
    public String registerNewUser(@RequestParam Optional<String> error,Model model) {
    	log.debug("PizzaPoint Register");
		return "user_create";
    }

 
//    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("myform") UserCreateForm form, BindingResult bindingResult, Model model) {
        log.info("Processing user create form= " + form + " bindingResult= " + bindingResult);
        model.addAttribute("users", userService.getAllUsers());
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getGlobalError().getDefaultMessage());
            return "user_create";
        }
            userService.create(form);
            customerService.createCustomer(form);
        return "redirect:/login";
    }


}
