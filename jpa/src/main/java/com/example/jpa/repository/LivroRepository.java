package com.example.jpa.repository;

import com.example.jpa.entity.Autor;
import com.example.jpa.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByAutor(Autor autor);

}
