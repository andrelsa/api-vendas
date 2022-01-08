package github.com.andrelsa.domain.repository;

import github.com.andrelsa.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Query(value = "SELECT * FROM Cliente C WHERE C.nome LIKE '%:nome%'", nativeQuery = true)
	List<Cliente> encontrarPorNome(@Param("nome") String nome);
	//List<Cliente> findByNomeContains(String nome);
	
	@Query(value = "DELETE FROM Cliente C WHERE C.nome =:nome")
	@Modifying
	void deleteByNome(String nome);
	
	boolean existsByNome(String nome);
	
}
