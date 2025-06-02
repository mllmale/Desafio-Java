package com.Desafio_Java.biblioteca.userCase;

import com.Desafio_Java.biblioteca.model.Aluno;
import com.Desafio_Java.biblioteca.model.Livro;
import com.Desafio_Java.biblioteca.model.Professor;
import com.Desafio_Java.biblioteca.repository.BibliotecaRepository;
import com.Desafio_Java.biblioteca.service.BibliotecaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DevolverLivroUseCaseTest {
    private BibliotecaRepository bibliotecaRepository;
    private DevolverLivroUseCase devolverLivroUseCase;
    private Livro livro;
    private Aluno aluno;
    private Professor professor;

    @BeforeEach
    void setUp() {
        bibliotecaRepository = new BibliotecaService();
        devolverLivroUseCase = new DevolverLivroUseCase(bibliotecaRepository);

        livro = new Livro(1L, "Test Book", 1);
        aluno = new Aluno(1L, "Test Student", 0);
        professor = new Professor(2L, "Test Professor");

        // Simular livro emprestado
        livro.setDisponivel(false);
        livro.setEmprestadoPara(aluno);
        bibliotecaRepository.salvar(livro);
    }

    @Test
    void deveDevolverLivroEAumentarCreditoDoAluno() {
        // Act
        devolverLivroUseCase.executar(1L, aluno);

        // Assert
        assertTrue(livro.isDisponivel());
        assertNull(livro.getEmprestadoPara());
        assertEquals(1, aluno.getCreditos());
    }

    @Test
    void deveDevolverLivroDosProfessor() {
        // Arrange
        livro.setEmprestadoPara(professor);

        // Act
        devolverLivroUseCase.executar(1L, professor);

        // Assert
        assertTrue(livro.isDisponivel());
        assertNull(livro.getEmprestadoPara());
    }

    @Test
    void naoDeveDevolverLivroQueNaoEstaEmprestado() {
        // Arrange
        livro.setDisponivel(true);
        livro.setEmprestadoPara(null);

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            devolverLivroUseCase.executar(1L, aluno);
        });
    }

    @Test
    void naoDeveDevolverLivroEmprestadoParaOutroUsuario() {
        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            devolverLivroUseCase.executar(1L, professor);
        });
    }

    @Test
    void naoDeveDevolverLivroInexistente() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            devolverLivroUseCase.executar(999L, aluno);
        });
    }
}