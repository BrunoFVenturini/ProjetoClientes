package com.projeto.projeto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column
    private String name;
    @Column
    private String cpf;
    @Column
    private String endereco;
    @Column
    private String telefone;
    @Column
    private String horario_cadastro;

    public Cliente(){}

}
