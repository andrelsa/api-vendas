package github.com.andrelsa.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import github.com.andrelsa.domain.entity.Produto;
import github.com.andrelsa.domain.repository.ProdutoRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	private final ProdutoRepository produtoRepository;
	
	public ProdutoController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Produto salvar(@RequestBody @Valid Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody @Valid Produto produto) {
		produtoRepository.findById(id).map(p -> {
			produto.setId(p.getId());
			produtoRepository.save(produto);
			return produto;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void excluir(@PathVariable Integer id) {
		produtoRepository.findById(id).map(p -> {
			produtoRepository.deleteById(id);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}
	
	@GetMapping("/{id}")
	public Produto obterPorId(@PathVariable Integer id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}
	
	@GetMapping
	public List<Produto> buscar(Produto filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		
		Example example = Example.of(filtro, matcher);
		return produtoRepository.findAll(example);
	}
	
}
