package com.Senior.Senior.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

public record ItemDTO(@NotBlank String idProduto, @NotNull Integer quantidade) {
}

