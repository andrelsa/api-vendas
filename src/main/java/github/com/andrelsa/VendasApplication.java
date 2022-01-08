package github.com.andrelsa;

import github.com.andrelsa.domain.entity.Cliente;
import github.com.andrelsa.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication()
public class VendasApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository clienteRepository) {
		return args -> {
			System.out.println("Salvando clientes");
			clienteRepository.save(new Cliente("André"));
			clienteRepository.save(new Cliente("Gabi"));
			
			List<Cliente> clientes = clienteRepository.encontrarPorNome("Gabi");
			clientes.forEach(System.out::println);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
	
}
