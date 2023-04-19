package com.pedidoapi.controller.handler;

import com.pedidoapi.PedidoService;
import com.pedidoapi.dto.PedidoRequest;
import com.pedidoapi.dto.PedidoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class PedidoHandler {
    private final PedidoService pedidoService;

    public Mono<ServerResponse> salvar(ServerRequest request){
        Mono<PedidoResponse> salvar = pedidoService.salvar(null);

       return request.bodyToMono(PedidoRequest.class)
                .subscribeOn(Schedulers.parallel())
                .flatMap(pedidoService::salvar)
                .flatMap(response ->
                        ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(response))
                );


    }

    public Mono<ServerResponse> obterPorId(ServerRequest request) {

        String id = request.pathVariable("id");

        var retorno = BodyInserters
                .fromPublisher(pedidoService.buscarPorId(id), PedidoResponse.class);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(retorno);
    }
}
