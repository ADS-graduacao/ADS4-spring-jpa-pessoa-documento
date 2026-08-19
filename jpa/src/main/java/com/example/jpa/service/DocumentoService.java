package com.example.jpa.service;

import com.example.jpa.dto.DocumentoDto;
import com.example.jpa.dto.mapper.DocumentoMapper;
import com.example.jpa.entity.Documento;
import com.example.jpa.entity.Pessoa;
import com.example.jpa.repository.DocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final DocumentoMapper documentoMapper;

    public DocumentoDto salvar(DocumentoDto documento){
        return documentoMapper.toDto((documentoRepository.save
                (documentoMapper.toEntity(documento))));
    }

    public DocumentoDto alterar(Long id, DocumentoDto documentoDto){
        Optional<Documento> documentoExistente = documentoRepository.findById(id);

        if (documentoExistente.isEmpty()){
            return null;
        }

        Documento documentoAtualizado = documentoMapper.toEntity(documentoDto);
        documentoAtualizado.setId(id);

        return documentoMapper.toDto(documentoRepository.save(documentoAtualizado));
    }

    public List<DocumentoDto> listarTodas() {
        return documentoRepository.findAll().stream()
                .map(documentoMapper::toDto)
                .toList();
    }

    // O Optional ele pode ter algo ou pode estar vazio
    public Optional<DocumentoDto> buscarPorId(Long id) {
        Optional<Documento> documento = documentoRepository.findById(id);
        if (documento.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(documentoMapper.toDto(documento.get()));
    }

    public void excluir(Long id) {
        documentoRepository.deleteById(id);
    }

    public DocumentoDto buscarPorRG(String rg) {
        return documentoMapper.toDto(documentoRepository.findByRg(rg));
    }

    public List<DocumentoDto> buscarPorNomePessoa(String nome) {
        return documentoRepository.findByNomeDaPessoa(nome).stream()
                .map(documentoMapper::toDto)
                .toList();
    }

}
