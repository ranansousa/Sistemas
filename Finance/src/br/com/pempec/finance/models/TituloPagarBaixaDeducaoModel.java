package br.com.pempec.finance.models;

import java.io.Serializable;

public class TituloPagarBaixaDeducaoModel extends FinancePkModel implements Serializable {

    private Boolean check = false;
    private TipoDeducaoModel tipoDeducao;
    private TituloPagarBaixaModel tituloPagarBaixaModel;
    private Double valor;

    public TituloPagarBaixaModel getTituloPagarBaixaModel() {
        return tituloPagarBaixaModel;
    }

    public void setTituloPagarBaixaModel(TituloPagarBaixaModel tituloPagarBaixaModel) {
        this.tituloPagarBaixaModel = tituloPagarBaixaModel;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public TipoDeducaoModel getTipoDeducao() {
        return tipoDeducao;
    }

    public void setTipoDeducao(TipoDeducaoModel tipoDeducao) {
        this.tipoDeducao = tipoDeducao;
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
        final TituloPagarBaixaDeducaoModel other = (TituloPagarBaixaDeducaoModel) obj;
        if (this.check != other.check && (this.check == null || !this.check.equals(other.check))) {
            return false;
        }
        if (this.tipoDeducao != other.tipoDeducao && (this.tipoDeducao == null || !this.tipoDeducao.equals(other.tipoDeducao))) {
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
        int hash = 5;
        hash = 29 * hash + (this.check != null ? this.check.hashCode() : 0);
        hash = 29 * hash + (this.tipoDeducao != null ? this.tipoDeducao.hashCode() : 0);
        hash = 29 * hash + (this.tituloPagarBaixaModel != null ? this.tituloPagarBaixaModel.hashCode() : 0);
        hash = 29 * hash + (this.valor != null ? this.valor.hashCode() : 0);
        return hash;
    }
}// fim da class

