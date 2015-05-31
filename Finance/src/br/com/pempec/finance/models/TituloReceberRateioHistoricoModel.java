package br.com.pempec.finance.models;

import java.io.Serializable;

/**
 *
 * @author PEMPEC
 */
public class TituloReceberRateioHistoricoModel extends FinancePkModel implements Serializable {

    private Boolean check = false;
    private TituloReceberModel tituloReceberModel;
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

    public TituloReceberModel getTituloReceberModel() {
        return tituloReceberModel;
    }

    public void setTituloReceberModel(TituloReceberModel tituloReceberModel) {
        this.tituloReceberModel = tituloReceberModel;
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
        final TituloReceberRateioHistoricoModel other = (TituloReceberRateioHistoricoModel) obj;
        if (this.check != other.check && (this.check == null || !this.check.equals(other.check))) {
            return false;
        }
        if (this.tituloReceberModel != other.tituloReceberModel && (this.tituloReceberModel == null || !this.tituloReceberModel.equals(other.tituloReceberModel))) {
            return false;
        }
        if (this.historicoModel != other.historicoModel && (this.historicoModel == null || !this.historicoModel.equals(other.historicoModel))) {
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
        hash = 97 * hash + (this.check != null ? this.check.hashCode() : 0);
        hash = 97 * hash + (this.tituloReceberModel != null ? this.tituloReceberModel.hashCode() : 0);
        hash = 97 * hash + (this.historicoModel != null ? this.historicoModel.hashCode() : 0);
        hash = 97 * hash + (this.valor != null ? this.valor.hashCode() : 0);
        return hash;
    }
}
