package com.valdoneves.contabilidade.dao;

import com.valdoneves.contabilidade.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    //cria uma lista de clientes, privada e estatica ou seja unica, para não ser modificada diretamente
    private final static List<Cliente> clientes = new ArrayList<>();

    //pega a lista e adiciona novos clientes
    public void salvaCliente(Cliente cliente) {clientes.add(cliente); }

    //faz uma copia de todos os clientes que existem na lista statica assim não corremos o risco
    //de perder dados
    public List<Cliente> todos() { return new ArrayList<>(clientes); }
}
