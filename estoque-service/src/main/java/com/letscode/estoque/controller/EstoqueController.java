package com.letscode.estoque.controller;

import com.letscode.estoque.dto.EstoqueResponse;
import com.letscode.estoque.service.EstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoque")
@RequiredArgsConstructor
public class EstoqueController {

    private final EstoqueService estoqueService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EstoqueResponse> emEstoque(@RequestParam List<String> codigoDeBarras){
        return estoqueService.emEstoque(codigoDeBarras);
    }
}
