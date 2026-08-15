package com.example.jpa.repository;

import com.example.jpa.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentoRepository extends JpaRepository <Documento, Long> {

    Documento findByCpf(String cpf);

    // Consulta com @Query
    @Query("SELECT d FROM documento d WHERE d.rg = :rg")
    Documento findByRg(@Param("rg") String rg);

    @Query("SELECT d FROM documento d WHERE d.pessoa.nome LIKE %:nome%")
    List<Documento> findByNomeDaPessoa(@Param("nome") String nome);

}
