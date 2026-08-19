package com.example.jpa.controller;

import com.example.jpa.dto.PessoaDto;
import com.example.jpa.entity.Pessoa;
import com.example.jpa.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaDto> criar(@RequestBody PessoaDto pessoa) {
        PessoaDto salva = pessoaService.salvar(pessoa);
        if (salva == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }


    @GetMapping
    public ResponseEntity<List<PessoaDto>> listarTodas() {
        List<PessoaDto> pessoas = pessoaService.listarTodas();
        if (pessoas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDto> buscarPorId(@PathVariable Long id) {
        return pessoaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pessoaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email")
    public ResponseEntity<PessoaDto> buscarPorEmail(@RequestParam String email) {
        PessoaDto pessoa = pessoaService.buscarPorEmail(email);
        if (pessoa == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<PessoaDto>> buscarPorNome(@RequestParam String nome) {
        List<PessoaDto> pessoa = pessoaService.buscarPorNome(nome);
        if (pessoa == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }

    @GetMapping("/nascimento-anterior")
    public ResponseEntity<List<PessoaDto>> buscarPorDataAnterior(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<PessoaDto> pessoa = pessoaService.buscarPorNascidosAntes(data);
        if (pessoa == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }

    @GetMapping("/cpf")
    public ResponseEntity<PessoaDto> buscarPorCpfDoDocumento(@RequestParam String cpf) {
        PessoaDto pessoaAtualizada = pessoaService.buscarPorCpfDoDocumento(cpf);
        if (pessoaAtualizada == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaAtualizada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDto> alterar(@PathVariable Long id, @RequestBody PessoaDto pessoa) {
        PessoaDto modificada = pessoaService.alterar(id, pessoa);
        if (modificada == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(modificada);
    }

}
