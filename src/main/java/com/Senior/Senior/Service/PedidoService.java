package com.Senior.Senior.Service;

import com.Senior.Senior.Models.Item;
import com.Senior.Senior.Models.Pedido;
import com.Senior.Senior.Models.Produto;
import com.Senior.Senior.Repositories.PedidoCustomRepository;
import com.Senior.Senior.Repositories.PedidoRepository;
import com.Senior.Senior.Repositories.ProdutoCustomRepository;
import com.Senior.Senior.Repositories.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final PedidoCustomRepository pedidoCustomRepository;


    public PedidoService(PedidoRepository pedidoRepository, PedidoCustomRepository pedidoCustomRepository) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoCustomRepository = pedidoCustomRepository;
    }

    public Page<Pedido> listPage(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    public List<Pedido> findByFiltros(String statusPedido, Double valorTotalComDesconto, Double valorTotalSemDesconto, String descricaoPedido) {
        return pedidoCustomRepository.findByFiltros(statusPedido, valorTotalComDesconto, valorTotalSemDesconto,descricaoPedido);
    }

    public Double calcularValorTotalComDesconto(Double porcetagemDesconto, Collection<Item>items){
        Double valorTotalSomadoProduto = 0.0;
        Double valorTotalSomadoServico = 0.0;
        Double valorFinalComDesconto = 0.0;
        Double retornoValoresFinal = 0.0;
        for(Item item : items){
            if(item.getProduto().getIsProduto().equals("true")){
                valorTotalSomadoProduto = valorTotalSomadoProduto + item.getValorItemTotal();
            }else{
                valorTotalSomadoServico = valorTotalSomadoServico + item.getValorItemTotal();
            }
        }

            valorFinalComDesconto = valorTotalSomadoProduto - (valorTotalSomadoProduto*porcetagemDesconto/100);

            retornoValoresFinal = valorFinalComDesconto + valorTotalSomadoServico;

        return retornoValoresFinal;
    }

    public  Double calcularValorTotalSemDesconto(Double porcetagemDesconto, Collection<Item>items){
        Double valorTotalSemDesconto = 0.0;
        for(Item item : items){
            valorTotalSemDesconto = valorTotalSemDesconto + item.getValorItemTotal();
        }
        return valorTotalSemDesconto;
    }
}
