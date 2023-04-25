package com.letscode.estoque;

import com.letscode.estoque.model.Estoque;
import com.letscode.estoque.repository.EstoqueRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class EstoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstoqueApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(EstoqueRepository estoqueRepository){
		return args -> {
			Estoque estoque1 = new Estoque();
			estoque1.setCodigoDeBarras("Samsung_S22");
			estoque1.setQuantidade(4);

			Estoque estoque2 = new Estoque();
			estoque2.setCodigoDeBarras("RTX_4090");
			estoque2.setQuantidade(0);

			estoqueRepository.save(estoque1);
			estoqueRepository.save(estoque2);
		};
	}

}
