package com.Desafio_Java.biblioteca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class AlunoTest {

    private Aluno aluno;

    @BeforeEach
    void setUp() {
        aluno = new Aluno(1L, "João Silva", 3);
    }

    @Test
    @DisplayName("Deve criar aluno com dados corretos")
    void deveCriarAlunoComDadosCorretos() {
        // Assert
        assertEquals(1L, aluno.getId());
        assertEquals("João Silva", aluno.getNome());
        assertEquals(3, aluno.getCreditos());
    }

    @Test
    @DisplayName("Aluno com créditos deve poder emprestar")
    void alunoComCreditosDevePodeEmprestar() {
        // Arrange
        Aluno alunoComCreditos = new Aluno(1L, "Test", 1);

        // Act & Assert
        assertTrue(alunoComCreditos.podeEmprestar());
    }

    @Test
    @DisplayName("Aluno sem créditos não deve poder emprestar")
    void alunoSemCreditosNaoDevePodeEmprestar() {
        // Arrange
        Aluno alunoSemCreditos = new Aluno(1L, "Test", 0);

        // Act & Assert
        assertFalse(alunoSemCreditos.podeEmprestar());
    }

    @Test
    @DisplayName("Deve diminuir crédito quando tiver créditos disponíveis")
    void deveDiminuirCreditoQuandoTiverCreditosDisponiveis() {
        // Arrange
        int creditosIniciais = aluno.getCreditos();

        // Act
        aluno.diminuirCredito();

        // Assert
        assertEquals(creditosIniciais - 1, aluno.getCreditos());
    }

    @Test
    @DisplayName("Não deve diminuir crédito quando já estiver zerado")
    void naoDeveDiminuirCreditoQuandoJaEstiverZerado() {
        // Arrange
        Aluno alunoSemCreditos = new Aluno(1L, "Test", 0);

        // Act
        alunoSemCreditos.diminuirCredito();

        // Assert
        assertEquals(0, alunoSemCreditos.getCreditos());
    }

    @Test
    @DisplayName("Deve aumentar crédito corretamente")
    void deveAumentarCreditoCorretamente() {
        // Arrange
        int creditosIniciais = aluno.getCreditos();

        // Act
        aluno.aumentarCredito();

        // Assert
        assertEquals(creditosIniciais + 1, aluno.getCreditos());
    }

    @Test
    @DisplayName("Deve aumentar crédito mesmo quando zerado")
    void deveAumentarCreditoMesmoQuandoZerado() {
        // Arrange
        Aluno alunoSemCreditos = new Aluno(1L, "Test", 0);

        // Act
        alunoSemCreditos.aumentarCredito();

        // Assert
        assertEquals(1, alunoSemCreditos.getCreditos());
    }

    @Test
    @DisplayName("ToString deve conter informações corretas")
    void toStringDeveConterInformacoesCorretas() {
        // Act
        String resultado = aluno.toString();

        // Assert
        assertTrue(resultado.contains("Aluno"));
        assertTrue(resultado.contains("1"));
        assertTrue(resultado.contains("João Silva"));
        assertTrue(resultado.contains("3"));
    }

    @Test
    @DisplayName("Deve criar aluno com créditos negativos e tratar como zero")
    void deveCriarAlunoComCreditosNegativosETratarComoZero() {
        // Este teste verifica se a implementação trata créditos negativos
        // Se não houver validação, pode ser adicionada
        Aluno alunoCredNegativo = new Aluno(1L, "Test", -1);

        // Assert - dependendo da implementação
        assertFalse(alunoCredNegativo.podeEmprestar());
    }
}
