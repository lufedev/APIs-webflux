package com.letscode.pedido.controller;

import com.letscode.pedido.dto.PedidoRequest;
import com.letscode.pedido.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String realizarPedido(@RequestBody PedidoRequest pedidoRequest){
        pedidoService.realizarPedido(pedidoRequest);
        return "Pedido Realizado!";
    }
}
