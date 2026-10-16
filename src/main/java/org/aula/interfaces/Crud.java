package org.aula.interfaces;

import java.util.List;
import java.util.Optional;

/** Operacoes de cadastro; a chave e definida por cada DAO. */
public interface Crud<T> {
    void salvar(T entidade);
    List<T> listar();
    Optional<T> buscar(String chave);
    boolean excluir(String chave);
    boolean atualizar(T entidade);
}
