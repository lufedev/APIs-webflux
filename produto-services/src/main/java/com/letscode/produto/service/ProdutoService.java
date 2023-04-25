package com.letscode.produto.service;

import com.letscode.produto.dto.ProdutoRequest;
import com.letscode.produto.dto.ProdutoResponse;
import com.letscode.produto.model.Produto;
import com.letscode.produto.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    public void criaProduto(ProdutoRequest produtoRequest){
        Produto produto = Produto.builder()
                .nome(produtoRequest.getNome())
                .descricao(produtoRequest.getDescricao())
                .valor(produtoRequest.getValor())
                .build();

        produtoRepository.save(produto);
        log.info("Produto " + produto.getId() + " adicionado!");
    }

    public List<ProdutoResponse> getAllProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(this::mapToProdutoResponse).toList();
    }

    private ProdutoResponse mapToProdutoResponse(Produto produto) {
        return ProdutoResponse.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .valor(produto.getValor())
                .build();

    }
}
