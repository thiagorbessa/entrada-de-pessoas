package Excutavel;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "br.thiago.entrada_saida_pessoas.usuarios.repository")
@EntityScan(basePackages = "br.thiago.entrada_saida_pessoas.usuarios.model")
@SpringBootApplication(scanBasePackages = {"estudos.thiago.lab_padroes_spring", "br.thiago.entrada_saida_pessoas.usuarios"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}

}
