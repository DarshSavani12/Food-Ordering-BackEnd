package foodordering.foodorderingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
public class FoodOrderingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodOrderingAppApplication.class, args);
	}

}
