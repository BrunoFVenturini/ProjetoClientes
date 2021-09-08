package com.projeto.projeto.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false,updatable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name= "produto_id")
    @NotNull
    private Produto produto;
    @OneToOne
    @JoinColumn(name= "cliente_id")
    @NotNull
    private Cliente cliente;
    @Column
    @NotNull
    private String horario_venda;
    @Column
    @NotNull
    private Long quantidade_vendido;

}
