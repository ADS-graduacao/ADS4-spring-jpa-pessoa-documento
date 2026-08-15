package com.example.jpa.controller;

import com.example.jpa.entity.Documento;
import com.example.jpa.entity.Pessoa;
import com.example.jpa.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.dom.DOMCryptoContext;

@RestController
@RequestMapping("/documentos")
@RequiredArgsConstructor
public class DocumentoController {

    private final DocumentoService documentoService;

    @PostMapping
    public ResponseEntity<Documento> criar(@RequestBody Documento documento) {
        Documento salvo = documentoService.salvar(documento);
        if (salvo == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documento> buscarPorId(@PathVariable Long id) {
        return documentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Documento> excluir(@PathVariable Long id) {
        documentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Documento> alterar(@PathVariable Long id, @RequestBody Documento documento) {
        Documento modificado = documentoService.alterar(id, documento);
        if (modificado == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(modificado);
    }

    @GetMapping("/RG")
    public ResponseEntity<Documento> buscarPorRG(@RequestParam String rg){
        Documento documento = documentoService.buscarPorRG(rg);
        if (documento == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(documento);
    }

}
