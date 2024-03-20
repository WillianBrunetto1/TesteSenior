package com.Senior.Senior.Repositories;

import com.Senior.Senior.Models.Item;
import com.Senior.Senior.Models.Pedido;
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
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Item Cadastrado no banco")
    @Transactional
    void cadastroItem(){
        Double valor = 100.0;
        Item item = new Item();
        item.setQuantidade(1);
        item.setValorItemTotal(valor);
        itemRepository.save(item);

        Optional<Item> ItemResult = this.itemRepository.findByValorItemTotal(item.getValorItemTotal());

        assertThat(ItemResult.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Item nao cadastrado no banco")
    @Transactional
    void cadastroItemCase2(){

        Double valor = 100.0;
        Item item = new Item();
        item.setQuantidade(1);
        item.setValorItemTotal(valor);

        Optional<Item> ItemResult = this.itemRepository.findByValorItemTotal(item.getValorItemTotal());

        assertThat(ItemResult.isEmpty()).isTrue();
    }
}