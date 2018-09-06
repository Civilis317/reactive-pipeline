package net.playground.reactive.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PublisherApplication {

	public static void main(String[] args) {

		var s = "test";
		System.out.println("Hello, " + s);

		System.out.println(s instanceof String );

		SpringApplication.run(PublisherApplication.class, args);
	}
}
