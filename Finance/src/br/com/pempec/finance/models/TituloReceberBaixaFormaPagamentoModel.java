package br.com.pempec.finance.models;

import java.io.Serializable;

public class TituloReceberBaixaFormaPagamentoModel extends FinancePkModel implements Serializable {

    //Variavel para controle do checkBox
    private Boolean check = false;
    private FormaPagamentoModel forma;
    private TituloReceberBaixaModel tituloReceberBaixaModel;
    private Double valor;
    private String conta;
    private String cheque;

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }
    

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public FormaPagamentoModel getForma() {
        return forma;
    }

    public void setForma(FormaPagamentoModel forma) {
        this.forma = forma;
    }

    public TituloReceberBaixaModel getTituloReceberBaixaModel() {
        return tituloReceberBaixaModel;
    }

    public void setTituloReceberBaixaModel(TituloReceberBaixaModel tituloReceberBaixaModel) {
        this.tituloReceberBaixaModel = tituloReceberBaixaModel;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TituloReceberBaixaFormaPagamentoModel other = (TituloReceberBaixaFormaPagamentoModel) obj;
        if (this.check != other.check && (this.check == null || !this.check.equals(other.check))) {
            return false;
        }
        if (this.forma != other.forma && (this.forma == null || !this.forma.equals(other.forma))) {
            return false;
        }
        if (this.tituloReceberBaixaModel != other.tituloReceberBaixaModel && (this.tituloReceberBaixaModel == null || !this.tituloReceberBaixaModel.equals(other.tituloReceberBaixaModel))) {
            return false;
        }
        if (this.valor != other.valor && (this.valor == null || !this.valor.equals(other.valor))) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.check != null ? this.check.hashCode() : 0);
        hash = 37 * hash + (this.forma != null ? this.forma.hashCode() : 0);
        hash = 37 * hash + (this.tituloReceberBaixaModel != null ? this.tituloReceberBaixaModel.hashCode() : 0);
        hash = 37 * hash + (this.valor != null ? this.valor.hashCode() : 0);
        return hash;
    }
}// fim da class

