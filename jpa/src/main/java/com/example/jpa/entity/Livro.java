package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(unique = true)
    private String isbn;

    private int paginas;

    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

}
