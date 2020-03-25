package com.valdoneves.contabilidade.modelo;

import androidx.annotation.NonNull;

public class Cliente {

    private String nome;
    private String valor;
    private String observacoes;
    private Integer id;

    public Cliente(Integer id, String nome, String valor,String observacoes) {
        this.nome = nome;
        this.valor = valor;
        this.id = id;
        this.observacoes = observacoes;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public Cliente() {
    }

    public String getNome() {
        return nome;
    }

    public String getValor() {
        return valor;
    }

    public Integer getId() {
        return id;
    }

    public String getObservacoes() {
        return observacoes;
    }
}
