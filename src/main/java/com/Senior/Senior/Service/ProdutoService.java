package com.Senior.Senior.Service;

import com.Senior.Senior.Models.Produto;
import com.Senior.Senior.Repositories.ProdutoCustomRepository;
import com.Senior.Senior.Repositories.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final ProdutoCustomRepository produtoCustomRepository;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoCustomRepository produtoCustomRepository) {
        this.produtoRepository = produtoRepository;
        this.produtoCustomRepository = produtoCustomRepository;
    }


    public Page<Produto> listPage(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public List<Produto> findByParameter(String nome, String descricao, Double preco ) {
        return produtoCustomRepository.findByNomeOrDescricaoOrPreco(nome, descricao, preco);
    }
}
