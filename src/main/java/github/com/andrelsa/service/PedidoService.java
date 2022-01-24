package github.com.andrelsa.service;

import github.com.andrelsa.domain.entity.Pedido;
import github.com.andrelsa.domain.enums.StatusPedido;
import github.com.andrelsa.rest.dto.PedidoDTO;
import java.util.Optional;

public interface PedidoService {
	
	Pedido salvar(PedidoDTO dto);
	
	Optional<Pedido> obterPedidoCompleto(Integer id);
	
	void atualizaStatus(Integer id, StatusPedido statusPedido);
	
}
