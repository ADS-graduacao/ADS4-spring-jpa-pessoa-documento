package com.example.jpa.service;

import com.example.jpa.entity.Documento;
import com.example.jpa.repository.DocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final DocumentoRepository documentoRepository;

    public Documento salvar(Documento documento){
        return documentoRepository.save(documento);
    }

    public Documento alterar(Long id, Documento documento){
        Optional<Documento> busca = buscarPorId(id);

        if (busca.isEmpty()){
            return null;
        }

        Documento cad = busca.get();
        cad.setCpf(documento.getCpf());
        cad.setRg(documento.getRg());

        return documentoRepository.save(cad);
    }

    public List<Documento> listarTodas() {
        return documentoRepository.findAll();
    }

    // O Optional ele pode ter algo ou pode estar vazio
    public Optional<Documento> buscarPorId(Long id) {
        return documentoRepository.findById(id);
    }

    public void excluir(Long id) {
        documentoRepository.deleteById(id);
    }

    public Documento buscarPorRG(String rg) {
        return documentoRepository.findByRg(rg);
    }

    public List<Documento> buscarPorNomePessoa(String nome) {
        return documentoRepository.findByNomeDaPessoa(nome);
    }

}
