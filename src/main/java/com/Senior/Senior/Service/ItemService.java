package com.Senior.Senior.Service;

import com.Senior.Senior.Models.Item;
import com.Senior.Senior.Models.Pedido;
import com.Senior.Senior.Models.Produto;
import com.Senior.Senior.Repositories.ItemCustomRepository;
import com.Senior.Senior.Repositories.ItemRepository;
import com.Senior.Senior.Repositories.PedidoCustomRepository;
import com.Senior.Senior.Repositories.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemCustomRepository itemCustomRepository;

    public ItemService(ItemRepository itemRepository, ItemCustomRepository itemCustomRepository) {
        this.itemRepository = itemRepository;
        this.itemCustomRepository = itemCustomRepository;
    }

    public Double calculaValorItemTotal(Double preco, Integer quantidade) {
        Double valorCalculado = preco * quantidade;
        return valorCalculado;
    }
    public Page<Item> listPage(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public List<Item> findByFiltros(Double valorItemTotal, Integer quantidade) {
        return itemCustomRepository.findByFiltros(valorItemTotal, quantidade);
    }
}
