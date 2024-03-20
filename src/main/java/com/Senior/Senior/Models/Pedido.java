package com.Senior.Senior.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID idPedido;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime dataCadastro = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

    private Double valorTotalComDesconto;

    private Double valorTotalSemDesconto;

    public Double getValorTotalSemDesconto() {
        return valorTotalSemDesconto;
    }

    public void setValorTotalSemDesconto(Double valorTotalSemDesconto) {
        this.valorTotalSemDesconto = valorTotalSemDesconto;
    }

    private String statusPedido;



    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Double getValorTotalComDesconto() {
        return valorTotalComDesconto;
    }

    public void setValorTotalComDesconto(Double valorTotalComDesconto) {
        this.valorTotalComDesconto = valorTotalComDesconto;
    }

    private Double porcentagemDesconto;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Item> item;
    private String descricaoPedido;

    public Collection<Item> getItem() {
        return item;
    }

    public void setItem(Collection<Item> item) {
        this.item = item;
    }

    public String getDescricaoPedido() {
        return descricaoPedido;
    }

    public void setDescricaoPedido(String descricaoPedido) {
        this.descricaoPedido = descricaoPedido;
    }

    public UUID getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(UUID idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }


    public Double getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public void setPorcentagemDesconto(Double porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
    }
}
