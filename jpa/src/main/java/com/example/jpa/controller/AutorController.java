package com.example.jpa.controller;

import com.example.jpa.dto.AutorDto;
import com.example.jpa.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @PostMapping
    public ResponseEntity<AutorDto> criar(@RequestBody AutorDto autor) {
        AutorDto salva = autorService.salvar(autor);
        if (salva == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }


    @GetMapping
    public ResponseEntity<List<AutorDto>> listarTodos() {
        List<AutorDto> autor = autorService.listarTodos();
        if (autor.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(autor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        autorService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDto> buscarPorId(@PathVariable Long id) {
        return autorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome")
    public ResponseEntity<List<AutorDto>> buscarPorNome(@RequestParam String nome) {
        List<AutorDto> autor = autorService.buscarPorNome(nome);
        if (autor == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(autor);
    }

}
