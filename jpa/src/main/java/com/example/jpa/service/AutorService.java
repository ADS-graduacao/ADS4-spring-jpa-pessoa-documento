package com.example.jpa.service;

import com.example.jpa.dto.AutorDto;
import com.example.jpa.dto.PessoaDto;
import com.example.jpa.dto.mapper.AutorMapper;
import com.example.jpa.entity.Autor;
import com.example.jpa.entity.Pessoa;
import com.example.jpa.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorMapper autorMapper;

    public AutorDto salvar(AutorDto autorDto) {
        return autorMapper.toDto(autorRepository.save(autorMapper.toEntity(autorDto)));
    }

    public AutorDto alterar(Long id, AutorDto autorDto) {
        Optional<Autor> autorExistente = autorRepository.findById(id);

        if (autorExistente.isEmpty()) {
            return null;
        }

        Autor autorAtualizada = autorMapper.toEntity(autorDto);
        autorAtualizada.setId(id);

        return autorMapper.toDto(autorRepository.save(autorAtualizada));
    }

    public List<AutorDto> listarTodos() {
        return autorRepository.findAll().stream()
                .map(autorMapper::toDto)
                .toList();
    }

    public void excluir(Long id) {
        autorRepository.deleteById(id);
    }

    public Optional<AutorDto> buscarPorId(Long id) {
        Optional<Autor> autor = autorRepository.findById(id);
        if (autor.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(autorMapper.toDto(autor.get()));
    }

    public List<AutorDto> buscarPorNome(String nome) {
        return autorRepository.findAll().stream()
                .map(autorMapper::toDto)
                .toList();
    }

}
