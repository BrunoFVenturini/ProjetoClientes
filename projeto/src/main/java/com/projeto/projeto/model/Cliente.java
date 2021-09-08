package com.projeto.projeto.model;

import com.sun.istack.NotNull;
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
    @NotNull
    private String name;
    @Column
    @NotNull
    private String cpf;
    @Column
    @NotNull
    private String endereco;
    @Column
    @NotNull
    private String telefone;
    @Column
    @NotNull
    private String horario_cadastro;

    public Cliente(){}

}
