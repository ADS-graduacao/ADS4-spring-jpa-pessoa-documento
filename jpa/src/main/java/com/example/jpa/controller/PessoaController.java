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
    public ResponseEntity<List<Pessoa>> listarTodas() {
        List<Pessoa> pessoas = pessoaService.listarTodas();
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
    public ResponseEntity<Pessoa> buscarPorEmail(@RequestParam String email) {
        Pessoa pessoa = pessoaService.buscarPorEmail(email);
        if (pessoa == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Pessoa>> buscarPorNome(@RequestParam String nome) {
        List<Pessoa> pessoa = pessoaService.buscarPorNome(nome);
        if (pessoa == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }

    @GetMapping("/nascimento-anterior")
    public ResponseEntity<List<Pessoa>> buscarPorDataAnterior(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<Pessoa> pessoa = pessoaService.buscarPorNascidosAntes(data);
        if (pessoa == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }

    @GetMapping("/cpf")
    public ResponseEntity<Pessoa> buscarPorCpfDoDocumento(@RequestParam String cpf) {
        Pessoa pessoa = pessoaService.buscarPorCpfDoDocumento(cpf);
        if (pessoa == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Pessoa> alterar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
//        Pessoa modificada = pessoaService.alterar(id, pessoa);
//        if (modificada == null) {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(modificada);
//    }

}
