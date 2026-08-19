package com.example.jpa.service;


import com.example.jpa.dto.LivroDto;
import com.example.jpa.dto.mapper.LivroMapper;
import com.example.jpa.entity.Autor;
import com.example.jpa.entity.Livro;
import com.example.jpa.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;

    public LivroDto salvar(LivroDto livroDto) {

        Livro livro = livroMapper.toEntity(livroDto);

        return livroMapper.toDto(livroRepository.save(livro));
    }

    public LivroDto alterar(Long id, LivroDto livroDto) {
        Optional<Livro> livroExistente = livroRepository.findById(id);

        if (livroExistente.isEmpty()) {
            return null;
        }

        Livro LivroAtualizado = livroMapper.toEntity(livroDto);
        LivroAtualizado.setId(id);

        return livroMapper.toDto(livroRepository.save(LivroAtualizado));
    }

    public List<LivroDto> listarTodos() {
        return livroRepository.findAll().stream()
                .map(livroMapper::toDto)
                .toList();
    }

    public Optional<LivroDto> buscarPorId(Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(livroMapper.toDto(livro.get()));
    }

    public void excluir(Long id) {
        livroRepository.deleteById(id);
    }

    public List<LivroDto> buscarPorAutor(Autor autor) {
        return livroRepository.findByAutor(autor)
                .stream()
                .map(livroMapper::toDto)
                .toList();
    }
}
