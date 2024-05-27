package br.thiago.entrada_saida_pessoas.usuarios;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.thiago.entrada_saida_pessoas.usuarios.model.User;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.thiago.entrada_saida_pessoas.usuarios.repository")
@EntityScan(basePackages = "br.thiago.entrada_saida_pessoas.usuarios.model")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		User user = new User();
		user.setName("admin");
		user.setPassword("admin123");
		user.setRole("ROLE_ADMIN");
	}

}
