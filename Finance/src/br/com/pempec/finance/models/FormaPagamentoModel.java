package br.com.pempec.finance.models;

import java.io.Serializable;

public class FormaPagamentoModel extends FinancePkModel implements Serializable {

    private String descricao;
    private ContaContabilModel contaContabil;
    private MovimentoDiarioModel movimentoDiarioModel;

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public ContaContabilModel getContaContabil() {
        return contaContabil;
    }

    public void setContaContabil(ContaContabilModel contaContabil) {
        this.contaContabil = contaContabil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }
}// fim da class

