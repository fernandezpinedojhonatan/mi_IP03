package tarea_IP03.tarea_IP03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TareaIp03Application {

	public static void main(String[] args) {
		SpringApplication.run(TareaIp03Application.class, args);
	}

}