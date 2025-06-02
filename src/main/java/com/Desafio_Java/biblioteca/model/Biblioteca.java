package com.Desafio_Java.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Biblioteca {
    private String alunoId;
    private String livroId;
    private String professorId;
}
