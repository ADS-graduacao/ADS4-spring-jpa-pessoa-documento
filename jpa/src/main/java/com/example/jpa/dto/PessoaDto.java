package com.example.jpa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PessoaDto(
        Long id,

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email
        String email,

        @NotNull(message = "Data de Nascimento é obrigatório")
        LocalDate dataNascimento,

        @NotBlank(message = "CPF é obrigatório")
        @CPF
        String cpf,

        @NotBlank(message = "RG é obrigatório")
        String rg
) {
}
