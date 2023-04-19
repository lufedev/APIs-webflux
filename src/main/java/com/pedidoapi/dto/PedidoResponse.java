package com.pedidoapi.dto;

import com.pedidoapi.entity.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(String id, List<ItemDTO> itens, Pedido.Status status){
}
