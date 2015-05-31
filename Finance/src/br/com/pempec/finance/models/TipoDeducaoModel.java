package br.com.pempec.finance.models;

import java.io.Serializable;

public class TipoDeducaoModel extends FinancePkModel implements Serializable {

    private String descricao;
    private HistoricoModel historico;
    private MovimentoDiarioModel movimentoDiarioModel;

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

    public HistoricoModel getHistorico() {
        return historico;
    }

    public void setHistorico(HistoricoModel historico) {
        this.historico = historico;
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }
}// fim da class

