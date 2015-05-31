package br.com.pempec.finance.models;

import java.io.Serializable;

/**
 *
 * @author PEMPEC
 */
public class TituloReceberRateioCCModel extends FinancePkModel implements Serializable {

    private Boolean check = false;
    private TituloReceberModel tituloReceberModel;
    private CentroCustoModel centroCustoModel;
    private Double valor;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public CentroCustoModel getCentroCustoModel() {
        return centroCustoModel;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public void setCentroCustoModel(CentroCustoModel centroCustoModel) {
        this.centroCustoModel = centroCustoModel;
    }

    public TituloReceberModel getTituloReceberModel() {
        return tituloReceberModel;
    }

    public void setTituloReceberModel(TituloReceberModel tituloReceberModel) {
        this.tituloReceberModel = tituloReceberModel;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TituloReceberRateioCCModel other = (TituloReceberRateioCCModel) obj;
        if (this.check != other.check && (this.check == null || !this.check.equals(other.check))) {
            return false;
        }
        if (this.centroCustoModel != other.centroCustoModel && (this.centroCustoModel == null || !this.centroCustoModel.equals(other.centroCustoModel))) {
            return false;
        }
        if (this.valor != other.valor && (this.valor == null || !this.valor.equals(other.valor))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.check != null ? this.check.hashCode() : 0);
        hash = 53 * hash + (this.tituloReceberModel != null ? this.tituloReceberModel.hashCode() : 0);
        hash = 53 * hash + (this.centroCustoModel != null ? this.centroCustoModel.hashCode() : 0);
        hash = 53 * hash + (this.valor != null ? this.valor.hashCode() : 0);
        return hash;
    }
}
