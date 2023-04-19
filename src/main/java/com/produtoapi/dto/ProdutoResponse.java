package com.produtoapi.dto;
p
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ProdutoResponse(
        @JsonProperty("id")
        String id,
        @JsonProperty("nome")
        String nome,
        @JsonProperty("preco")
        BigDecimal preco,
        @JsonProperty("quantidade")
        int quantidade
) {}
