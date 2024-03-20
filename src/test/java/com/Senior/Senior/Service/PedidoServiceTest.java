package com.Senior.Senior.Service;

import com.Senior.Senior.Models.Item;
import com.Senior.Senior.Models.Produto;
import com.Senior.Senior.Repositories.ItemRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PedidoServiceTest {

    @Autowired
    PedidoService pedidoService;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Calculo efetuado com sucesso")
    void calcularValorTotalSemDesconto() {
        Double valorDesconto = 10.0;
        Collection<Item> itens = new ArrayList<>();
        Produto produto = new Produto();
        produto.setNome("teste");
        produto.setDescricao("Teste Desc");
        produto.setIsProduto("true");
        //Item 1 do pedido
        Item item = new Item();
        item.setValorItemTotal(1000.0);
        item.setQuantidade(2);
        item.setProduto(produto);
        itens.add(item);
        //Item 2 do pedido
        Produto produto2 = new Produto();
        produto2.setNome("teste");
        produto2.setDescricao("Teste Desc");
        produto2.setIsProduto("false");
        Item item2 = new Item();
        item2.setValorItemTotal(100.0);
        item2.setQuantidade(2);
        item2.setProduto(produto2);
        itens.add(item2);

        Double valorteste  = pedidoService.calcularValorTotalSemDesconto(valorDesconto, itens);

        if(valorteste.equals(1100.0)){
            assertTrue(true);
        }else{
            assertTrue(false);
        }
    }

    @Test
    @DisplayName("Calculo efetuado com sucesso")
    void calcularValorTotalComDesconto() {
        Double valorDesconto = 10.0;
        Collection<Item> itens = new ArrayList<>();
        Produto produto = new Produto();
        produto.setNome("teste");
        produto.setDescricao("Teste Desc");
        produto.setIsProduto("true");
        //Item 1 do pedido
        Item item = new Item();
        item.setValorItemTotal(1000.0);
        item.setQuantidade(2);
        item.setProduto(produto);
        itens.add(item);
        //Item 2 do pedido
        Produto produto2 = new Produto();
        produto2.setNome("teste");
        produto2.setDescricao("Teste Desc");
        produto2.setIsProduto("false");
        Item item2 = new Item();
        item2.setValorItemTotal(100.0);
        item2.setQuantidade(2);
        item2.setProduto(produto2);
        itens.add(item2);

        Double valorteste  = pedidoService.calcularValorTotalComDesconto(valorDesconto, itens);

        if(valorteste.equals(1000.0)){
            assertTrue(true);
        }else{
            assertTrue(false);
        }
    }
}