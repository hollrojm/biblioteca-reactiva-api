package co.com.sofkau.bibliotecareactiva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages="co.com.sofkau.bibliotecareactiva")
public class BibliotecareactivaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecareactivaApplication.class, args);
	}

}
