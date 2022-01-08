package github.com.andrelsa.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Column(name = "data_pedido")
	private LocalDate dataPedido;
	
	@Column(name = "total_pedido", length = 20, precision = 2)
	private BigDecimal totalPedido;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public LocalDate getDataPedido() {
		return dataPedido;
	}
	
	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	public BigDecimal getTotalPedido() {
		return totalPedido;
	}
	
	public void setTotalPedido(BigDecimal totalPedido) {
		this.totalPedido = totalPedido;
	}
	
	public List<ItemPedido> getItens() {
		return itens;
	}
	
	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
	
}
