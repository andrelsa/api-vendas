package github.com.andrelsa;

import github.com.andrelsa.domain.entity.Cliente;
import github.com.andrelsa.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication()
public class SalesApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired ClientRepository clientRepository) {
		return args -> {
			clientRepository.save(new Cliente("André"));
			clientRepository.save(new Cliente("Gabi"));
			
			List<Cliente> clienteList = clientRepository.getAll();
			clienteList.forEach(System.out::println);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}
	
}
