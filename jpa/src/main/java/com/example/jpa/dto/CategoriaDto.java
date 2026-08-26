package com.example.jpa.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDto(
        Long id,

        @NotBlank(message = "Nome da categoria é obrigatório")
        String nome,
        String descricao
) {
}
