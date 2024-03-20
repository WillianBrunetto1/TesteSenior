package com.Senior.Senior.Controllers;

import com.Senior.Senior.Dtos.PedidoDTO;
import com.Senior.Senior.Models.Item;
import com.Senior.Senior.Models.Pedido;
import com.Senior.Senior.Models.Produto;
import com.Senior.Senior.Repositories.ItemRepository;
import com.Senior.Senior.Repositories.PedidoRepository;
import com.Senior.Senior.Service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
     PedidoService pedidoService;

    @Autowired
    ItemRepository itemRepository;

    @PostMapping("/pedido")
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid PedidoDTO pedidoDTO) {
        var pedido = new Pedido();
        Collection<Item> listaItem = new ArrayList<>();
        pedido.setDescricaoPedido(pedidoDTO.descricaoPedido());
        for(String idItem : pedidoDTO.idItem()) {
            Optional<Item> item = itemRepository.findById(UUID.fromString(idItem));
            if (item.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item "+ idItem +" não encontrado!");
            }
            listaItem.add(item.get());
        }
        pedido.setItem(listaItem);
        pedido.setStatusPedido(pedidoDTO.statusPedido());
        if(pedido.getStatusPedido().equals("Aberto")){
            pedido.setValorTotalComDesconto(pedidoService.calcularValorTotalComDesconto(pedidoDTO.porcentagemDesconto(), listaItem));
        }
        pedido.setValorTotalSemDesconto(pedidoService.calcularValorTotalSemDesconto(pedidoDTO.porcentagemDesconto(), listaItem));
        pedido.setPorcentagemDesconto(pedidoDTO.porcentagemDesconto());
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoRepository.save(pedido));
    }


    @GetMapping("/pedido/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") UUID id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if(pedido.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pedido.get());
    }

    @GetMapping(path = "/pedido/pagina")
    public ResponseEntity<Page<Pedido>> listPage(Pageable pageable) {
        return ResponseEntity.ok(pedidoService.listPage(pageable));
    }


    @DeleteMapping("/deletaPedido/{id}")
    public ResponseEntity<Object> deletaPedido(@PathVariable(value="id") UUID id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if(pedido.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        pedidoRepository.delete(pedido.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

    @GetMapping(path = "/pedidos/filtros")
    public ResponseEntity<List<Pedido>> findByParameter(
            @RequestParam(required = false) String statusPedido,
            @RequestParam(required = false) Double valorTotalComDesconto,
            @RequestParam(required = false) Double valorTotalSemDesconto,
            @RequestParam(required = false) String descricaoPedido) {

        List<Pedido> lista = pedidoService.findByFiltros(statusPedido, valorTotalComDesconto, valorTotalSemDesconto, descricaoPedido);
        return ResponseEntity.ok(lista);
    }

}
