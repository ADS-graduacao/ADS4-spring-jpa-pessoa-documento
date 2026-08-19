package com.example.jpa.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record DocumentoDto(

    Long id,

    @NotBlank(message = "CPF é obrigatório")
    @CPF
    String cpf,

    @NotBlank(message = "RG é obrigatório")
    String rg
) {
}
