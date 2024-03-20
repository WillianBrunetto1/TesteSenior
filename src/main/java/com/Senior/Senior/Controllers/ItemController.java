package com.Senior.Senior.Controllers;

import com.Senior.Senior.Dtos.ItemDTO;
import com.Senior.Senior.Dtos.ProdutoDTO;
import com.Senior.Senior.Models.Item;
import com.Senior.Senior.Models.Produto;
import com.Senior.Senior.Repositories.ItemRepository;
import com.Senior.Senior.Repositories.ProdutoRepository;
import com.Senior.Senior.Service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ItemService itemService;


    @PostMapping("/item")
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ItemDTO itemDTO) {
        var item = new Item();
        Optional<Produto> produto = produtoRepository.findById(UUID.fromString(itemDTO.idProduto()));
        if (produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto not found.");
        }else {
            Produto produtoResult = produto.get();
            if(produtoResult.getStatusProduto().equals("desativado")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não é possivel adicionar um produto Desativado a um item para Pedido.");
            }else{
                item.setProduto(produtoResult);
                item.setQuantidade(itemDTO.quantidade());
                item.setValorItemTotal(itemService.calculaValorItemTotal(produtoResult.getPreco(), itemDTO.quantidade()));
                return ResponseEntity.status(HttpStatus.CREATED).body(itemRepository.save(item));
            }

        }
    }
    @GetMapping("/item/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") UUID id){
        Optional<Item> item = itemRepository.findById(id);
        if(item.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(item.get());
    }

    @PutMapping("/atualizarItem/{id}")
    public ResponseEntity<Object> updateItem(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid ProdutoDTO produtoDTO) {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var itemResult = item.get();
        BeanUtils.copyProperties(produtoDTO, itemResult);
        return ResponseEntity.status(HttpStatus.OK).body(itemRepository.save(itemResult));
    }

    @GetMapping(path = "/item/pagina")
    public ResponseEntity<Page<Item>> listPage(Pageable pageable) {
        return ResponseEntity.ok(itemService.listPage(pageable));
    }

    @DeleteMapping("/deletaItem/{id}")
    public ResponseEntity<Object> deletaItem(@PathVariable(value="id") UUID id) {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        Item itemResult = item.get();
        itemRepository.delete(itemResult);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

    @GetMapping(path = "/itens/filtros")
    public ResponseEntity<List<Item>> findByParameter(
            @RequestParam(required = false) Double valorItemTotal,
            @RequestParam(required = false) Integer quantidade) {

        List<Item> lista = itemService.findByFiltros(valorItemTotal, quantidade);
        return ResponseEntity.ok(lista);
    }
}
