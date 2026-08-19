package com.example.jpa.dto.mapper;

import com.example.jpa.dto.AutorDto;
import com.example.jpa.entity.Autor;
import org.springframework.stereotype.Component;

@Component
public class AutorMapper {

    public AutorDto toDto(Autor autor){
        return new AutorDto(
                autor.getId(),
                autor.getNome(),
                autor.getNacionalidade(),
                autor.getDataNascimento()
        );
    }

    public Autor toEntity(AutorDto autorDto){
        Autor autor = new Autor();
        autor.setId(autorDto.id());
        autor.setNome(autorDto.nome());
        autor.setNacionalidade(autorDto.nascionalidade());
        autor.setDataNascimento(autorDto.dataNascimento());
        return autor;
    }

}
