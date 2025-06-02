package com.Desafio_Java.biblioteca.userCase;

import com.Desafio_Java.biblioteca.model.Aluno;
import com.Desafio_Java.biblioteca.model.Livro;
import com.Desafio_Java.biblioteca.model.Usuario;
import com.Desafio_Java.biblioteca.repository.BibliotecaRepository;

import java.util.Optional;

public class DevolverLivroUseCase {
    private BibliotecaRepository bibliotecaRepository;

    public DevolverLivroUseCase(BibliotecaRepository bibliotecaRepository) {
        this.bibliotecaRepository = bibliotecaRepository;
    }

    public void executar(Long livroId, Usuario usuario) {
        Optional<Livro> livroOpt = bibliotecaRepository.buscarPorId(livroId);

        if (livroOpt.isEmpty()) {
            throw new IllegalArgumentException("Livro não encontrado");
        }

        Livro livro = livroOpt.get();

        if (livro.isDisponivel()) {
            throw new IllegalStateException("Livro já está disponível");
        }

        if (!usuario.equals(livro.getEmprestadoPara()) ||
                !usuario.getId().equals(livro.getEmprestadoPara().getId())) {
            throw new IllegalStateException("Usuário não pode devolver este livro");
        }

        // Realizar devolução
        livro.setDisponivel(true);
        livro.setEmprestadoPara(null);

        // Se for aluno, aumentar crédito
        if (usuario instanceof Aluno) {
            ((Aluno) usuario).aumentarCredito();
        }

        bibliotecaRepository.salvar(livro);
    }
}