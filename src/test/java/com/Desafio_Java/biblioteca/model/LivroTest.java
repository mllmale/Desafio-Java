package com.Desafio_Java.biblioteca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class LivroTest {

    private Livro livro;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        livro = new Livro(1L, "Clean Code", 2);
        usuario = new Professor(1L, "Dr. Test");
    }

    @Test
    @DisplayName("Deve criar livro com dados corretos")
    void deveCriarLivroComDadosCorretos() {
        // Assert
        assertEquals(1L, livro.getId());
        assertEquals("Clean Code", livro.getTitulo());
        assertEquals(2, livro.getValorCredito());
        assertTrue(livro.isDisponivel());
        assertNull(livro.getEmprestadoPara());
    }

    @Test
    @DisplayName("Livro deve estar disponível por padrão")
    void livroDeveEstarDisponivelPorPadrao() {
        // Assert
        assertTrue(livro.isDisponivel());
    }

    @Test
    @DisplayName("Deve alterar disponibilidade corretamente")
    void deveAlterarDisponibilidadeCorretamente() {
        // Act
        livro.setDisponivel(false);

        // Assert
        assertFalse(livro.isDisponivel());

        // Act
        livro.setDisponivel(true);

        // Assert
        assertTrue(livro.isDisponivel());
    }

    @Test
    @DisplayName("Deve definir usuário emprestado corretamente")
    void deveDefinirUsuarioEmprestadoCorretamente() {
        // Act
        livro.setEmprestadoPara(usuario);

        // Assert
        assertEquals(usuario, livro.getEmprestadoPara());
    }

    @Test
    @DisplayName("Deve remover usuário emprestado corretamente")
    void deveRemoverUsuarioEmprestadoCorretamente() {
        // Arrange
        livro.setEmprestadoPara(usuario);

        // Act
        livro.setEmprestadoPara(null);

        // Assert
        assertNull(livro.getEmprestadoPara());
    }

    @Test
    @DisplayName("ToString deve conter informações corretas")
    void toStringDeveConterInformacoesCorretas() {
        // Act
        String resultado = livro.toString();

        // Assert
        assertTrue(resultado.contains("Livro"));
        assertTrue(resultado.contains("1"));
        assertTrue(resultado.contains("Clean Code"));
        assertTrue(resultado.contains("2"));
        assertTrue(resultado.contains("true"));
    }

    @Test
    @DisplayName("Deve simular estado de empréstimo completo")
    void deveSimularEstadoDeEmprestimoCompleto() {
        // Act - Simular empréstimo
        livro.setDisponivel(false);
        livro.setEmprestadoPara(usuario);

        // Assert
        assertFalse(livro.isDisponivel());
        assertEquals(usuario, livro.getEmprestadoPara());
    }

    @Test
    @DisplayName("Deve simular devolução completa")
    void deveSimularDevolucaoCompleta() {
        // Arrange - Simular livro emprestado
        livro.setDisponivel(false);
        livro.setEmprestadoPara(usuario);

        // Act - Simular devolução
        livro.setDisponivel(true);
        livro.setEmprestadoPara(null);

        // Assert
        assertTrue(livro.isDisponivel());
        assertNull(livro.getEmprestadoPara());
    }

    @Test
    @DisplayName("Deve aceitar valor de crédito zero")
    void deveAceitarValorDeCreditoZero() {
        // Arrange
        Livro livroGratuito = new Livro(2L, "Livro Gratuito", 0);

        // Assert
        assertEquals(0, livroGratuito.getValorCredito());
    }
}
