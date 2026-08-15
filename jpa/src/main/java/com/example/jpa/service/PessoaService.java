package com.example.jpa.service;

import com.example.jpa.dto.PessoaDto;
import com.example.jpa.dto.mapper.PessoaMapper;
import com.example.jpa.entity.Pessoa;
import com.example.jpa.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;

    public PessoaDto salvar(PessoaDto pessoa) {

        Pessoa entidade = pessoaMapper.toEntity(pessoa);

        if (entidade.getDocumento() != null) {
            entidade.getDocumento().setPessoa(entidade);
        }
        return pessoaMapper.toDto(pessoaRepository.save(entidade));
    }

//    public Pessoa alterar(Long id, Pessoa pessoa){
//        Optional<Pessoa> busca = buscarPorId(id);
//
//        if (busca.isEmpty()){
//            return null;
//        }
//
//        Pessoa cad = busca.get();
//        cad.setNome(pessoa.getNome());
//        cad.setEmail(pessoa.getEmail());
//        cad.setDataNascimento(pessoa.getDataNascimento());
//        cad.setDocumento(pessoa.getDocumento());
//
//        return pessoaRepository.save(cad);
//    }

    public List<Pessoa> listarTodas() {
        return pessoaRepository.findAll();
    }

    // O Optional ele pode ter algo ou pode estar vazio
    public Optional<PessoaDto> buscarPorId(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(pessoaMapper.toDto(pessoa.get()));
    }

    public void excluir(Long id) {
        pessoaRepository.deleteById(id);
    }

    public Pessoa buscarPorEmail(String email) {
        return pessoaRepository.findByEmail(email);
    }

    public List<Pessoa> buscarPorNome(String nome) {
        return pessoaRepository.findByNomeLike("%" + nome + "%");
    }

    public List<Pessoa> buscarPorNascidosAntes(LocalDate data) {
        return pessoaRepository.findPessoasNascidasAntesDe(data);
    }

    public Pessoa buscarPorCpfDoDocumento(String cpf) {
        return pessoaRepository.findByCpfDoDocumento(cpf);
    }

}