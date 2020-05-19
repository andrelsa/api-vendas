package com.dev.andrelsa.rest;

import com.dev.andrelsa.domain.entity.Produto;
import com.dev.andrelsa.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final transient Produtos produtosRepository;

    public ProdutoController(Produtos produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable Integer id) {
        return produtosRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FOUND, "Produto não encontrado"));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto produto) {
        return produtosRepository.save(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        produtosRepository.findById(id)
                .map(produto -> {
                    produtosRepository.delete(produto);
                    return produto;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.FOUND, "Produto não encontrado"));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id,
                       @RequestBody Produto produto) {
        produtosRepository
                .findById(id)
                .map(p -> {
                    produto.setId(p.getId());
                    produtosRepository.save(produto);
                    return p;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.FOUND, "Produto não encontrado"));
    }

    @GetMapping()
    public List<Produto> find(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        List<Produto> lista = produtosRepository.findAll(example);

        return produtosRepository.findAll(example);
    }
}
