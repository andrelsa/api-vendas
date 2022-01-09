package github.com.andrelsa;

import github.com.andrelsa.domain.entity.Cliente;
import github.com.andrelsa.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class VendasApplication {
	
	@Bean
	public CommandLineRunner commandLineRunner(@Autowired ClienteRepository clienteRepository) {
		return args -> {
			Cliente cliente = new Cliente(null, "Fulano");
			clienteRepository.save(cliente);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
	
}
