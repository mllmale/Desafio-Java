package com.Desafio_Java.biblioteca.userCase;


import com.Desafio_Java.biblioteca.model.Aluno;
import com.Desafio_Java.biblioteca.model.Livro;
import com.Desafio_Java.biblioteca.model.Usuario;
import com.Desafio_Java.biblioteca.repository.BibliotecaRepository;

import java.util.Optional;

public class EmprestarLivroUseCase {  // ← NOME CORRIGIDO
    private BibliotecaRepository bibliotecaRepository;

    public EmprestarLivroUseCase(BibliotecaRepository bibliotecaRepository) {
        this.bibliotecaRepository = bibliotecaRepository;
    }

    public void executar(Long livroId, Usuario usuario) {
        Optional<Livro> livroOpt = bibliotecaRepository.buscarPorId(livroId);

        if (livroOpt.isEmpty()) {
            throw new IllegalArgumentException("Livro não encontrado");
        }

        Livro livro = livroOpt.get();

        if (!livro.isDisponivel()) {
            throw new IllegalStateException("Livro não está disponível");
        }

        if (!usuario.podeEmprestar()) {
            throw new IllegalStateException("Usuário não pode emprestar livros");
        }

        // Realizar empréstimo
        livro.setDisponivel(false);
        livro.setEmprestadoPara(usuario);

        // Se for aluno, diminuir crédito
        if (usuario instanceof Aluno) {
            ((Aluno) usuario).diminuirCredito();
        }

        bibliotecaRepository.salvar(livro);
    }
}