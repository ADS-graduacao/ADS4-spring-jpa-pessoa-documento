package com.example.jpa.dto.mapper;

import com.example.jpa.dto.PessoaDto;
import com.example.jpa.entity.Documento;
import com.example.jpa.entity.Pessoa;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;

@Component
public class PessoaMapper {

    public PessoaDto toDto(Pessoa pessoa){

        if (pessoa == null){
            return null;
        }

        String cpf = null;
        String rg = null;

        if (pessoa.getDocumento() != null){
            cpf = pessoa.getDocumento().getCpf();
            rg = pessoa.getDocumento().getRg();
        }
        return new PessoaDto(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getEmail(),
                pessoa.getDataNascimento(),
                cpf,
                rg);
    }

    public Pessoa toEntity(PessoaDto pessoaDto){

        if (pessoaDto == null){
            return null;
        }

        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaDto.id());
        pessoa.setNome(pessoaDto.nome());
        pessoa.setEmail(pessoaDto.email());
        pessoa.setDataNascimento(pessoaDto.dataNascimento());

        Documento documento = new Documento();
        documento.setCpf(pessoaDto.cpf());
        documento.setRg(pessoaDto.rg());

        documento.setPessoa(pessoa);
        pessoa.setDocumento(documento);

        return pessoa;
    }


}
