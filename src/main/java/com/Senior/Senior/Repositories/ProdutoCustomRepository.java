package com.Senior.Senior.Repositories;

import com.Senior.Senior.Models.Produto;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoCustomRepository {

    private final EntityManager entityManager;

    public ProdutoCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Produto> findByNomeOrDescricaoOrPreco(String nome, String descricao, Double preco){

        String query = "select P from Produto as P";
        String condicao = " where ";

        if(nome !=null){
            query += condicao + "P.nome = :nome";
            condicao = " and ";
        }
        if(descricao !=null){
            query += condicao + "P.descricao = :descricao";
            condicao = " and ";
        }
        if(preco !=null){
            query += condicao + "P.preco = :preco";
        }
        var q = entityManager.createQuery(query, Produto.class);

        if(nome !=null){
           q.setParameter("nome", nome);
        }
        if(descricao !=null){
            q.setParameter("descricao", descricao);
        }
        if(preco !=null){
            q.setParameter("preco", preco);
        }
        return q.getResultList();
    }
}
