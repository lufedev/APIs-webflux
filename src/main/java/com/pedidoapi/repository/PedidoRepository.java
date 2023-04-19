package com.pedidoapi.repository;

import com.pedidoapi.entity.Pedido;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class PedidoRepository {
    private static final List<Pedido> PEDIDOS_BD
            = new CopyOnWriteArrayList<>();
    public void salvar(Pedido pedido) {
        PEDIDOS_BD.add(pedido);
    }
    public Optional<Pedido> buscarPorId(String id){
        return Optional.ofNullable(PEDIDOS_BD.stream()
                .filter(pedido -> pedido.id().equals(id))
                .findFirst()
                .orElseThrow());
    }
    public List<Pedido> buscarTodos(){
        return new ArrayList<>(PEDIDOS_BD);
    }

}
