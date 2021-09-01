package com.projeto.projeto.enums;

public enum TipoProdutoEnum {

    ELETRONICO(0),
    MOVEIS(1),
    PERIFERICOS(2);

    private int valor;

    TipoProdutoEnum(int valor){
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
