package com.letscode.pedido.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "t_lista_itens_pedidos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ListaItensPedidos {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String codigoDeBarras;
        private BigDecimal preco;
        private Integer quantidade;

}
