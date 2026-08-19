package com.example.jpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AutorDto(
        Long id,

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Nascionalidade é obrigatório")
        String nascionalidade,

        @NotNull(message = "Data de Nascimento é obrigatório")
        LocalDate dataNascimento
) {
}
