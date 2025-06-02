package com.Desafio_Java.biblioteca.model;

public class Professor extends Usuario {

    public Professor(Long id, String nome) {
        super(id, nome);
    }

    @Override
    public boolean podeEmprestar() {
        return true; // Professor pode emprestar livremente
    }

    @Override
    public String toString() {
        return String.format("Professor{id=%d, nome='%s'}", id, nome);
    }
}
