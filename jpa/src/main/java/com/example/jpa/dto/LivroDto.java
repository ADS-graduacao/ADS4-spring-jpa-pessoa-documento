package com.example.jpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LivroDto(
        Long id,

        @NotBlank(message = "Título é obrigatório")
        String titulo,

        @NotBlank(message = "ISBN é obrigatório")
        String isbn,

        @NotNull(message = "Quantidade de paginas é obrigatório")
        Integer paginas,

        @NotNull(message = "Preço é obrigatório")
        Double preco,

        Long idAutor,
        String nomeAutor
) {
}
