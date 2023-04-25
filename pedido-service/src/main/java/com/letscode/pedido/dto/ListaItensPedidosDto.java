package com.letscode.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaItensPedidosDto {
    private Long id;
    private String codigoDeBarras;
    private BigDecimal preco;
    private Integer quantidade;
}
