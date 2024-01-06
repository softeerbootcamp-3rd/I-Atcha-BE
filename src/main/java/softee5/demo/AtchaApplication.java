package softee5.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AtchaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtchaApplication.class, args);
	}

}
