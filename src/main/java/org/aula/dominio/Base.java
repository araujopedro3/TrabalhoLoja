package org.aula.dominio;

import java.time.LocalDate;

public abstract class Base {
    private LocalDate dataCriacao;
    private String criadoPor;

    public Base() {
        this.dataCriacao = LocalDate.now();
    }

    public Base(LocalDate dataCriacao, String criadoPor) {
        this.dataCriacao = dataCriacao;
        this.criadoPor = criadoPor;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(String criadoPor) {
        this.criadoPor = criadoPor;
    }
}
