package brain.springframework.bookapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class bookappApplication {

	public static void main(String[] args) {
		SpringApplication.run(bookappApplication.class, args);
		System.out.println("Book App server now running....");
	}

}
