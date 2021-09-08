package com.projeto.projeto.model;

import com.projeto.projeto.enums.TipoProdutoEnum;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "Produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, updatable = false)
    private Long Id;
    @Column
    @NotNull
    private String name;
    @Column
    @NotNull
    private String preco;
    @Column
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private TipoProdutoEnum tipo;
    @Column
    private String descricao;
    @Column
    @NotNull
    private int quantidade_estoque;

    public Produto(){}

}
