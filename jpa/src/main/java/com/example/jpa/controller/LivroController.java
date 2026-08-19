package com.example.jpa.controller;

import com.example.jpa.dto.LivroDto;
import com.example.jpa.entity.Autor;
import com.example.jpa.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    public ResponseEntity<LivroDto> criar(@RequestBody LivroDto livro) {
        LivroDto salvo = livroService.salvar(livro);

        if (salvo == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<LivroDto>> listarTodos() {
        List<LivroDto> livros = livroService.listarTodos();

        if (livros.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> buscarPorId(@PathVariable Long id) {
        return livroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        livroService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDto> alterar(
            @PathVariable Long id,
            @RequestBody LivroDto livro) {

        LivroDto modificado = livroService.alterar(id, livro);

        if (modificado == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(modificado);
    }

    @GetMapping("/autor")
    public ResponseEntity<List<LivroDto>> buscarPorAutor(@RequestBody Autor autor) {

        List<LivroDto> livros = livroService.buscarPorAutor(autor);

        if (livros.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(livros);
    }

}
