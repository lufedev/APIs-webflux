package com.letscode.pedido.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="t_pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String numeroPedido;

    private LocalDateTime dataPedido;
    private StatusPedido status;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ListaItensPedidos> listaItensPedidos;
}
