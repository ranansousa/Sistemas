package br.com.pempec.finance.models;

import java.io.Serializable;

/**
 *
 * @author PEMPEC
 */
public class TituloPagarRateioHistoricoModel extends FinancePkModel implements Serializable {

    private Boolean check = false;
    private TituloPagarModel tituloPagarModel;
    private HistoricoModel historicoModel;
    private Double valor;

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public HistoricoModel getHistoricoModel() {
        return historicoModel;
    }

    public void setHistoricoModel(HistoricoModel historicoModel) {
        this.historicoModel = historicoModel;
    }

    public TituloPagarModel getTituloPagarModel() {
        return tituloPagarModel;
    }

    public void setTituloPagarModel(TituloPagarModel tituloPagarModel) {
        this.tituloPagarModel = tituloPagarModel;
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
        final TituloPagarRateioHistoricoModel other = (TituloPagarRateioHistoricoModel) obj;
        if (this.check != other.check && (this.check == null || !this.check.equals(other.check))) {
            return false;
        }
        if (this.tituloPagarModel != other.tituloPagarModel && (this.tituloPagarModel == null || !this.tituloPagarModel.equals(other.tituloPagarModel))) {
            return false;
        }
        if (this.historicoModel != other.historicoModel && (this.historicoModel == null || !this.historicoModel.equals(other.historicoModel))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + (this.check != null ? this.check.hashCode() : 0);
        hash = 11 * hash + (this.tituloPagarModel != null ? this.tituloPagarModel.hashCode() : 0);
        hash = 11 * hash + (this.historicoModel != null ? this.historicoModel.hashCode() : 0);
        hash = 11 * hash + (this.valor != null ? this.valor.hashCode() : 0);
        return hash;
    }
}
