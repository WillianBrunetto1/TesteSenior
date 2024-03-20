package com.Senior.Senior.Repositories;

import com.Senior.Senior.Models.Item;
import com.Senior.Senior.Models.Pedido;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ItemCustomRepository {
    private final EntityManager entityManager;

    public ItemCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Item> findByFiltros(Double valorItemTotal, Integer quantidade){

        String query = "select I from Item as I";
        String condicao = " where ";

        if(valorItemTotal !=null){
            query += condicao + "I.valorItemTotal = :valorItemTotal";
            condicao = " and ";
        }
        if(quantidade !=null){
            query += condicao + "I.quantidade = :quantidade";

        }

        var q = entityManager.createQuery(query, Item.class);

        if(valorItemTotal !=null){
            q.setParameter("valorItemTotal", valorItemTotal);
        }
        if(quantidade !=null){
            q.setParameter("quantidade", quantidade);
        }

        return q.getResultList();
    }
}
