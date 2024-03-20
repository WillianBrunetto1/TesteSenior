package com.Senior.Senior.Repositories;

import com.Senior.Senior.Models.Produto;
import jakarta.persistence.Transient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID>{

    Optional<Produto> findByNome(String nome);

}