package com.egs.shopping;

import com.egs.shopping.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
	}

}
