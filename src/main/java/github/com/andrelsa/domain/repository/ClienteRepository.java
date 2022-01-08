package github.com.andrelsa.domain.repository;

import github.com.andrelsa.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	List<Cliente> findByNomeContains(String nome);
	
	boolean existsByNome(String nome);
	
}
