package br.com.pempec.finance.models;

import java.io.Serializable;

public class EstadoModel extends FinanceIdModel implements Serializable {

    private static final long serialVersionUID = -5014736832037075248L;
    private String sigla;
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }
}
