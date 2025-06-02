package com.Desafio_Java.biblioteca.repository;

import com.Desafio_Java.biblioteca.model.Livro;

import java.util.List;
import java.util.Optional;

// BibliotecaRepository.java
public interface BibliotecaRepository {
    List<Livro> listarTodos();
    List<Livro> listarDisponiveis();
    Optional<Livro> buscarPorId(Long id);
    void salvar(Livro livro);
}
