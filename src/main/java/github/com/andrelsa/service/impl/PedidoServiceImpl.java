package github.com.andrelsa.service.impl;

import github.com.andrelsa.domain.entity.Cliente;
import github.com.andrelsa.domain.entity.ItemPedido;
import github.com.andrelsa.domain.entity.Pedido;
import github.com.andrelsa.domain.entity.Produto;
import github.com.andrelsa.domain.repository.ClienteRepository;
import github.com.andrelsa.domain.repository.ItemPedidoRepository;
import github.com.andrelsa.domain.repository.PedidoRepository;
import github.com.andrelsa.domain.repository.ProdutoRepository;
import github.com.andrelsa.exception.RegraNegocioException;
import github.com.andrelsa.rest.dto.ItemPedidoDTO;
import github.com.andrelsa.rest.dto.PedidoDTO;
import github.com.andrelsa.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
	
	private final PedidoRepository pedidoRepository;
	private final ClienteRepository clienteRepository;
	private final ProdutoRepository produtoRepository;
	private final ItemPedidoRepository itemPedidoRepository;
	private final String CODIGO_CLIENTE_INVALIDO = "Código de cliente invállido.";
	private final String CODIGO_PRODUTO_INVALIDO = "Código de produto inválido: ";
	
	@Transactional
	@Override
	public Pedido salvar(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new RegraNegocioException(CODIGO_CLIENTE_INVALIDO));
		
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		
		List<ItemPedido> itensPedidos = converterItens(pedido, dto.getItems());
		pedidoRepository.save(pedido);
		itemPedidoRepository.saveAll(itensPedidos);
		pedido.setItens(itensPedidos);
		
		return pedido;
	}
	
	private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens) {
		if (itens.isEmpty()) {
			throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
		}
		
		return itens.stream().map(itemPedidoDTO -> {
			Integer idProduto = itemPedidoDTO.getProduto();
			Produto produto = produtoRepository.findById(idProduto)
					.orElseThrow(() -> new RegraNegocioException(CODIGO_PRODUTO_INVALIDO + idProduto));
			
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			return itemPedido;
		}).collect(Collectors.toList());
		
	}
	
}
