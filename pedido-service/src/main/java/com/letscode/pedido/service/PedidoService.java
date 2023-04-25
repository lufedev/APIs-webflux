package com.letscode.pedido.service;

import com.letscode.pedido.dto.EstoqueResponse;
import com.letscode.pedido.dto.ListaItensPedidosDto;
import com.letscode.pedido.dto.PedidoRequest;
import com.letscode.pedido.model.ListaItensPedidos;
import com.letscode.pedido.model.Pedido;
import com.letscode.pedido.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@Transactional
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final WebClient.Builder webClientBuilder;
    public void realizarPedido(PedidoRequest pedidoRequest){
        Pedido pedido = new Pedido();
        pedido.setNumeroPedido(UUID.randomUUID().toString());

        List<ListaItensPedidos> listaItensPedidos = pedidoRequest.getListaItensPedidosDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();


        pedido.setListaItensPedidos(listaItensPedidos);

        List<String> codigoDeBarras = pedido.getListaItensPedidos().stream()
                .map(ListaItensPedidos::getCodigoDeBarras)
                .toList();
        // Chama O Estoque, e realiza o pedido se o produto estiver no estoque.
      EstoqueResponse[] EstoqueResponseArray = webClientBuilder.build().get()
                        .uri("http://estoque-service/api/estoque",
                                uriBuilder -> uriBuilder.queryParam("codigoDeBarras",codigoDeBarras).build())
                        .retrieve()
                        .bodyToMono(EstoqueResponse[].class)
                        .block();

      boolean todosProdutosEmEstoque = Arrays.stream(EstoqueResponseArray)
              .allMatch(EstoqueResponse::isEmEstoque);

        if(todosProdutosEmEstoque){
           pedidoRepository.save(pedido);
        } else {
            throw new IllegalArgumentException("Produto fora de estoque!");
        }
    }

    private ListaItensPedidos mapToDto(ListaItensPedidosDto listaItensPedidosDto) {
        ListaItensPedidos listaItensPedidos = new ListaItensPedidos();
        listaItensPedidos.setPreco(listaItensPedidosDto.getPreco());
        listaItensPedidos.setQuantidade(listaItensPedidosDto.getQuantidade());
        listaItensPedidos.setCodigoDeBarras(listaItensPedidosDto.getCodigoDeBarras());
        return listaItensPedidos;
    }
}
