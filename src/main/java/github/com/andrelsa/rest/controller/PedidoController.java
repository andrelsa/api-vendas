package github.com.andrelsa.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import github.com.andrelsa.domain.entity.ItemPedido;
import github.com.andrelsa.domain.entity.Pedido;
import github.com.andrelsa.rest.dto.InformacaoItemPedidoDTO;
import github.com.andrelsa.rest.dto.InformacoesPedidoDTO;
import github.com.andrelsa.rest.dto.PedidoDTO;
import github.com.andrelsa.service.PedidoService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	private final PedidoService pedidoService;
	
	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Integer salvar(@RequestBody PedidoDTO dto) {
		Pedido pedido = pedidoService.salvar(dto);
		return pedido.getId();
		
	}
	
	@GetMapping("/{id}")
	public InformacoesPedidoDTO getById(@PathVariable Integer id) {
		return pedidoService.obterPedidoCompleto(id).map(pedido -> converter(pedido))
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
	}
	
	private InformacoesPedidoDTO converter(Pedido pedido) {
		return InformacoesPedidoDTO
				.builder()
				.codigo(pedido.getId())
				.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
				.cpf(pedido.getCliente().getCpf())
				.nomeCliente(pedido.getCliente().getNome())
				.total(pedido.getTotal())
				.items(converter(pedido.getItens()))
				.build();
		
	}
	
	private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens) {
		if (CollectionUtils.isEmpty(itens)){
			return Collections.emptyList();
		}
		
		return itens
				.stream()
				.map(itemPedido -> InformacaoItemPedidoDTO
						.builder()
						.descricaoProduto(itemPedido.getProduto().getDescricao())
						.precoUnitario(itemPedido.getProduto().getPreco())
						.quantidade(itemPedido.getQuantidade())
						.build()
				).collect(Collectors.toList());
	}
	
}
