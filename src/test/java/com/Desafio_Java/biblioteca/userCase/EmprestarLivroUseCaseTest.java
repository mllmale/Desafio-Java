package com.Desafio_Java.biblioteca.userCase;

import com.Desafio_Java.biblioteca.model.Aluno;
import com.Desafio_Java.biblioteca.model.Livro;
import com.Desafio_Java.biblioteca.model.Professor;
import com.Desafio_Java.biblioteca.repository.BibliotecaRepository;
import com.Desafio_Java.biblioteca.service.BibliotecaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmprestarLivroUseCaseTest {
    private BibliotecaRepository bibliotecaRepository;
    private EmprestarLivroUseCase emprestarLivroUseCase;
    private Livro livro;
    private Aluno aluno;
    private Professor professor;

    @BeforeEach
    void setUp() {
        bibliotecaRepository = new BibliotecaService();
        emprestarLivroUseCase = new EmprestarLivroUseCase(bibliotecaRepository);

        livro = new Livro(1L, "Test Book", 1);
        bibliotecaRepository.salvar(livro);

        aluno = new Aluno(1L, "Test Student", 1);
        professor = new Professor(2L, "Test Professor");
    }

    @Test
    void deveEmprestarLivroParaAlunoComCredito() {
        // Act
        emprestarLivroUseCase.executar(1L, aluno);

        // Assert
        assertFalse(livro.isDisponivel());
        assertEquals(aluno, livro.getEmprestadoPara());
        assertEquals(0, aluno.getCreditos());
    }

    @Test
    void deveEmprestarLivroParaProfessor() {
        // Act
        emprestarLivroUseCase.executar(1L, professor);

        // Assert
        assertFalse(livro.isDisponivel());
        assertEquals(professor, livro.getEmprestadoPara());
    }

    @Test
    void naoDeveEmprestarLivroParaAlunoSemCredito() {
        // Arrange
        Aluno alunoSemCredito = new Aluno(3L, "No Credit Student", 0);

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            emprestarLivroUseCase.executar(1L, alunoSemCredito);
        });
    }

    @Test
    void naoDeveEmprestarLivroJaEmprestado() {
        // Arrange
        livro.setDisponivel(false);
        livro.setEmprestadoPara(professor);

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            emprestarLivroUseCase.executar(1L, aluno);
        });
    }

    @Test
    void naoDeveEmprestarLivroInexistente() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            emprestarLivroUseCase.executar(999L, aluno);
        });
    }
}

