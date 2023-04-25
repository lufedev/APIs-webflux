package com.letscode.estoque.repository;

import com.letscode.estoque.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    List<Estoque> findByCodigoDeBarrasIn(List<String> codigoDeBarras);
}
