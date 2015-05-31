package br.com.pempec.finance.models;

import java.io.Serializable;

public class BairroModel extends FinanceIdModel implements Serializable {

    private static final long serialVersionUID = 3053596074232729665L;
    private String descricao;
    private CidadeModel cidade;
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

    public CidadeModel getCidade() {
        return cidade;
    }

    public void setCidade(CidadeModel cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }
}
