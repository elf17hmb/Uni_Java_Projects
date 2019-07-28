package whz.pti.eva.security.boundary;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam Optional<String> error, Model model) {
		log.debug("PizzaPoint Login");
		return "login";
	}

	@RequestMapping("/403")
	public String accessDenied() {
		return "error/403";
	}

}
