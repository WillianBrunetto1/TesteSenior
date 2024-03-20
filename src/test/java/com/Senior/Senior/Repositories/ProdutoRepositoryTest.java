package com.Senior.Senior.Repositories;

import com.Senior.Senior.Dtos.ProdutoDTO;
import com.Senior.Senior.Models.Produto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProdutoRepositoryTest {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Produto Cadastrado no banco")
    @Transactional
    void cadastroProduto(){

        Produto produto = new Produto();
        produto.setNome("teste Nome");
        produto.setStatusProduto("ativado");
        produto.setDescricao("teste Descicao");
        produto.setPreco(100.0);
        produto.setIsProduto("true");
        produtoRepository.save(produto);

        Optional<Produto>  produtoResult = this.produtoRepository.findByNome(produto.getNome());

       assertThat(produtoResult.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Produto nao cadastrado no banco")
    @Transactional
    void cadastroProdutoCase2(){

        Produto produto = new Produto();
        produto.setNome("teste Nome");
        produto.setStatusProduto("ativado");
        produto.setDescricao("teste Descicao");
        produto.setPreco(100.0);
        produto.setIsProduto("true");

        Optional<Produto>  produtoResult = this.produtoRepository.findByNome(produto.getNome());

        assertThat(produtoResult.isEmpty()).isTrue();
    }

}