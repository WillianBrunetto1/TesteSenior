package com.Senior.Senior.Repositories;

import com.Senior.Senior.Models.Pedido;
import com.Senior.Senior.Models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    Optional<Pedido> findByDescricaoPedido(String descricaoPedido);
}