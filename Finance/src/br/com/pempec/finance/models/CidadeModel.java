package br.com.pempec.finance.models;

import java.io.Serializable;

public class CidadeModel extends FinanceIdModel implements Serializable {

    private static final long serialVersionUID = 706249102343449232L;
    private String descricao;
    private EstadoModel estado;
    private transient MovimentoDiarioModel movimentoDiarioModel;

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public EstadoModel getEstado() {
        return estado;
    }

    public void setEstado(EstadoModel estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }
}
