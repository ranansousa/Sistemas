package br.com.pempec.finance.models;

import java.io.Serializable;

public class TituloPagarBaixaFormaPagamentoModel extends FinancePkModel implements Serializable {

    //Variavel para controle do checkBox
    private Boolean check = false;
    private FormaPagamentoModel forma;
    private TituloPagarBaixaModel tituloPagarBaixaModel;
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
    

    public TituloPagarBaixaModel getTituloPagarBaixaModel() {
        return tituloPagarBaixaModel;
    }

    public void setTituloPagarBaixaModel(TituloPagarBaixaModel tituloPagarBaixaModel) {
        this.tituloPagarBaixaModel = tituloPagarBaixaModel;
    }

    public FormaPagamentoModel getForma() {
        return forma;
    }

    public void setForma(FormaPagamentoModel forma) {
        this.forma = forma;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TituloPagarBaixaFormaPagamentoModel other = (TituloPagarBaixaFormaPagamentoModel) obj;

        if (this.forma != other.forma && (this.forma == null || !this.forma.equals(other.forma))) {
            return false;
        }
        if (this.tituloPagarBaixaModel != other.tituloPagarBaixaModel && (this.tituloPagarBaixaModel == null || !this.tituloPagarBaixaModel.equals(other.tituloPagarBaixaModel))) {
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
        hash = 97 * hash + (this.check != null ? this.check.hashCode() : 0);
        hash = 97 * hash + (this.forma != null ? this.forma.hashCode() : 0);
        hash = 97 * hash + (this.tituloPagarBaixaModel != null ? this.tituloPagarBaixaModel.hashCode() : 0);
        hash = 97 * hash + (this.valor != null ? this.valor.hashCode() : 0);
        return hash;
    }
}
