package com.Senior.Senior.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public record PedidoDTO(@NotNull Collection<String> idItem, @NotBlank String descricaoPedido, Double porcentagemDesconto, @NotBlank String statusPedido) {
}
