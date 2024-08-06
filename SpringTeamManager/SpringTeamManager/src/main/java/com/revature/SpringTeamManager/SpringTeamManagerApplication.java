package com.revature.SpringTeamManager;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication(scanBasePackages = "com.revature")
@EntityScan("com.revature.Model")
@EnableJpaRepositories("com.revature.Repos")
public class SpringTeamManagerApplication {

	public static void main(String[] args) {
		// Load .env file
		Dotenv dotenv = Dotenv.load();

		// Set environment variables if not already set
		dotenv.entries().forEach(entry -> {
			if (System.getenv(entry.getKey()) == null) {
				System.setProperty(entry.getKey(), entry.getValue());
			}
		});

		SpringApplication.run(SpringTeamManagerApplication.class, args);
	}

}
