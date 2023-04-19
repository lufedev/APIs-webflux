package com.produtoapi.controller;

import com.produtoapi.dto.ProdutoRequest;
import com.produtoapi.entity.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    @GetMapping
    public Flux<Produto> listarTodos() {
        return produtoRepository.buscarTodos();
    }

    @GetMapping("/{id}")
    public Mono<Produto> buscarPorId(@PathVariable String id) {
        return produtoRepository.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> criar(@RequestBody ProdutoRequest produtoRequest) {
        Produto produto = new Produto(
                UUID.randomUUID().toString(),
                produtoRequest.nome(),
                produtoRequest.preco(),
                produtoRequest.quantidade()
        );
        return produtoRepository.salvar(produto).then();
    }

    @PutMapping("/{id}")
    public Mono<Produto> atualizar(@PathVariable String id, @RequestBody ProdutoRequest produtoRequest) {
        return produtoRepository.buscarPorId(id)
                .flatMap(produto -> {
                    produto.setNome(produtoRequest.nome());
                    produto.setPreco(produtoRequest.preco());
                    produto.setQuantidade(produtoRequest.quantidade());
                    return produtoRepository.salvar(produto);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletar(@PathVariable String id) {
        return produtoRepository.deletar(id);
    }

}
