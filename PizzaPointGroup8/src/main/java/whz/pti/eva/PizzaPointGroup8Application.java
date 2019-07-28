package whz.pti.eva;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PizzaPointGroup8Application {

	public static void main(String[] args) {
		SpringApplication.run(PizzaPointGroup8Application.class, args);
	}
	
	@Bean
	CommandLineRunner init() {
		return (evt)-> System.out.println("Test");
	}
}
