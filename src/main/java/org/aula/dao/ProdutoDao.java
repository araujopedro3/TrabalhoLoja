package org.aula.dao;

import org.aula.dominio.Produto;

public class ProdutoDao extends DaoEmMemoria<Produto> {
    @Override
    protected String chaveDe(Produto produto) {
        return produto.getCodigo();
    }
}
