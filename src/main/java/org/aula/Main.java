package org.aula;

import java.math.BigDecimal;
import org.aula.dao.ProdutoDao;
import org.aula.dominio.Produto;

public class Main {
    public static void main(String[] args) {
        ProdutoDao produtos = new ProdutoDao();
        produtos.salvar(new Produto("P001", "Caderno", new BigDecimal("15.90")));
        produtos.listar().forEach(produto ->
                System.out.println(produto.getCodigo() + " - " + produto.getNome()
                        + " - R$ " + produto.getPreco()));
    }
}
