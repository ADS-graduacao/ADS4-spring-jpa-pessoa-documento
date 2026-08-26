package com.example.jpa.controller;

import com.example.jpa.dto.CategoriaDto;
import com.example.jpa.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaDto> criar(@RequestBody CategoriaDto categoria) {
        CategoriaDto salva = categoriaService.salvar(categoria);
        if (salva == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }


    @GetMapping
    public ResponseEntity<List<CategoriaDto>> listarTodas() {
        List<CategoriaDto> categorias = categoriaService.listarTodas();
        if (categorias.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        categoriaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> alterar(@PathVariable Long id, @RequestBody CategoriaDto categoria) {
        CategoriaDto modificada = categoriaService.alterar(id, categoria);
        if (modificada == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(modificada);
    }
    
}
