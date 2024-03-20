package com.Senior.Senior.Repositories;

import com.Senior.Senior.Models.Pedido;
import com.Senior.Senior.Models.Produto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PedidoRepositoryTest {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Pedido Cadastrado no banco")
    @Transactional
    void cadastroPedido(){

        Pedido pedido = new Pedido();
        pedido.setStatusPedido("Aberto");
        pedido.setValorTotalSemDesconto(100.0);
        pedido.setValorTotalComDesconto(90.0);
        pedido.setDescricaoPedido("Teste pedido desct");
        pedidoRepository.save(pedido);

        Optional<Pedido> produtoResult = this.pedidoRepository.findByDescricaoPedido(pedido.getDescricaoPedido());

        assertThat(produtoResult.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Pedido nao cadastrado no banco")
    @Transactional
    void cadastroPedidoCase2(){

        Pedido pedido = new Pedido();
        pedido.setStatusPedido("Aberto");
        pedido.setValorTotalSemDesconto(100.0);
        pedido.setValorTotalComDesconto(90.0);
        pedido.setDescricaoPedido("Teste pedido desct");

        Optional<Pedido> produtoResult = this.pedidoRepository.findByDescricaoPedido(pedido.getDescricaoPedido());

        assertThat(produtoResult.isEmpty()).isTrue();
    }

}