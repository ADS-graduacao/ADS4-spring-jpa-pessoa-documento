package com.example.jpa.repository;

import com.example.jpa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    // Consulta usando @NamedQuery
    Pessoa findByEmail(String email);

    List<Pessoa> findByNomeLike(String nome);

    // Consulta usando @Query
    @Query("SELECT p FROM pessoa p WHERE p.dataNascimento < :data")
    List<Pessoa> findPessoasNascidasAntesDe(@Param("data") LocalDate data);

    @Query("SELECT p FROM pessoa p WHERE p.documento.cpf = :cpf")
    Pessoa findByCpfDoDocumento(@Param("cpf") String cpf);

}
