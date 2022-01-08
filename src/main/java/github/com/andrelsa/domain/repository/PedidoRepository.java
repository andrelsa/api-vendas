package github.com.andrelsa.domain.repository;

import github.com.andrelsa.domain.entity.Cliente;
import github.com.andrelsa.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	List<Pedido> findByCliente(Cliente cliente);

}
