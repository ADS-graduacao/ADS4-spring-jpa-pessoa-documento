package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity(name = "pessoa")
public class Pessoa {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nome;

        @Column(unique = true)
        private String email;

        private LocalDate dataNascimento;

        // Cascade: se eu apagar a pessoa, apaga o documento dela também,
        // mas como na entity do documento não tem esse,
        // se eu apagar um documento não apaga a pessoa relacionada a ele
        @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
        private Documento documento;
}
