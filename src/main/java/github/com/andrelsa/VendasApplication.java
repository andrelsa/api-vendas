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
	public CommandLineRunner init(@Autowired ClienteRepository clienteRepository) {
		return args -> {
			System.out.println("Salvando clientes");
			clienteRepository.save(new Cliente("André"));
			clienteRepository.save(new Cliente("Gabi"));
			
			boolean existe = clienteRepository.existsByNome("Gabi");
			System.out.println("existe um cliente com o nome Gabi? " + existe);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
	
}
