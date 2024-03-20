package com.Senior.Senior.Repositories;

import com.Senior.Senior.Models.Pedido;
import com.Senior.Senior.Models.Produto;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PedidoCustomRepository {

    private final EntityManager entityManager;

    public PedidoCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Pedido> findByFiltros(String statusPedido, Double valorTotalComDesconto, Double valorTotalSemDesconto, String descricaoPedido){

        String query = "select P from Pedido as P";
        String condicao = " where ";

        if(statusPedido !=null){
            query += condicao + "P.statusPedido = :statusPedido";
            condicao = " and ";
        }
        if(valorTotalComDesconto !=null){
            query += condicao + "P.valorTotalComDesconto = :valorTotalComDesconto";
            condicao = " and ";
        }
        if(valorTotalSemDesconto !=null){
            query += condicao + "P.valorTotalSemDesconto = :valorTotalSemDesconto";
            condicao = " and ";
        }
        if(descricaoPedido !=null){
            query += condicao + "P.descricaoPedido = :descricaoPedido";
        }
        var q = entityManager.createQuery(query, Pedido.class);

        if(statusPedido !=null){
           q.setParameter("statusPedido", statusPedido);
        }
        if(valorTotalComDesconto !=null){
            q.setParameter("valorTotalComDesconto", valorTotalComDesconto);
        }
        if(valorTotalSemDesconto !=null){
            q.setParameter("valorTotalSemDesconto", valorTotalSemDesconto);
        }
        if(descricaoPedido !=null){
            q.setParameter("descricaoPedido", descricaoPedido);
        }
        return q.getResultList();
    }
}
