package github.com.andrelsa.domain.repository;

import github.com.andrelsa.domain.entity.Cliente;
import github.com.andrelsa.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	List<Pedido> findByCliente(Cliente cliente);
	
	//Optional<Pedido> findByIdFetchItens(Integer id);
	
	@Query(" select p from Pedido p left join fetch p.itens where p.id = :id ")
	Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
	
}
