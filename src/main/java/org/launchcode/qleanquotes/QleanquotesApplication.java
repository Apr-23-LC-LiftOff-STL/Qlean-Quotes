package org.launchcode.qleanquotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("models.data")
public class QleanquotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(QleanquotesApplication.class, args);
	}

}
