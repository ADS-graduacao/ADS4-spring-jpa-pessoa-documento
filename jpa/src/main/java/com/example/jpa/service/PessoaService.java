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

    public PessoaDto alterar(Long id, PessoaDto pessoaDto){
        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(id);

        if (pessoaExistente.isEmpty()){
            return null;
        }

        Pessoa pessoaAtualizada = pessoaMapper.toEntity(pessoaDto);
        pessoaAtualizada.setId(id);

        if (pessoaAtualizada.getDocumento() != null){
            pessoaAtualizada.getDocumento().setPessoa(pessoaAtualizada);
        }

        return pessoaMapper.toDto(pessoaRepository.save(pessoaAtualizada));
    }

    public List<PessoaDto> listarTodas() {
        return pessoaRepository.findAll().stream()
                .map(pessoaMapper::toDto)
                .toList();
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

    public PessoaDto buscarPorEmail(String email) {
        return pessoaMapper.toDto(pessoaRepository.findByEmail(email));
    }

    public List<PessoaDto> buscarPorNome(String nome) {
        return pessoaRepository.findAll().stream()
                .map(pessoaMapper::toDto)
                .toList();
    }

    public List<PessoaDto> buscarPorNascidosAntes(LocalDate data) {
        return pessoaRepository.findAll().stream()
                .map(pessoaMapper::toDto)
                .toList();
    }

    public PessoaDto buscarPorCpfDoDocumento(String cpf) {
        return pessoaMapper.toDto(pessoaRepository.findByCpfDoDocumento(cpf));
    }

}