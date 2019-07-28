package whz.pti.eva.config;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import whz.pti.eva.customer.domain.Customer;
import whz.pti.eva.customer.domain.CustomerRepository;
import whz.pti.eva.deliveryAddress.domain.DeliveryAddress;
import whz.pti.eva.pizza.domain.Pizza;
import whz.pti.eva.pizza.domain.PizzaRepository;
import whz.pti.eva.security.domain.Role;
import whz.pti.eva.security.domain.User;
import whz.pti.eva.security.domain.UserRepository;


@Component
public class InitializeDB {
	
    private static final Logger log = LoggerFactory.getLogger(InitializeDB.class);
    
    @Autowired
    private PizzaRepository pizzarepo;
    
    @Autowired
    private CustomerRepository customerrepo;
    
    @Autowired
    UserRepository userRepository;
    
    @PostConstruct
    public void init() {
    	log.debug("Db initialized");
    	
    	User user = new User();
        user.setLoginName("admin");
        user.setEmail("admin");
        user.setPasswordHash(new BCryptPasswordEncoder().encode("demo"));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    	
    	user = new User();
        user.setLoginName("elisa");
        user.setEmail("el@a");
        user.setPasswordHash(new BCryptPasswordEncoder().encode("demo"));
        user.setRole(Role.USER);
        userRepository.save(user);

        user = new User();
        user.setLoginName("marga");
        user.setEmail("ma@a");
        user.setPasswordHash(new BCryptPasswordEncoder().encode("demo"));
        user.setRole(Role.USER);
        userRepository.save(user);

        user = new User();
        user.setLoginName("frieda");
        user.setEmail("fr@a");
        user.setPasswordHash(new BCryptPasswordEncoder().encode("demo"));
        user.setRole(Role.USER);
        userRepository.save(user);
    	
    	pizzarepo.save(new Pizza("Salami",new BigDecimal(10),new BigDecimal(12.5),new BigDecimal(15)));
    	pizzarepo.save(new Pizza("Hawaii",new BigDecimal(13.5),new BigDecimal(22.5),new BigDecimal(35)));
    	pizzarepo.save(new Pizza("Bacon&Eggs",new BigDecimal(11),new BigDecimal(13.99),new BigDecimal(999)));
    	pizzarepo.save(new Pizza("Veggie",new BigDecimal(100),new BigDecimal(200),new BigDecimal(355.99)));
    	
    	Customer elisa = new Customer("elisa","el@a");
    	Customer marga = new Customer("marga","ma@a");
    	Customer frieda = new Customer("frieda","fr@a");
    	Customer admin = new Customer("admin","admin");
    	
    	elisa.addDeliveryAddress(new DeliveryAddress("Bonhoefferstr", "123", "Plauen" ,"08562"));
    	elisa.addDeliveryAddress(new DeliveryAddress("Privet Drive", "4", "Little Whinging" ,"01453"));
    	elisa.setEmail("el@a");
    	elisa.setFirstName("Elisa");
    	elisa.setLastName("Seidel");
    	
    	marga.addDeliveryAddress(new DeliveryAddress("Innere Schneebergerstr", "410", "Zwickau" ,"08535"));
    	marga.setEmail("ma@a");
    	marga.setFirstName("Marga");
    	marga.setLastName("Musterfrau");
    	
    	frieda.addDeliveryAddress(new DeliveryAddress("Jibek-Joolu", "213", "Bischkek" ,"31234"));
    	frieda.setEmail("fr@a");
    	frieda.setFirstName("Frieda");
    	frieda.setLastName("Reiss");
    	
    	admin.addDeliveryAddress(new DeliveryAddress("Streng geheim", "999", "Madagaskar" ,"358562"));
    	admin.setEmail("admin");
    	admin.setFirstName("admin");
    	admin.setLastName("admin");
    	
    	customerrepo.save(elisa);
    	customerrepo.save(marga);
    	customerrepo.save(frieda);
    	customerrepo.save(admin);
    }

}
