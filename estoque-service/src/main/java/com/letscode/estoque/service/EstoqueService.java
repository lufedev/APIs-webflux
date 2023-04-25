package com.letscode.estoque.service;

import com.letscode.estoque.dto.EstoqueResponse;
import com.letscode.estoque.repository.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    @Transactional(readOnly = true)
    public List<EstoqueResponse> emEstoque(List<String> codigoDeBarras){
        return estoqueRepository.findByCodigoDeBarrasIn(codigoDeBarras).stream()
                .map(estoque ->
                    EstoqueResponse.builder()
                            .codigoDeBarras(estoque.getCodigoDeBarras())
                            .emEstoque(estoque.getQuantidade() > 0)
                            .build()
                ).toList();
    }
}
