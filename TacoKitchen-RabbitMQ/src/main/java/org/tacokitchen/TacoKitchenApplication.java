package org.tacokitchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
public class TacoKitchenApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoKitchenApplication.class, args);
	}

}
