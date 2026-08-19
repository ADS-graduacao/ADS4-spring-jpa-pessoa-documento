package com.example.jpa.dto.mapper;

import com.example.jpa.dto.LivroDto;
import com.example.jpa.entity.Autor;
import com.example.jpa.entity.Livro;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LivroMapper {

    public LivroDto toDto(Livro livro){

        Long idAutor = null;
        String nomeAutor = null;

        if (livro.getAutor() != null){
            idAutor = livro.getAutor().getId();
            nomeAutor = livro.getAutor().getNome();
        }

        return new LivroDto(
                livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getPaginas(),
                livro.getPreco().doubleValue(), // no DTO é DOUBLE, no ENTITY é BIGDECIMAL
                idAutor,
                nomeAutor
        );
    }

    public Livro toEntity(LivroDto livroDto){

        Autor autor = new Autor();
        autor.setId(livroDto.idAutor());

        Livro livro = new Livro();
        livro.setId(livroDto.id());
        livro.setTitulo(livro.getTitulo());
        livro.setIsbn(livro.getIsbn());
        livro.setPaginas(livro.getPaginas());
        livro.setPreco(BigDecimal.valueOf(livroDto.preco()));
        livro.setAutor(autor);
        return livro;
    }

}
