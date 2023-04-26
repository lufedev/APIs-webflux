package com.letscode.estoque.service;

import com.letscode.estoque.dto.EstoqueResponse;
import com.letscode.estoque.model.Estoque;
import com.letscode.estoque.repository.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    @Transactional
    public List<EstoqueResponse> emEstoque(List<String> codigoDeBarras, List<Integer> quantidade){
        List<EstoqueResponse> estoqueResponses = new ArrayList<>();
        for (int i = 0; i < codigoDeBarras.size(); i++) {
            String codigo = codigoDeBarras.get(i);
            int qtdSolicitada = quantidade.get(i);
            Optional<Estoque> optionalEstoque = estoqueRepository.findByCodigoDeBarras(codigo);
            if (optionalEstoque.isPresent()) {
                Estoque estoque = optionalEstoque.get();
                int qtdDisponivel = estoque.getQuantidade();
                boolean emEstoque = qtdDisponivel >= qtdSolicitada;

                if(emEstoque) {
                    estoque.setQuantidade(estoque.getQuantidade() - qtdSolicitada);
                    estoqueResponses.add(EstoqueResponse.builder()
                            .codigoDeBarras(codigo)
                            .isEmEstoque(emEstoque)
                            .build());
                }


            } else {
                // Produto não encontrado no estoque ou estoque insuficiente
                estoqueResponses.add(EstoqueResponse.builder()
                        .codigoDeBarras(codigo)
                        .quantidade(qtdSolicitada)
                        .isEmEstoque(false)
                        .build());
            }
        }
        System.out.println("SUCESSO");
        return estoqueResponses;
    }
}
