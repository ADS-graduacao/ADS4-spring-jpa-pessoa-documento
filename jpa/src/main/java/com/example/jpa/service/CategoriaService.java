package com.example.jpa.service;

import com.example.jpa.dto.CategoriaDto;
import com.example.jpa.dto.mapper.CategoriaMapper;
import com.example.jpa.entity.Categoria;
import com.example.jpa.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;
    
    public CategoriaDto salvar(CategoriaDto categoriaDto){
        Categoria entidade = categoriaMapper.toEntity(categoriaDto);
        return categoriaMapper.toDto(categoriaRepository.save(entidade));
    }

    public CategoriaDto alterar(Long id, CategoriaDto categoriaDto){
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);

        if (categoriaExistente.isEmpty()){
            return null;
        }

        Categoria categoriaAtualizada = categoriaMapper.toEntity(categoriaDto);
        categoriaAtualizada.setId(id);

        return categoriaMapper.toDto(categoriaRepository.save(categoriaAtualizada));
    }

    public List<CategoriaDto> listarTodas() {
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::toDto)
                .toList();
    }


    // O Optional ele pode ter algo ou pode estar vazio
    public Optional<CategoriaDto> buscarPorId(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(categoriaMapper.toDto(categoria.get()));
    }

    public void excluir(Long id) {
        categoriaRepository.deleteById(id);
    }


}
