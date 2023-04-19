package com.produtoapi.repository;

import com.produtoapi.entity.Produto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository {
    private final List<Produto> produtos = new ArrayList<>();

    public Produto salvar(Produto produto) {
        produto.setId(produtos.size() + 1L);
        produtos.add(produto);
        return produto;
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtos.stream()
                .filter(produto -> produto.getId().equals(id))
                .findFirst();
    }

    public List<Produto> listar() {
        return produtos;
    }

    public List<Produto> buscarPorNome(String nome) {
        return produtos.stream()
                .filter(produto -> produto.getNome().toLowerCase().contains(nome.toLowerCase()))
                .toList();
    }

    public List<Produto> buscarPorPreco(BigDecimal precoMinimo, BigDecimal precoMaximo) {
        return produtos.stream()
                .filter(produto -> produto.getPreco().compareTo(precoMinimo) >= 0 &&
                        produto.getPreco().compareTo(precoMaximo) <= 0)
                .toList();
    }

    public void atualizarEstoque(Long id, int quantidade) {
        produtos.stream()
                .filter(produto -> produto.getId().equals(id))
                .findFirst()
                .ifPresent(produto -> produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade));
    }
}
