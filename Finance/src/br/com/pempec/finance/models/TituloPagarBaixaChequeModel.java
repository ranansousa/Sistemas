package br.com.pempec.finance.models;

import java.io.Serializable;

/**
 * @author Equipe Pempec
 */
public class TituloPagarBaixaChequeModel extends FinancePkModel implements Serializable {

    private Boolean check = false;
    private ContaBancariaChequeModel contaBancariaCheque;
    private TituloPagarBaixaModel tituloPagarBaixa;
    private TipoOperacaoBancariaModel tipoOperacaoBancaria;
    private Double valor;
    private String observacao;
    private MovimentoDiarioModel movimentoDiarioModel;

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public ContaBancariaChequeModel getContaBancariaCheque() {
        return contaBancariaCheque;
    }

    public void setContaBancariaCheque(ContaBancariaChequeModel contaBancariaCheque) {
        this.contaBancariaCheque = contaBancariaCheque;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public TipoOperacaoBancariaModel getTipoOperacaoBancaria() {
        return tipoOperacaoBancaria;
    }

    public void setTipoOperacaoBancaria(TipoOperacaoBancariaModel tipoOperacaoBancaria) {
        this.tipoOperacaoBancaria = tipoOperacaoBancaria;
    }

    public TituloPagarBaixaModel getTituloPagarBaixa() {
        return tituloPagarBaixa;
    }

    public void setTituloPagarBaixa(TituloPagarBaixaModel tituloPagarBaixa) {
        this.tituloPagarBaixa = tituloPagarBaixa;
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
        final TituloPagarBaixaChequeModel other = (TituloPagarBaixaChequeModel) obj;
        if (this.contaBancariaCheque != other.contaBancariaCheque && (this.contaBancariaCheque == null || !this.contaBancariaCheque.equals(other.contaBancariaCheque))) {
            return false;
        }
        if (this.tituloPagarBaixa != other.tituloPagarBaixa && (this.tituloPagarBaixa == null || !this.tituloPagarBaixa.equals(other.tituloPagarBaixa))) {
            return false;
        }
        if (this.tipoOperacaoBancaria != other.tipoOperacaoBancaria && (this.tipoOperacaoBancaria == null || !this.tipoOperacaoBancaria.equals(other.tipoOperacaoBancaria))) {
            return false;
        }
        if (this.valor != other.valor && (this.valor == null || !this.valor.equals(other.valor))) {
            return false;
        }
        if ((this.observacao == null) ? (other.observacao != null) : !this.observacao.equals(other.observacao)) {
            return false;
        }
        if (this.movimentoDiarioModel != other.movimentoDiarioModel && (this.movimentoDiarioModel == null || !this.movimentoDiarioModel.equals(other.movimentoDiarioModel))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.contaBancariaCheque != null ? this.contaBancariaCheque.hashCode() : 0);
        hash = 13 * hash + (this.tituloPagarBaixa != null ? this.tituloPagarBaixa.hashCode() : 0);
        hash = 13 * hash + (this.tipoOperacaoBancaria != null ? this.tipoOperacaoBancaria.hashCode() : 0);
        hash = 13 * hash + (this.valor != null ? this.valor.hashCode() : 0);
        hash = 13 * hash + (this.observacao != null ? this.observacao.hashCode() : 0);
        hash = 13 * hash + (this.movimentoDiarioModel != null ? this.movimentoDiarioModel.hashCode() : 0);
        return hash;
    }
}
