package com.Senior.Senior.Controllers;


import com.Senior.Senior.Dtos.ProdutoDTO;
import com.Senior.Senior.Models.Produto;
import com.Senior.Senior.Repositories.ProdutoRepository;
import com.Senior.Senior.Service.ProdutoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class ProdutoController {

    @Autowired
    ProdutoRepository productRepository;


    @Autowired
    ProdutoService produtoService;


    @PostMapping("/produto")
    public ResponseEntity<Produto> saveProduct(@RequestBody @Valid ProdutoDTO produtoDTO) {
        var produto = new Produto();
        BeanUtils.copyProperties(produtoDTO, produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(produto));
    }
    @GetMapping("/produto/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") UUID id){
        Optional<Produto> productO = productRepository.findById(id);
        if(productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productO.get());
    }
    @GetMapping("/produto/nome")
    public ResponseEntity<Object> getOneProductPorNome(@RequestParam(required = false) String nome){
        Optional<Produto> productO = productRepository.findByNome(nome);
        if(productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productO.get());
    }
    @PutMapping("/atualizarProduto/{id}")
    public ResponseEntity<Object> updateProduto(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid ProdutoDTO produtoDTO) {
        Optional<Produto> productO = productRepository.findById(id);
        if(productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var productModel = productO.get();
        BeanUtils.copyProperties(produtoDTO, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }
    @GetMapping(path = "/produto/pagina")
    public ResponseEntity<Page<Produto>> listPage(Pageable pageable) {
        return ResponseEntity.ok(produtoService.listPage(pageable));
    }

    @DeleteMapping("/deletaProduto/{id}")
    public ResponseEntity<Object> deletaProduto(@PathVariable(value="id") UUID id) {
        Optional<Produto> produto = productRepository.findById(id);
        if(produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        Produto produtoResult = produto.get();
        if(produtoResult.getItem().getIdItem() !=null){
            return ResponseEntity.status(HttpStatus.OK).body("Não foi possivel excluir produto porque esta vinculado com um Item de Pedido.");
        }
        productRepository.delete(produtoResult);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

    @GetMapping(path = "/produtos/filtros")
    public ResponseEntity<List<Produto>> findByParameter(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) Double preco) {

        List<Produto> lista = produtoService.findByParameter(nome, descricao, preco);
        return ResponseEntity.ok(lista);
    }

}