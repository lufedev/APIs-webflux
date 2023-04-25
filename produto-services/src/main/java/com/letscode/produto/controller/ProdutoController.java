package com.letscode.produto.controller;

import com.letscode.produto.dto.ProdutoRequest;
import com.letscode.produto.dto.ProdutoResponse;
import com.letscode.produto.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criaProduto(@RequestBody ProdutoRequest produtoRequest){
        produtoService.criaProduto(produtoRequest);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoResponse> getAllProdutos(){
        return produtoService.getAllProdutos();
    }
}
