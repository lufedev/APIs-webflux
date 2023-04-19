package com.produtoapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ProdutoRequest(
        @JsonProperty("nome")
        String nome,
        @JsonProperty("preco")
        BigDecimal preco,
        @JsonProperty("quantidadeEstoque")
        int quantidadeEstoque
) {

}