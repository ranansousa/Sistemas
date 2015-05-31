package br.com.pempec.finance.models;

import java.io.Serializable;

public class HistoricoModel extends FinancePkModel implements Serializable {

    private Integer codigo;
    private String descricao;
    private String tipo;
    private MovimentoDiarioModel movimentoDiarioModel;
    private ContaContabilModel contaContabil;

    public ContaContabilModel getContaContabil() {
        return contaContabil;
    }

    public void setContaContabil(ContaContabilModel contaContabil) {
        this.contaContabil = contaContabil;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

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

    @Override
    public String toString() {
        return this.getDescricao();
    }
}// fim da class

