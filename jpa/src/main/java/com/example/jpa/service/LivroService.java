package com.example.jpa.service;


import com.example.jpa.dto.LivroDto;
import com.example.jpa.dto.mapper.LivroMapper;
import com.example.jpa.entity.Autor;
import com.example.jpa.entity.Livro;
import com.example.jpa.repository.AutorRepository;
import com.example.jpa.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;
    private final AutorRepository autorRepository;

    public LivroDto salvar(LivroDto livroDto) {

        Livro livro = livroMapper.toEntity(livroDto);
        Optional<Autor> autor = autorRepository.findById(livroDto.idAutor());
        if (autor.isEmpty()){
            return null;
        }
        livro.setAutor(autor.get());

        return livroMapper.toDto(livroRepository.save(livro));
    }

    public LivroDto alterar(Long id, LivroDto livroDto) {

        Optional<Livro> livroExistente = livroRepository.findById(id);
        Optional<Autor> autor = autorRepository.findById(livroDto.idAutor());

        if (livroExistente.isEmpty()) {
            return null;
        }

        if (autor.isEmpty()) {
            return null;
        }

        Livro livro = livroExistente.get();

        livro.setTitulo(livroDto.titulo());
        livro.setIsbn(livroDto.isbn());
        livro.setPaginas(livroDto.paginas());
        livro.setPreco(BigDecimal.valueOf(livroDto.preco()));
        livro.setAutor(autor.get());

        return livroMapper.toDto(livroRepository.save(livro));
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
