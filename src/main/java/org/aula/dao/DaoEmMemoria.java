package org.aula.dao;

import org.aula.interfaces.Crud;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/** Armazenamento temporario: os dados duram apenas durante a execucao. */
public abstract class DaoEmMemoria<T> implements Crud<T> {
    private final Map<String, T> registros = new LinkedHashMap<>();

    protected abstract String chaveDe(T entidade);

    private String validarChave(T entidade) {
        Objects.requireNonNull(entidade, "A entidade e obrigatoria.");
        String chave = chaveDe(entidade);
        if (chave == null || chave.isBlank()) {
            throw new IllegalArgumentException("A chave do cadastro e obrigatoria.");
        }
        return chave;
    }

    @Override
    public void salvar(T entidade) {
        String chave = validarChave(entidade);
        if (registros.containsKey(chave)) {
            throw new IllegalArgumentException("Ja existe um cadastro com a chave: " + chave);
        }
        registros.put(chave, entidade);
    }

    @Override
    public List<T> listar() {
        return List.copyOf(registros.values());
    }

    @Override
    public Optional<T> buscar(String chave) {
        return Optional.ofNullable(registros.get(chave));
    }

    @Override
    public boolean excluir(String chave) {
        return registros.remove(chave) != null;
    }

    @Override
    public boolean atualizar(T entidade) {
        String chave = validarChave(entidade);
        if (!registros.containsKey(chave)) {
            return false;
        }
        registros.put(chave, entidade);
        return true;
    }
}
