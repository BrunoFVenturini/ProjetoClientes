package com.projeto.projeto.model;

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
    private Produto produto;
    @OneToOne
    @JoinColumn(name= "cliente_id")
    private Cliente cliente;
    @Column
    private String horario_venda;
    @Column
    private Long quantidade_vendido;

}
