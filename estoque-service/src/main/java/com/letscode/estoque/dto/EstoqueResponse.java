package com.letscode.estoque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstoqueResponse {
    private String codigoDeBarras;
    private Integer quantidade;
    private boolean isEmEstoque;
}
