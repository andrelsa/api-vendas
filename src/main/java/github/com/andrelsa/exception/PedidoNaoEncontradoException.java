package github.com.andrelsa.exception;

public class PedidoNaoEncontradoException extends RuntimeException {
	
	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado.");
	}
	
}
