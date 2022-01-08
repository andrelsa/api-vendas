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
			clienteRepository.salvar(new Cliente("André"));
			clienteRepository.salvar(new Cliente("Gabi"));
			
			List<Cliente> clienteList = clienteRepository.obterTodos();
			clienteList.forEach(System.out::println);
			
			System.out.println("Atualizando clientes");
			clienteList.forEach(cliente -> {
				cliente.setNome(cliente.getNome() + " atualizado");
				clienteRepository.atualizar(cliente);
			});
			
			clienteList = clienteRepository.obterTodos();
			clienteList.forEach(System.out::println);
			
			System.out.println("Buscando clientes");
			clienteRepository.obterPorNome("Gabi").forEach(System.out::println);
			
			System.out.println("Deletando clientes");
			clienteRepository.obterTodos().forEach(cliente -> {
				clienteRepository.excluir(cliente);
			});
			
			clienteList = clienteRepository.obterTodos();
			if (clienteList.isEmpty()) {
				System.out.println("Nenhum cliente encontrado!!!!");
			} else {
				clienteList.forEach(System.out::println);
			}
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
	
}
