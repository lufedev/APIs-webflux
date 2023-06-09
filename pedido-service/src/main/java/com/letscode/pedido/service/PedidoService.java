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

import java.time.LocalDateTime;
import java.time.LocalTime;
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
        pedido.setDataPedido(LocalDateTime.now());
        List<ListaItensPedidos> listaItensPedidos = pedidoRequest.getListaItensPedidosDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();


        pedido.setListaItensPedidos(listaItensPedidos);

        List<String> codigoDeBarras = pedido.getListaItensPedidos().stream()
                .map(ListaItensPedidos::getCodigoDeBarras)
                .toList();
        List<Integer> quantidade = pedido.getListaItensPedidos().stream()
                .map(ListaItensPedidos::getQuantidade)
                .toList();

        System.out.println(codigoDeBarras + "" + quantidade);
        // Chama O Estoque, e realiza o pedido se o produto estiver no estoque.
      EstoqueResponse[] EstoqueResponseArray = webClientBuilder.build().get()
                        .uri("http://estoque-service/api/estoque",
                                uriBuilder ->
                                        uriBuilder.queryParam("codigoDeBarras",codigoDeBarras)
                                                  .queryParam("quantidade",quantidade)
                                                  .build())

                        .retrieve()
                        .bodyToMono(EstoqueResponse[].class)
                        .block();

        // Verifica se todos os produtos solicitados existem no estoque
        if (EstoqueResponseArray.length != codigoDeBarras.size()) {
            throw new IllegalArgumentException("Produto não existe no estoque!");
        }

        // Verifica se todos os produtos solicitados estão em estoque
        boolean todosProdutosEmEstoque = Arrays.stream(EstoqueResponseArray)
                .allMatch(EstoqueResponse::isEmEstoque);

        if (todosProdutosEmEstoque) {
            pedidoRepository.save(pedido);
        } else {
            throw new IllegalArgumentException("Produto fora de estoque!");
        }

    }

    private ListaItensPedidos mapToDto(ListaItensPedidosDto listaItensPedidosDto) {
        ListaItensPedidos listaItensPedidos = new ListaItensPedidos();
        listaItensPedidos.setPreco(listaItensPedidosDto.getPreco());
        listaItensPedidos.setQuantidade(listaItensPedidosDto.getQuantidade());
        listaItensPedidos.setPreco(listaItensPedidosDto.getPreco());
        listaItensPedidos.setCodigoDeBarras(listaItensPedidosDto.getCodigoDeBarras());
        return listaItensPedidos;
    }
}
