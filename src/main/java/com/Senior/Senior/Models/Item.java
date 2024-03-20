package com.Senior.Senior.Models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID idItem;

    private Integer quantidade;

    private Double valorItemTotal;

    @ManyToOne(fetch = FetchType.EAGER)
    private Produto produto;

    public UUID getIdItem() {
        return idItem;
    }

    public void setIdItem(UUID idItem) {
        this.idItem = idItem;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorItemTotal() {
        return valorItemTotal;
    }

    public void setValorItemTotal(Double valorItemTotal) {
        this.valorItemTotal = valorItemTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
