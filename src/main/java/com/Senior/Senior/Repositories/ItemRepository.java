package com.Senior.Senior.Repositories;

import com.Senior.Senior.Models.Item;
import com.Senior.Senior.Models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    Optional<Item> findByValorItemTotal(Double valorItemTotal);
}