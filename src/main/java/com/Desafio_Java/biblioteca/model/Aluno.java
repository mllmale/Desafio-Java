package com.Desafio_Java.biblioteca.model;

public class Aluno extends Usuario {
    private int creditos;

    public Aluno(Long id, String nome, int creditos) {
        super(id, nome);
        this.creditos = creditos;
    }

    public int getCreditos() { return creditos; }

    @Override
    public boolean podeEmprestar() {
        return creditos > 0;
    }

    public void diminuirCredito() {
        if (creditos > 0) {
            creditos--;
        }
    }

    public void aumentarCredito() {
        creditos++;
    }

    @Override
    public String toString() {
        return String.format("Aluno{id=%d, nome='%s', creditos=%d}", id, nome, creditos);
    }
}
