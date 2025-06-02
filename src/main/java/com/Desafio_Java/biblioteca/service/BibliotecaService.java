package com.Desafio_Java.biblioteca.service;

import com.Desafio_Java.biblioteca.model.Livro;
import com.Desafio_Java.biblioteca.repository.BibliotecaRepository;

import java.util.*;
import java.util.stream.Collectors;

public class BibliotecaService implements BibliotecaRepository {
    private Map<Long, Livro> livros = new HashMap<>();

    @Override
    public List<Livro> listarTodos() {
        return new ArrayList<>(livros.values());
    }

    @Override
    public List<Livro> listarDisponiveis() {
        return livros.values().stream()
                .filter(Livro::isDisponivel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Livro> buscarPorId(Long id) {
        return Optional.ofNullable(livros.get(id));
    }

    @Override
    public void salvar(Livro livro) {
        livros.put(livro.getId(), livro);
    }

    public void adicionarLivro(Livro livro) {
        salvar(livro);
    }
}
