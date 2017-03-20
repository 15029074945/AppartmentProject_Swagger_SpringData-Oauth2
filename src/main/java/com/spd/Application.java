package com.spd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableAuthorizationServer
@EnableResourceServer
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
