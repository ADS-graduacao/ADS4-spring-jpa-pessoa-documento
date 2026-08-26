package com.example.jpa.dto.mapper;

import com.example.jpa.dto.CategoriaDto;
import com.example.jpa.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaDto toDto(Categoria categoria){
        return new CategoriaDto(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao()
        );
    }

    public Categoria toEntity(CategoriaDto categoriaDto){
        Categoria categoria = new Categoria();
        categoria.setId(categoriaDto.id());
        categoria.setNome(categoriaDto.nome());
        categoria.setDescricao(categoriaDto.descricao());
        return categoria;
    }

}
