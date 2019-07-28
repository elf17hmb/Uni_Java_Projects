package whz.pti.eva;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import whz.pti.eva.pizza.domain.Pizza;
import whz.pti.eva.pizza.service.PizzaService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PizzaControllerTest {
	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    
    //@MockBean
    //private PizzaService pizzaService;
    
    @Before
    public void setup() {
    	mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();    	
    }
    
    @Test
    public void getPizzasTest() throws Exception {
    	mockMvc.perform(get("/pizzas")
    			.contentType(MediaType.APPLICATION_JSON_VALUE))
    	.andExpect(status().isOk())
    	.andExpect(view().name("pizzas"))
    	.andExpect(model().attribute("listAllPizzas", hasSize(4)));    	
    }
    
    @Test
    public void addToCartTestNormal() throws Exception {    
    	System.out.println("add test");
    	mockMvc.perform(get("/pizzas/addToCart")
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
    		.param("quantity", "1")
    		.param("pizzaName", "Veggie")
    		.param("size", "Medium"))
    	.andExpect(status().is3xxRedirection());    
    }
    
    @Test
    public void addToCartTestZero() throws Exception {
    	mockMvc.perform(get("/addToCart")
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
    		.param("quantity", "0")
    		.param("pizzaName", "Veggie")
    		.param("size", "Small"))
    	.andExpect(status().is4xxClientError());    
    }
    
    @Test
    public void addToCartTestMinusOne() throws Exception {
    	mockMvc.perform(get("/addToCart")
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
    		.param("quantity", "-1")
    		.param("pizzaName", "Veggie")
    		.param("size", "Small"))
    	.andExpect(status().is4xxClientError());    
    }
    
    @Test
    public void addToCartTestText() throws Exception {
    	mockMvc.perform(get("/addToCart")
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
    		.param("quantity", "one")
    		.param("pizzaName", "Veggie")
    		.param("size", "Small"))
    	.andExpect(status().is4xxClientError());    
    }
    
    @Test
    public void addToCartTest999999() throws Exception {
    	mockMvc.perform(get("/addToCart")
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
    		.param("quantity", "999999")
    		.param("pizzaName", "Veggie")
    		.param("size", "Small"))
    	.andExpect(status().is4xxClientError());    
    }
    
    @Test
    public void addToCartTestPizzaDoesNotExist() throws Exception {
    	mockMvc.perform(get("/addToCart")
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
    		.param("quantity", "1")
    		.param("pizzaName", "Fake Pizza")
    		.param("size", "Small"))
    	.andExpect(status().is4xxClientError());    
    }
    
    @Test
    public void addToCartTestSizeDoesNotExist() throws Exception {
    	mockMvc.perform(get("/addToCart")
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
    		.param("quantity", "1")
    		.param("pizzaName", "Veggie")
    		.param("size", "Tiny"))
    	.andExpect(status().is4xxClientError());    
    }
    
}
