package com.Senior.Senior.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(@NotBlank String nome,@NotBlank String descricao,@NotNull Double preco,@NotBlank String StatusProduto,@NotBlank String isProduto) {
}