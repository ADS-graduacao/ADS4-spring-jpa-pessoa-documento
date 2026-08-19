package com.example.jpa.dto.mapper;

import com.example.jpa.dto.DocumentoDto;
import com.example.jpa.entity.Documento;
import org.springframework.stereotype.Component;

@Component
public class DocumentoMapper {

    public DocumentoDto toDto(Documento documento) {

        if (documento == null) {
            return null;
        }

        return new DocumentoDto(
                documento.getId(),
                documento.getCpf(),
                documento.getRg()
        );
    }

    public Documento toEntity(DocumentoDto documentoDto) {

        if (documentoDto == null) {
            return null;
        }

        Documento documento = new Documento();

        documento.setId(documentoDto.id());
        documento.setCpf(documentoDto.cpf());
        documento.setRg(documentoDto.rg());

        return documento;
    }
}
