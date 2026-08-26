package com.example.jpa.dto.mapper;

import com.example.jpa.dto.CategoriaDto;
import com.example.jpa.dto.LivroDto;
import com.example.jpa.entity.Autor;
import com.example.jpa.entity.Categoria;
import com.example.jpa.entity.Livro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LivroMapper {

    private final CategoriaMapper categoriaMapper;

    public LivroDto toDto(Livro livro){

        Long idAutor = null;
        String nomeAutor = null;

        if (livro.getAutor() != null){
            idAutor = livro.getAutor().getId();
            nomeAutor = livro.getAutor().getNome();
        }

        List<CategoriaDto> categoriaDtos = livro.getCategorias()
                .stream()
                .map(categoriaMapper::toDto)
                .toList();

        return new LivroDto(
                livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getPaginas(),
                livro.getPreco().doubleValue(), // no DTO é DOUBLE, no ENTITY é BIGDECIMAL
                idAutor,
                nomeAutor,
                categoriaDtos
        );
    }

    public Livro toEntity(LivroDto livroDto){

        List<Categoria> categorias = livroDto.categorias()
                .stream()
                .map(categoriaMapper::toEntity)
                .toList();

        Livro livro = new Livro();

        livro.setId(livroDto.id());
        livro.setTitulo(livroDto.titulo());
        livro.setIsbn(livroDto.isbn());
        livro.setPaginas(livroDto.paginas());
        livro.setPreco(BigDecimal.valueOf(livroDto.preco()));
        livro.setCategorias(categorias);

        return livro;
    }

}
