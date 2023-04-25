package com.letscode.pedido.dto;

import com.letscode.pedido.model.ListaItensPedidos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {
    private List<ListaItensPedidosDto> listaItensPedidosDtoList;
}
