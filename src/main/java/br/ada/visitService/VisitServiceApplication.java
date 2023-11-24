package br.ada.visitService;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class VisitServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitServiceApplication.class, args);
	}

}
