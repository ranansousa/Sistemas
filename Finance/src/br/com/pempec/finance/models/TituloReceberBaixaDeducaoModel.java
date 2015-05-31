package br.com.pempec.finance.models;

import java.io.Serializable;

public class TituloReceberBaixaDeducaoModel extends FinancePkModel implements Serializable {

    private Boolean check = false;
    private TipoDeducaoModel tipoDeducao;
    private TituloReceberBaixaModel tituloReceberBaixaModel;
    private Double valor;

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
        final TituloReceberBaixaDeducaoModel other = (TituloReceberBaixaDeducaoModel) obj;
        if (this.check != other.check && (this.check == null || !this.check.equals(other.check))) {
            return false;
        }
        if (this.tipoDeducao != other.tipoDeducao && (this.tipoDeducao == null || !this.tipoDeducao.equals(other.tipoDeducao))) {
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
        int hash = 5;
        hash = 29 * hash + (this.check != null ? this.check.hashCode() : 0);
        hash = 29 * hash + (this.tipoDeducao != null ? this.tipoDeducao.hashCode() : 0);
        hash = 29 * hash + (this.tituloReceberBaixaModel != null ? this.tituloReceberBaixaModel.hashCode() : 0);
        hash = 29 * hash + (this.valor != null ? this.valor.hashCode() : 0);
        return hash;
    }
}// fim da class
