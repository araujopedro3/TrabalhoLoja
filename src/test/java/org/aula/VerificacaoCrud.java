package org.aula;

import java.math.BigDecimal;
import org.aula.dao.ClienteDao;
import org.aula.dao.ProdutoDao;
import org.aula.dominio.Cliente;
import org.aula.dominio.Produto;

/** Verificacao executavel sem dependencias externas. */
public class VerificacaoCrud {
    private static void verificar(boolean condicao, String mensagem) {
        if (!condicao) throw new AssertionError(mensagem);
    }

    private static void deveRejeitar(Runnable acao) {
        try {
            acao.run();
        } catch (IllegalArgumentException | NullPointerException esperado) {
            return;
        }
        throw new AssertionError("A entrada invalida deveria ter sido rejeitada.");
    }

    public static void main(String[] args) {
        ProdutoDao dao = new ProdutoDao();
        Produto original = new Produto("1", "Caderno", new BigDecimal("10"));
        dao.salvar(original);
        verificar(dao.buscar("1").orElseThrow() == original, "Busca do produto");
        deveRejeitar(() -> dao.salvar(original));
        verificar(dao.listar().size() == 1, "Duplicata alterou os registros");
        var fotografia = dao.listar();
        Produto atualizado = new Produto("1", "Caderno novo", new BigDecimal("12"));
        verificar(dao.atualizar(atualizado), "Atualizacao existente");
        verificar(dao.buscar("1").orElseThrow() == atualizado, "Atualizacao nao substituiu");
        verificar(!dao.atualizar(new Produto("2", "Outro", BigDecimal.ZERO)),
                "Atualizar nao deve inserir");
        try {
            fotografia.clear();
            throw new AssertionError("Lista externa permitiu alteracao estrutural.");
        } catch (UnsupportedOperationException esperado) {
            // A colecao interna permanece protegida.
        }
        verificar(dao.excluir("1"), "Exclusao existente");
        verificar(!dao.excluir("1"), "Exclusao inexistente");
        verificar(dao.buscar("1").isEmpty(), "Registro excluido ainda existe");
        verificar(fotografia.size() == 1, "Lista deve representar uma fotografia");
        deveRejeitar(() -> dao.salvar(null));
        deveRejeitar(() -> new Produto(" ", "Nome", BigDecimal.ZERO));
        deveRejeitar(() -> new Produto("3", "Nome", new BigDecimal("-1")));

        ClienteDao clientes = new ClienteDao();
        Cliente cliente = new Cliente();
        deveRejeitar(() -> clientes.salvar(cliente));
        cliente.setCpf("cliente-teste");
        cliente.setNome("Cliente de teste");
        clientes.salvar(cliente);
        verificar(clientes.buscar("cliente-teste").orElseThrow() == cliente, "Busca de cliente");
        Cliente substituto = new Cliente();
        substituto.setCpf("cliente-teste");
        substituto.setNome("Nome atualizado");
        verificar(clientes.atualizar(substituto), "Atualizacao de cliente");
        verificar(clientes.buscar("cliente-teste").orElseThrow() == substituto, "Cliente atualizado");
        deveRejeitar(() -> clientes.salvar(substituto));
        verificar(clientes.excluir("cliente-teste"), "Exclusao de cliente");
        System.out.println("OK: verificacoes de CRUD de produtos e clientes.");
    }
}
