package org.aula.dao;

import org.aula.dominio.Cliente;

/** O CPF identifica o cliente e nao deve mudar depois do cadastro. */
public class ClienteDao extends DaoEmMemoria<Cliente> {
    @Override
    protected String chaveDe(Cliente cliente) {
        return cliente.getCpf();
    }
}
