package github.com.andrelsa.service.impl;

import github.com.andrelsa.domain.repository.PedidoRepository;
import github.com.andrelsa.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {
	private PedidoRepository pedidoRepository;
	
	public PedidoServiceImpl(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}
	
}
